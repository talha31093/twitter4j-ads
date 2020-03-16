package twitter4jads.impl;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsResponse;
import twitter4jads.TwitterAdsClient;
import twitter4jads.api.TwitterAdsAccountApi;
import twitter4jads.internal.http.HttpParameter;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.AdAccount;
import twitter4jads.models.ads.AdAccountNativePermissions;
import twitter4jads.models.ads.HttpVerb;
import twitter4jads.models.ads.PromotableUser;
import twitter4jads.models.ads.sort.AccountsSortByField;
import twitter4jads.util.TwitterAdUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static twitter4jads.TwitterAdsConstants.*;

/**
 * User: abhay
 * Date: 4/4/16
 * Time: 7:54 PM
 */
public class TwitterAdsAccountApiImpl implements TwitterAdsAccountApi {

    private final TwitterAdsClient twitterAdsClient;

    public TwitterAdsAccountApiImpl(TwitterAdsClient twitterAdsClient) {
        this.twitterAdsClient = twitterAdsClient;
    }

    @Override
    public BaseAdsListResponseIterable<AdAccount> getAllAccounts(Boolean withDeleted, Optional<AccountsSortByField> sortByField, Optional<List<String>> accountIds, Optional<String> q) throws TwitterException {
        final List<HttpParameter> params = new ArrayList<>();
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + PREFIX_ACCOUNTS_URI;
        if (TwitterAdUtil.isNotNull(withDeleted)) {
            params.add(new HttpParameter(PARAM_WITH_DELETED, withDeleted));
        }
        if (accountIds != null && accountIds.isPresent()) {
            params.add(new HttpParameter(PARAM_ACCOUNT_IDS, StringUtils.join(accountIds.get(), ",")));
        }
        if (q != null && q.isPresent()) {
            params.add(new HttpParameter(PARAM_Q, q.get()));
        }
        if (sortByField != null && sortByField.isPresent()) {
            params.add(new HttpParameter(PARAM_SORT_BY, sortByField.get().getField()));
        }
        Type type = new TypeToken<BaseAdsListResponse<AdAccount>>() {
        }.getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsResponse<AdAccount> getAdAccountById(String accountId, Boolean withDeleted) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        HttpParameter[] param;

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + PREFIX_ACCOUNTS_URI + accountId;
        param = new HttpParameter[]{new HttpParameter(PARAM_WITH_DELETED, withDeleted)};
        Type type = new TypeToken<BaseAdsResponse<AdAccount>>() {
        }.getType();

        return twitterAdsClient.executeHttpRequest(baseUrl, param, type, HttpVerb.GET);
    }

    @Override
    public List<String> getAccountPermissions(String accountId) throws TwitterException {
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + PREFIX_ACCOUNTS_URI + accountId + PATH_FEATURES;
        final HttpResponse httpResponse = twitterAdsClient.getWithoutMergeOfParams(baseUrl, null);
        final List<String> permissionsFromChannel = Lists.newArrayList();
        try {
            Type type = new TypeToken<BaseAdsListResponse<String>>() {
            }.getType();

            final BaseAdsListResponse<String> permissions = TwitterAdUtil.constructBaseAdsListResponse(httpResponse, httpResponse.asString(), type);
            if (permissions == null || CollectionUtils.isEmpty(permissions.getData())) {
                throw new TwitterException("Empty response returned for Account Permissions");
            }

            final List<String> data = permissions.getData();
            permissionsFromChannel.addAll(data);
        } catch (Exception e) {
            throw new TwitterException("Exception occurred while getting the Account Permissions", e);
        }

        return permissionsFromChannel;
    }

    @Override
    public BaseAdsListResponseIterable<PromotableUser> getPromotableUsers(String accountId, boolean withDeleted) throws TwitterException {
        TwitterAdUtil.ensureNotNull(accountId, "accountId");
        final List<HttpParameter> params = new ArrayList<>();
        params.add(new HttpParameter(PARAM_WITH_DELETED, withDeleted));

        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + PREFIX_ACCOUNTS_URI + accountId + PATH_PROMOTABLE_USERS;
        final Type type = new TypeToken<BaseAdsListResponse<PromotableUser>>() {
        }.getType();

        return twitterAdsClient.executeHttpListRequest(baseUrl, params, type);
    }

    @Override
    public BaseAdsResponse<AdAccountNativePermissions> getAdAccountNativePermissions(String accountId) throws TwitterException {
        final String baseUrl = twitterAdsClient.getBaseAdsAPIUrl() + PREFIX_ACCOUNTS_URI + accountId + AUTHENTICATED_USER_ACCESS;
        Type type = new TypeToken<BaseAdsResponse<AdAccountNativePermissions>>() {
        }.getType();
        return twitterAdsClient.executeHttpRequest(baseUrl, null, type, HttpVerb.GET);
    }
}

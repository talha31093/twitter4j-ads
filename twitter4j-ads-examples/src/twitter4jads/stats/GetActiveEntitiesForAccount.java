package twitter4jads.stats;

import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsTest;
import twitter4jads.TwitterAds;
import twitter4jads.api.TwitterAdsStatApi;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.TwitterActiveEntitiesResponse;
import twitter4jads.models.ads.TwitterEntityType;

public class GetActiveEntitiesForAccount extends BaseAdsTest {

    public static void main(String[] args) throws TwitterException {
        TwitterAds twitterAdsInstance = getTwitterAdsInstance();
        TwitterAdsStatApi statApi = twitterAdsInstance.getStatApi();
        BaseAdsListResponseIterable<TwitterActiveEntitiesResponse> activeEntitiesResponseBaseAdsListResponseIterable =
                statApi.fetchActiveEntities("18ce54k4rvf", TwitterEntityType.LINE_ITEM, null
                , null, null,1578873600000l, 1579132800000l);
        System.out.println();
    }
}

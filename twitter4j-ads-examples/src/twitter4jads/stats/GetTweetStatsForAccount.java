package twitter4jads.stats;

import com.google.common.collect.Lists;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsTest;
import twitter4jads.TwitterAds;
import twitter4jads.api.TwitterAdsStatApi;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.Granularity;
import twitter4jads.models.ads.Placement;
import twitter4jads.models.ads.TwitterEntityStatistics;
import twitter4jads.models.ads.TwitterEntityType;

import java.util.List;

/**
 * User: shivraj
 * Date: 12/05/16 2:08 PM.
 */
public class GetTweetStatsForAccount extends BaseAdsTest {

    public static void main(String[] args) {
        TwitterAds twitterAdsInstance = getTwitterAdsInstance();
        TwitterAdsStatApi statApi = twitterAdsInstance.getStatApi();
        List<TwitterEntityStatistics> twitterEntityStatsList = Lists.newArrayList();
        long since = 1601769600000L;
        long until = 1602028800000L;
        try {
            BaseAdsListResponseIterable<TwitterEntityStatistics> allTwitterEntityStats = statApi.fetchStatsSync("18ce55907vh", TwitterEntityType.CAMPAIGN, Lists.<String>newArrayList("eilx6"), since, until, Boolean.FALSE, Granularity.DAY, Placement.ALL_ON_TWITTER);
            for (BaseAdsListResponse<TwitterEntityStatistics> allTwitterEntityStat : allTwitterEntityStats) {
                twitterEntityStatsList.addAll(allTwitterEntityStat.getData());
            }
            System.out.println(twitterEntityStatsList.size());
        } catch (TwitterException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}
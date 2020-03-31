package twitter4jads.promotedtweets;

import com.google.common.collect.Lists;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsListResponseIterable;
import twitter4jads.BaseAdsTest;
import twitter4jads.TwitterAds;
import twitter4jads.api.TwitterAdsPromotedTweetApi;
import twitter4jads.api.TwitterAdsTweetsApi;
import twitter4jads.internal.models4j.TwitterException;
import twitter4jads.models.ads.PromotedTweets;
import twitter4jads.models.ads.Tweet;
import twitter4jads.models.ads.TwitterTimelineType;
import twitter4jads.models.ads.TwitterTweetType;

import java.util.List;

/**
 * User: shivraj
 * Date: 12/05/16 2:08 PM.
 */
public class GetPromotedTweetForAccount extends BaseAdsTest {

    public static void main(String[] args) {
        TwitterAds twitterAdsInstance = getTwitterAdsInstance();
        TwitterAdsTweetsApi promotedTweetApi = twitterAdsInstance.getTweetsApi();
        List<Tweet> promotedTweetsList = Lists.newArrayList();
        try {
            BaseAdsListResponseIterable<Tweet> allPromotedTweets = promotedTweetApi
                    .getTweets("18ce54k4rvf", TwitterTweetType.PUBLISHED, 2,
                            null, null, TwitterTimelineType.NULLCAST,
                            true, null, null);
            for (BaseAdsListResponse<Tweet> allPromotedTweet : allPromotedTweets) {
                promotedTweetsList.addAll(allPromotedTweet.getData());
            }
            System.out.println(promotedTweetsList.size());
        } catch (TwitterException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}
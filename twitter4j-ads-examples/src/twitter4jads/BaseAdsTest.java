package twitter4jads;

import twitter4jads.conf.ConfigurationBuilder;

/**
 * User: abhishekanand
 * Date: 10/05/16 10:49 PM.
 */
public class BaseAdsTest {

    public static TwitterAds getTwitterAdsInstance() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerSecret("pNAuAeyICHeqfORmbDlMyalCnTIAgRQ0lpmawMBEKwGn1lzk8W").setOAuthConsumerKey("0lt3gcu5b2c0JuAdxsH4ojNvF").setOAuthAccessToken("933737516-WR6ckg5Vd5sDeZykxEK2x1m41XiKoLoc7Vh7wnTW").setOAuthAccessTokenSecret("GUBDNlhhoM0ZD2l1TNv00ZV7njR8qkUGpg5Y6I14HDOCb").setHttpRetryCount(0).setHttpConnectionTimeout(5000);
        return new TwitterAdsFactory(configurationBuilder.build()).getAdsInstance();
    }


}

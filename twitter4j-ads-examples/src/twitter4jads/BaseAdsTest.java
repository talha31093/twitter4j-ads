package twitter4jads;

import twitter4jads.conf.ConfigurationBuilder;

/**
 * User: abhishekanand
 * Date: 10/05/16 10:49 PM.
 */
public class BaseAdsTest {

    public static TwitterAds getTwitterAdsInstance() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerSecret("gryztMQW6uRwWqYSovmMgzvleyI9owW3AW76nzBxJO7cfZkEkv").setOAuthConsumerKey("wLlBIFJ6Kc024ESG2VYUSQNDM").setOAuthAccessToken("2303139841-MZolz3tXaQCEnw5LUEK7e9hvI0QuFkK3nrPI2Dw").setOAuthAccessTokenSecret("46bxSzq5gN2u6gv9mK2OtlFBKmvycJEbo0rEaZTq74XyQ").setHttpRetryCount(0).setHttpConnectionTimeout(5000);
        return new TwitterAdsFactory(configurationBuilder.build()).getAdsInstance();
    }


}

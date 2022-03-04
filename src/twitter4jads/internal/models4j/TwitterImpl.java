package twitter4jads.internal.models4j;

import twitter4jads.auth.Authorization;
import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpParameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A java representation of the <a href="https://dev.twitter.com/docs/api">Twitter REST API</a><br>
 * This class is thread safe and can be cached/re-used and used concurrently.<br>
 * Currently this class is not carefully designed to be extended. It is suggested to extend this class only for mock testing purpose.<br>
 *
 */
public class TwitterImpl extends TwitterBaseImpl implements Twitter {
    private static final long serialVersionUID = -1486360080128882436L;

    private final String IMPLICIT_PARAMS_STR;
    private final HttpParameter[] IMPLICIT_PARAMS;
    private final HttpParameter INCLUDE_MY_RETWEET;
    private final HttpParameter TWEET_MODE;

    private static final Map<Configuration, HttpParameter[]> implicitParamsMap = new ConcurrentHashMap<>();
    private static final Map<Configuration, String> implicitParamsStrMap = new ConcurrentHashMap<>();

    /*package*/
    public TwitterImpl(Configuration conf, Authorization auth) {
        super(conf, auth);
        INCLUDE_MY_RETWEET = new HttpParameter("include_my_retweet", conf.isIncludeMyRetweetEnabled());
        TWEET_MODE = new HttpParameter("tweet_mode", conf.getTweetMode());

        HttpParameter[] implicitParams = implicitParamsMap.get(conf);
        String implicitParamsStr = implicitParamsStrMap.get(conf);
        if (implicitParams == null) {
            String includeEntities = conf.isIncludeEntitiesEnabled() ? "1" : "0";
            String includeRTs = conf.isIncludeRTsEnabled() ? "1" : "0";
            boolean contributorsEnabled = conf.getContributingTo() != -1L;
            implicitParamsStr = "include_entities=" + includeEntities + "&include_rts=" + includeRTs +
                                (contributorsEnabled ? "&contributingto=" + conf.getContributingTo() : "");
            implicitParamsStrMap.put(conf, implicitParamsStr);

            List<HttpParameter> params = new ArrayList<HttpParameter>();
            params.add(new HttpParameter("include_entities", includeEntities));
            params.add(new HttpParameter("include_rts", includeRTs));
            if (contributorsEnabled) {
                params.add(new HttpParameter("contributingto", conf.getContributingTo()));
            }
            implicitParams = params.toArray(new HttpParameter[params.size()]);
            implicitParamsMap.put(conf, implicitParams);
        }
        IMPLICIT_PARAMS = implicitParams;
        IMPLICIT_PARAMS_STR = implicitParamsStr;
    }

    @Override
    String getImplicitParamsStr() {
        return IMPLICIT_PARAMS_STR;
    }

    @Override
    HttpParameter[] getImplicitParams() {
        return IMPLICIT_PARAMS;
    }

    @Override
    public String toString() {
        return "TwitterImpl{" +
               "INCLUDE_MY_RETWEET=" + INCLUDE_MY_RETWEET +
               '}';
    }
}

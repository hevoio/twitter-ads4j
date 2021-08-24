package twitter4jads.internal.json;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.Status;
import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;
import twitter4jads.internal.models4j.RelatedResults;
import twitter4jads.internal.models4j.ResponseList;
import twitter4jads.internal.models4j.TwitterException;

import java.util.HashMap;
import java.util.Map;

/**
 * A data class representing related_results API response
 *
 *  Mocel - mocel at guma.jp
 */
/*package*/ final class RelatedResultsJSONImpl extends TwitterResponseImpl implements RelatedResults, java.io.Serializable {

    private static final String TWEETS_WITH_CONVERSATION = "TweetsWithConversation";
    private static final String TWEETS_WITH_REPLY = "TweetsWithReply";
    private static final String TWEETS_FROM_USER = "TweetsFromUser";
    private static final long serialVersionUID = -7417061781993004083L;

    private Map<String, ResponseList<Status>> tweetsMap;

    /* package */ RelatedResultsJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        JSONArray jsonArray = res.asJSONArray();
        init(jsonArray, res, conf.isJSONStoreEnabled());
    }

    /* package */ RelatedResultsJSONImpl(JSONArray jsonArray) throws TwitterException {
        super();
        init(jsonArray, null, false);

    }

    private void init(JSONArray jsonArray, HttpResponse res, boolean registerRawJSON) throws TwitterException {
        tweetsMap = new HashMap<String, ResponseList<Status>>(2);
        try {
            for (int i = 0, listLen = jsonArray.length(); i < listLen; ++i) {
                JSONObject o = jsonArray.getJSONObject(i);
                if (!"Tweet".equals(o.getString("resultType"))) {
                    continue;
                }

                String groupName = o.getString("groupName");
                if (groupName.length() == 0
                        || !(groupName.equals(TWEETS_WITH_CONVERSATION) || groupName.equals(TWEETS_WITH_REPLY) || groupName.equals(TWEETS_FROM_USER))) {
                    continue;
                }

                JSONArray results = o.getJSONArray("results");
                ResponseList<Status> statuses = tweetsMap.get(groupName);
                if (statuses == null) {
                    statuses = new ResponseListImpl<Status>(results.length(), res);
                    tweetsMap.put(groupName, statuses);
                }

                for (int j = 0, resultsLen = results.length(); j < resultsLen; ++j) {
                    JSONObject json = results.getJSONObject(j).getJSONObject("value");
                    Status status = new StatusJSONImpl(json);
                    if (registerRawJSON) {
                        DataObjectFactoryUtil.registerJSONObject(status, json);
                    }
                    statuses.add(status);
                }
                if (registerRawJSON) {
                    DataObjectFactoryUtil.registerJSONObject(statuses, results);
                }
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseList<Status> getTweetsWithConversation() {
        ResponseList<Status> statuses = this.tweetsMap.get(TWEETS_WITH_CONVERSATION);
        if (statuses != null) {
            return statuses;
        } else {
            return new ResponseListImpl<Status>(0, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseList<Status> getTweetsWithReply() {
        ResponseList<Status> statuses = this.tweetsMap.get(TWEETS_WITH_REPLY);
        if (statuses != null) {
            return statuses;
        } else {
            return new ResponseListImpl<Status>(0, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseList<Status> getTweetsFromUser() {
        ResponseList<Status> statuses = this.tweetsMap.get(TWEETS_FROM_USER);
        if (statuses != null) {
            return statuses;
        } else {
            return new ResponseListImpl<Status>(0, null);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + tweetsMap.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RelatedResultsJSONImpl) {
            RelatedResultsJSONImpl other = (RelatedResultsJSONImpl) obj;
            if (tweetsMap == null) {
                if (other.tweetsMap != null)
                    return false;
            } else if (!tweetsMap.equals(other.tweetsMap))
                return false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RelatedResultsJSONImpl {tweetsMap=" + tweetsMap + "}";
    }
}

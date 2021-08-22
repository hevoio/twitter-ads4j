package twitter4jads.api.internal;


import twitter4jads.internal.models4j.RelatedResults;
import twitter4jads.internal.models4j.TwitterException;

public interface UndocumentedResources {
    /**
     * If available, returns an array of replies and mentions related to the specified Tweet. There is no guarantee there will be any replies or mentions in the response. This method is only available to users who have access to #newtwitter.
     * <br>This method has not been finalized and the interface is subject to change in incompatible ways.
     * <br>This method calls http://api.twitter.com/1.1/related_results/show/:id
     *
     * @param statusId the numerical ID of the status you're trying to retrieve
     * @return the related results of a given tweet
     * @see <a href="http://groups.google.com/group/twitter-api-announce/msg/34909da7c399169e">#newtwitter and the API - Twitter API Announcements | Google Group</a>
     * @since Twitter4J 2.1.8
     */
    RelatedResults getRelatedResults(long statusId) throws TwitterException;
}

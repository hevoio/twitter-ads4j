package com.hevodata.twitterads4j.internal.models4j;

import com.hevodata.twitterads4j.api.internal.TweetsResources;
import com.hevodata.twitterads4j.api.internal.UndocumentedResources;
import com.hevodata.twitterads4j.api.internal.UsersResources;
import com.hevodata.twitterads4j.auth.OAuthSupport;

/**
 *
 * @since Twitter4J 2.2.0
 */
public interface Twitter extends java.io.Serializable,
        OAuthSupport,
        TwitterBase,
        TweetsResources,
        UsersResources,
        UndocumentedResources {
}

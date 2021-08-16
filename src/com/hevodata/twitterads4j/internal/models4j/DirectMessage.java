package com.hevodata.twitterads4j.internal.models4j;

import java.util.Date;

/**
 * A data interface representing sent/received direct message.
 *
 *
 */
public interface DirectMessage extends TwitterResponse, EntitySupport, java.io.Serializable, JSONResponse {

    long getId();

    String getIdStr();

    String getText();

    String getUnEscapedText();

    long getSenderId();

    long getRecipientId();

    /**
     * @return created_at
     * @since Twitter4J 1.1.0
     */
    Date getCreatedAt();

    String getSenderScreenName();

    String getRecipientScreenName();


    User getSender();


    User getRecipient();

}

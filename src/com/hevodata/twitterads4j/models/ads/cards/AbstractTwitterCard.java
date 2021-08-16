package com.hevodata.twitterads4j.models.ads.cards;

import com.google.gson.annotations.SerializedName;
import com.hevodata.twitterads4j.models.ads.TwitterEntity;

import java.util.Date;

/**
 *
 * Date: 13/06/14
 * Time: 3:50 PM
 */

public abstract class AbstractTwitterCard extends TwitterEntity {

    @SerializedName("name")
    private String name;

    @SerializedName("card_type")
    private TwitterCardType twitterCardType;

    @SerializedName("card_uri")
    private String cardUri;

    @SerializedName("updated_at")
    private Date updatedAt;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("deleted")
    private boolean deleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TwitterCardType getTwitterCardType() {
        return twitterCardType;
    }

    @SuppressWarnings("unused")
    public void setTwitterCardType(TwitterCardType twitterCardType) {
        this.twitterCardType = twitterCardType;
    }

    public String getCardUri() {
        return cardUri;
    }

    public void setCardUri(String cardUri) {
        this.cardUri = cardUri;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

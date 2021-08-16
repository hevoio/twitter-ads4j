package com.hevodata.twitterads4j.models.ads;


import com.hevodata.twitterads4j.BaseListResponse;
import com.hevodata.twitterads4j.internal.models4j.Status;

import java.util.List;

/**
 *
 * Date: 3/17/16
 * Time: 12:49 PM
 */
public class ListStatusResponse extends BaseListResponse<Status> {

    public List<Status> getStatuses() {
        return getData();
    }

}

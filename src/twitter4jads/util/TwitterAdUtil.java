package twitter4jads.util;

import com.google.gson.Gson;
import twitter4jads.BaseAdsListResponse;
import twitter4jads.BaseAdsResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.RateLimitStatus;
import twitter4jads.models.ads.TwitterAdObjective;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.collections.SetUtils.unmodifiableSet;
import static twitter4jads.models.ads.TwitterAdObjective.PREROLL_VIEWS;
import static twitter4jads.models.ads.TwitterAdObjective.WEBSITE_CONVERSIONS;

/**
 *
 * Date: 29/01/14
 * Time: 2:23 PM
 */
public final class TwitterAdUtil {

    public static final String UTC_TMZ = "UTC";

    public static <T> String getCsv(Collection<T> collection) {
        String result = "";
        if (collection != null && collection.size() != 0) {
            result = getLocalCsv(collection);
        }
        return result;
    }

    public static <T> String getLocalCsv(Collection<T> coll) {
        StringBuilder buff = new StringBuilder();
        int i = 0;
        for (T value : coll) {
            if (i != 0) {
                buff.append(",");
            }
            buff.append(value);
            i++;
        }
        return buff.toString();
    }

    public static boolean isNotNullOrEmpty(String string) {
        return !(string == null || string.isEmpty());
    }

    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static void ensureNotNull(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException(name + " can not be null.");
        }
    }

    public static <T> void ensureNotEmpty(Collection<T> collection, String name) {
        if (!isNotEmpty(collection)) {
            throw new IllegalArgumentException(name + " can not be null or empty.");
        }
    }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && collection.size() != 0;
    }

    public static <T> Boolean ensureMaxSize(Collection<T> collection, int size) {
        if (isNotEmpty(collection) && size > 0) {
            if (collection.size() > size) {
                throw new IllegalArgumentException("Collection size must be less than " + size);
            }
        }
        return true;
    }

    public static String getDelimiterSeparatedMethod(final Collection<String> values, String delimiter) {
        if (values == null || values.isEmpty()) {
            return "";
        }
        String rv = "";
        for (String value : values) {
            rv = rv + value + delimiter;
        }
        rv = rv.substring(0, rv.length() - 1);
        return rv;
    }

    public static <E> List<E> createMutableList(Collection<E> collection) {
        List<E> mutableList = new ArrayList<>();
        if (isNotEmpty(collection)) {
            for (E data : collection) {
                mutableList.add(data);
            }
        }
        return mutableList;
    }

    public static <T> BaseAdsResponse<T> constructBaseAdsResponse(HttpResponse httpResponse, String response, Type type) throws IOException {
        if (type == null) {
            return null;
        }
        Gson gson = new Gson();
        BaseAdsResponse<T> baseResponse = gson.fromJson(response, type);
        RateLimitStatus rateLimitStatus = TwitterAdHttpUtils.createFromResponseHeader(httpResponse);
        baseResponse.setRateLimitStatus(rateLimitStatus);
        return baseResponse;
    }

}

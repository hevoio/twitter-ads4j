package twitter4jads.internal.models4j;

import twitter4jads.internal.json.z_T4JInternalParseUtil;
import twitter4jads.internal.org.json.JSONObject;

/**
 * StatusDeletionNotice implementation. This class is NOT intended to be extended but left non-final for the ease of mock testing.
 *
 *
 * @since Twitter4J 2.1.2
 */
public class StatusDeletionNoticeImpl extends JSONResponseImpl implements StatusDeletionNotice, java.io.Serializable {

    private long statusId;
    private long userId;
    private static final long serialVersionUID = 1723338404242596062L;

    /*package*/ StatusDeletionNoticeImpl(JSONObject status) {
        super(status);
        this.statusId = z_T4JInternalParseUtil.getLong("id", status);
        this.userId = z_T4JInternalParseUtil.getLong("user_id", status);
    }
        
    @Override
    public long getStatusId() {
        return statusId;
    }

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public int compareTo(StatusDeletionNotice that) {
        long delta = this.statusId - that.getStatusId();
        if (delta < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (delta > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusDeletionNoticeImpl that = (StatusDeletionNoticeImpl) o;

        if (statusId != that.statusId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (statusId ^ (statusId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StatusDeletionNoticeImpl{" +
                "statusId=" + statusId +
                ", userId=" + userId +
                '}';
    }
}


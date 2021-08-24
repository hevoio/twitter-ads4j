package twitter4jads.internal.json;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.http.HttpResponse;
import twitter4jads.internal.models4j.*;
import twitter4jads.internal.org.json.JSONArray;
import twitter4jads.internal.org.json.JSONException;
import twitter4jads.internal.org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A data class representing Basic list information element
 *
 *
 */
public class UserListJSONImpl extends TwitterResponseImpl implements UserList, java.io.Serializable {

    private long id;
    private String idStr;
    private String name;
    private String fullName;
    private String slug;
    private String description;
    private int subscriberCount;
    private int memberCount;
    private String uri;
    private boolean mode;
    private User user;
    private boolean following;
    private static final long serialVersionUID = -6345893237975349030L;

    /*package*/ UserListJSONImpl(HttpResponse res, Configuration conf) throws TwitterException {
        super(res);
        init(getJSONObject());
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
        }
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.registerJSONObject(this, getJSONObject());
        }
    }

    public UserListJSONImpl(JSONObject json) throws TwitterException {
        super(json);
        init(json);
    }

    private void init(JSONObject json) throws TwitterException {
        id = z_T4JInternalParseUtil.getLong("id", json);
        idStr = z_T4JInternalParseUtil.getRawString("id_str", json);
        name = z_T4JInternalParseUtil.getRawString("name", json);
        fullName = z_T4JInternalParseUtil.getRawString("full_name", json);
        slug = z_T4JInternalParseUtil.getRawString("slug", json);
        description = z_T4JInternalParseUtil.getRawString("description", json);
        subscriberCount = z_T4JInternalParseUtil.getInt("subscriber_count", json);
        memberCount = z_T4JInternalParseUtil.getInt("member_count", json);
        uri = z_T4JInternalParseUtil.getRawString("uri", json);
        mode = "public".equals(z_T4JInternalParseUtil.getRawString("mode", json));
        following = z_T4JInternalParseUtil.getBoolean("following", json);

        try {
            if (!json.isNull("user")) {
                user = new UserJSONImpl(json.getJSONObject("user"));
            }
        } catch (JSONException jsone) {
            throw new TwitterException(jsone.getMessage() + ":" + json.toString(), jsone);
        }
    }

    @Override
    public int compareTo(UserList that) {
        long val = this.id - that.getId();
        if (val == 0l) {
            return 0;
        }
        return val > 0l ? 1 : -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getId() {
        if (idStr != null) {
            try {
                /**
                 * This is needed buecause the id in long and the id_str in string in the repsonse from twitter are different. And the long doesn't make
                 * sense it gives 404 on lookup. We have to get this id_str stored in our entities. i.e., stream and profile list. Rather than handing that
                 * from UI; ensuring here that the id and id_str is the correct id. for more info SPR-27778
                 */
                Long idLong = Long.valueOf(idStr);
                if (idLong != null && id != idLong) {
                    id = idLong;
                }
            } catch (Exception ignored) {
            }
        }
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIdStr() {
        return idStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFullName() {
        return fullName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSlug() {
        return slug;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSubscriberCount() {
        return subscriberCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMemberCount() {
        return memberCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getURI() {
        try {
            return new URI(uri);
        } catch (URISyntaxException ex) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPublic() {
        return mode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowing() {
        return following;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser() {
        return user;
    }

    /*package*/
    static PagableResponseList<UserList> createPagableUserListList(HttpResponse res, Configuration conf) throws TwitterException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("lists");
            int size = list.length();
            PagableResponseList<UserList> users = new PagableResponseListImpl<UserList>(size, json, res);
            for (int i = 0; i < size; i++) {
                JSONObject userListJson = list.getJSONObject(i);
                UserList userList = new UserListJSONImpl(userListJson);
                users.add(userList);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userList, userListJson);
                }
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(users, json);
            }
            return users;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        } catch (TwitterException te) {
            throw te;
        }
    }

    /*package*/
    static ResponseList<UserList> createUserListList(HttpResponse res, Configuration conf) throws TwitterException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONArray list = res.asJSONArray();
            int size = list.length();
            ResponseList<UserList> users = new ResponseListImpl<UserList>(size, res);
            for (int i = 0; i < size; i++) {
                JSONObject userListJson = list.getJSONObject(i);
                UserList userList = new UserListJSONImpl(userListJson);
                users.add(userList);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(userList, userListJson);
                }
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(users, list);
            }
            return users;
        } catch (JSONException jsone) {
            throw new TwitterException(jsone);
        } catch (TwitterException te) {
            throw te;
        }
    }

    @Override
    public int hashCode() {
        return this.idStr.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj instanceof UserList && ((UserList) obj).getIdStr().equals(this.idStr);
    }

    @Override
    public String toString() {
        return "UserListJSONImpl{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", fullName='" + fullName + '\'' +
               ", slug='" + slug + '\'' +
               ", description='" + description + '\'' +
               ", subscriberCount=" + subscriberCount +
               ", memberCount=" + memberCount +
               ", uri='" + uri + '\'' +
               ", mode=" + mode +
               ", user=" + user +
               ", following=" + following +
               '}';
    }
}

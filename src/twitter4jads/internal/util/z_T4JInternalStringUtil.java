package twitter4jads.internal.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @since Twitter4J 2.1.4
 */
public class z_T4JInternalStringUtil {
    private z_T4JInternalStringUtil() {
        throw new AssertionError();
    }

    public static String maskString(String str) {
        StringBuilder buf = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            buf.append("*");
        }
        return buf.toString();
    }

    // for JDK1.4 compatibility

    public static String[] split(String str, String separator) {
        String[] returnValue;
        int index = str.indexOf(separator);
        if (index == -1) {
            returnValue = new String[]{str};
        } else {
            List<String> strList = new ArrayList<String>();
            int oldIndex = 0;
            while (index != -1) {
                String subStr = str.substring(oldIndex, index);
                strList.add(subStr);
                oldIndex = index + separator.length();
                index = str.indexOf(separator, oldIndex);
            }
            if (oldIndex != str.length()) {
                strList.add(str.substring(oldIndex));
            }
            returnValue = strList.toArray(new String[strList.size()]);
        }

        return returnValue;
    }

    public static String join(int[] follows) {
        StringBuilder buf = new StringBuilder(11 * follows.length);
        for (int follow : follows) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(follow);
        }
        return buf.toString();
    }

    public static String join(long[] follows) {
        StringBuilder buf = new StringBuilder(11 * follows.length);
        for (long follow : follows) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(follow);
        }
        return buf.toString();
    }

    public static String join(String[] track) {
        StringBuilder buf = new StringBuilder(11 * track.length);
        for (String str : track) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(str);
        }
        return buf.toString();
    }

    public static String join(List<String> strs) {
        StringBuilder buf = new StringBuilder(11 * strs.size());
        for (String str : strs) {
            if (0 != buf.length()) {
                buf.append(",");
            }
            buf.append(str);
        }
        return buf.toString();
    }
}

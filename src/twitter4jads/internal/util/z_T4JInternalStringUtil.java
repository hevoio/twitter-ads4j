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
}

package units;

import java.util.List;

public class Common {
    /**
     * 判断一个字符串是否为空
     *
     * @param str
     * @return 如果不为空,则返回false
     */
    public static boolean isEmpty(String str) {
        if (str != null && !"".equals(str) && str.trim().length() > 0){
            return false;
        }
        return true;
    }
    /**
     * 判断一个集合是否为null或size=0
     * @param list
     * @return 如果不为空,则返回false
     */
    public static boolean isEmpty(List list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }
}

package utils.db.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @projectName: myUtils
 * @package: utils.db.utils
 * @className: TransformHump
 * @author: 胡代伟
 * @description: 驼峰命名转换
 * @date: 2023/12/1 19:41
 * @version: 1.0
 */
public class TransformHump {
    private static final Pattern linePattern = Pattern.compile("_(\\w)");
    /**
     * @param str:带下划线的字符串
     * @return String 驼峰命名字符串
     * @author 胡代伟
     * @description 下划线转驼峰
     * @date 2023/11/9 14:17
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }



    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    /**
     * @param str:驼峰命名字符串
     * @return String 带下划线的字符串
     * @author 胡代伟
     * @description 驼峰转下划线
     * @date 2023/11/9 14:19
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(lineToHump("create_time"));
        System.out.println(humpToLine("createTime"));

        int pageSize=10;
        long size=134;
        System.out.println((size/pageSize)+1);

        String sql="select * from class_info order by id DESC  limit ?,?";
        String limit = sql.split("limit")[0];
        System.out.println(limit);
    }
}

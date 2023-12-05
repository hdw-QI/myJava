package utils.db.utils;

import utils.db.model.PageParams;

/**
 * @projectName: myUtils
 * @package: utils.db.utils
 * @className: GetSQL
 * @author: 胡代伟
 * @description: 获取增删改查的sql语句的PreparedStatement模板
 * @date: 2023/12/1 19:59
 * @version: 1.0
 */
public class GetSQLTemplate {
    private static final String insertOneSQLTemplate="INSERT INTO %s(%s) VALUES(%s)";

    private static final String deleteWhereIdInSQLTemplate="DELETE FROM %s WHERE %s IN (%s)";

    private static final String updateWhereEqIdSQLTemplate="UPDATE %s SET %s WHERE %s = ?";

    private static final String queryWhereInIdSQLTemplate="SELECT * FROM %s WHERE %s IN (%s)";

    private static final String queryAllSQLTemplate="SELECT * FROM %s ";

    private static final String queryPageAllSQLTemplate="SELECT * FROM %s LIMIT ?,?";

    /**
     * @param tableName: 表名
     * @param fieldListString: 如："id,name,age"。id、name、age为数据库中的字段名
     * @param placeholderString: 如："?,?,?" 占位符
     * @return String
     * @author 胡代伟
     * @description 获取添加的sql语句的PreparedStatement模板
     * @date 2023/12/1 20:03
     */
    public static String insertOne(String tableName, String fieldListString, String placeholderString) {
        return String.format(insertOneSQLTemplate, tableName, fieldListString, placeholderString);
    }

    /**
     * @param tableName: 表名
     * @param placeholderString: 如："?,?,?" 占位符
     * @return String
     * @author 胡代伟
     * @description 获取通过多个id删除的sql语句的PreparedStatement模板获取添加的sql语句的PreparedStatement模板
     * @date 2023/12/1 20:23
     */
    public static String deleteWhereIdIn(String tableName, String idFieldName, String placeholderString) {
        return String.format(deleteWhereIdInSQLTemplate, tableName,idFieldName, placeholderString);
    }

    /**
     * @param tableName:表名
     * @param fieldAndPlaceholderListString: 如："id=?,name=?,age=?"
     * @param idFieldName: 数据库主键的字段名
     * @return String
     * @author 胡代伟
     * @description 获取通过id修改的sql语句的PreparedStatement模板
     * @date 2023/12/1 20:29
     */
    public static String updateWhereEqId(String tableName, String fieldAndPlaceholderListString, String idFieldName) {
        return String.format(updateWhereEqIdSQLTemplate, tableName, fieldAndPlaceholderListString,idFieldName);
    }

    /**
     * @param tableName: 表名
     * @param idFieldName: 数据库主键的字段名
     * @param placeholderString: 占位符
     * @return String
     * @author 胡代伟
     * @description TODO
     * @date 2023/12/2 13:00
     */
    public static String queryWhereInId(String tableName,String idFieldName,String placeholderString) {
        return String.format(queryWhereInIdSQLTemplate, tableName,idFieldName,placeholderString);
    }

    /**
     * @param tableName: 表名
     * @return String
     * @author 胡代伟
     * @description 获取查询全部的sql语句的PreparedStatement模板
     * @date 2023/12/2 12:01
     */
    public static String queryAll(String tableName) {
        return String.format(queryAllSQLTemplate, tableName);
    }

    /**
     * @param tableName: 表名
     * @return String
     * @author 胡代伟
     * @description 分页查询所有
     * @date 2023/12/2 13:48
     */
    public static String queryPageAll(String tableName){
        return String.format(queryPageAllSQLTemplate, tableName);
    }


    public static void main(String[] args) {
        String insert = insertOne("table", "id,name,age", "?,?,?");
//        System.out.println(insert);
        String deleteWhereIdIn = deleteWhereIdIn("table", "id","?,?,?");
//        System.out.println(deleteWhereIdIn);
        String updateWhereEqId = updateWhereEqId("table", "id=?,name=?,age=?","id");
//        System.out.println(updateWhereEqId);
        String queryEqId = queryWhereInId("employee","id","?,?,?");
//        System.out.println(queryEqId);

        String queryAll = queryAll("employee");
//        System.out.println(queryAll);

        PageParams pageParams = new PageParams(1, 10);
        String s = queryPageAll("employee");
        System.out.println(s);
    }
}

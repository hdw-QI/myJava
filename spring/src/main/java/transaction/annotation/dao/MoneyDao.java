package transaction.annotation.dao;

/**
 * @projectName: git
 * @package: transaction.xml.dao
 * @className: MoneyDao
 * @author: 胡代伟
 * @description: MoneyDao
 * @date: 2023/12/22 16:24
 * @version: 1.0
 */
public interface MoneyDao {

    //减少余额
    void reduceMoney(int id, double balance);
    //增加余额
    void addMoney(int id, double balance);
}

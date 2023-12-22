package transaction.xml.service;

/**
 * @projectName: git
 * @package: transaction.xml.service
 * @className: MoneyService
 * @author: 胡代伟
 * @description: MoneyService
 * @date: 2023/12/22 15:59
 * @version: 1.0
 */
public interface MoneyService {
    //实现转账的业务方法
    void accountSalary(int lessenId,int addId,double money);
}

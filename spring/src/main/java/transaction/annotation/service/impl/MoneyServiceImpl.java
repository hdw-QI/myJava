package transaction.annotation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transaction.annotation.dao.MoneyDao;
import transaction.annotation.service.MoneyService;

/**
 * @projectName: git
 * @package: transaction.xml.service.impl
 * @className: MoneyServiceImpl
 * @author: 胡代伟
 * @description: MoneyServiceImpl
 * @date: 2023/12/22 15:59
 * @version: 1.0
 */
@Service
public class MoneyServiceImpl implements MoneyService {
    @Autowired
    MoneyDao moneyDao;


    @Override
    @Transactional
    public void accountSalary(int lessenId, int addId, double money) {
        //某个账号减少金额
        moneyDao.reduceMoney(lessenId, money);
        //制造异常
        int i = 1 / 0;
        //走个账号增加金额
        moneyDao.addMoney(addId, money);
    }
}

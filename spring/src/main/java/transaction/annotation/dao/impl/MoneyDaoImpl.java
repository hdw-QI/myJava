package transaction.annotation.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import transaction.annotation.dao.MoneyDao;

/**
 * @projectName: git
 * @package: transaction.xml.dao.impl
 * @className: MoneyDaoImpl
 * @author: 胡代伟
 * @description: MoneyDaoImpl
 * @date: 2023/12/22 16:24
 * @version: 1.0
 */
@Repository
public class MoneyDaoImpl implements MoneyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void reduceMoney(int id, double balance) {
        jdbcTemplate.update("update salary set money= money - ? where id=?",balance,id);
    }

    @Override
    public void addMoney(int id, double balance) {
        jdbcTemplate.update("update salary set money=money + ? where id=?",balance,id);
    }
}

package md5.domain.entity;

import utils.db.annotation.Id;
import utils.db.annotation.Table;

/**
 * @projectName: git
 * @package: md5.domain.entity
 * @className: Md5User
 * @author: 胡代伟
 * @description: Md5User
 * @date: 2023/12/5 13:07
 * @version: 1.0
 */
@Table("md5_user")
public class Md5User {

    @Id("id")
    private int id;
    private String userName;
    private String password;
    private String salt;



    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getSalt() {
        return salt;
    }


}

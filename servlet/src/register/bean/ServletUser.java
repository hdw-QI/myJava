package register.bean;

/**
 * @projectName: git
 * @package: register.bean
 * @className: ServletUser
 * @author: 胡代伟
 * @description: 用户
 * @date: 2023/11/28 18:03
 * @version: 1.0
 */
public class ServletUser {
    private int id;
    private String username;
    private String password;
    private String email;



    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

}

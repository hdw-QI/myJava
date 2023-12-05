package md5.domain.dto;

/**
 * @projectName: git
 * @package: md5.domain.dto
 * @className: LoginDto
 * @author: 胡代伟
 * @description: LoginDto
 * @date: 2023/12/5 13:12
 * @version: 1.0
 */
public class LoginDto {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

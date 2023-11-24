package utils.db.annotation.bean;

/**
 * @projectName: scond_stage
 * @package: utils.db.annotation.bean
 * @className: ConfigConnectionPara
 * @author: 胡代伟
 * @description: 连接数据库参数
 * @date: 2023/11/8 19:52
 * @version: 1.0
 */
public class ConfigConnectionParameters {
    private String driver;
    private String url;
    private String username;
    private String password;

    public ConfigConnectionParameters() {
    }

    public ConfigConnectionParameters(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

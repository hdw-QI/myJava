package utils.db.annotation.service.impl;

import utils.db.annotation.bean.ConfigConnectionParameters;
import utils.db.annotation.service.ConnectionParametersInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @projectName: scond_stage
 * @package: utils.db.annotation.service.impl
 * @className: ConnectionParametersInfoImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/8 20:21
 * @version: 1.0
 */
public class ConnectionParametersInfoImpl implements ConnectionParametersInfo {

    @Override
    public ConfigConnectionParameters getInfoByProperties(String propertiesPath) {
        InputStream resourceAsStream = ConnectionParametersInfoImpl.class.getClassLoader().getResourceAsStream(propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return new ConfigConnectionParameters(driver,url,username,password);
    }

    // todo
    @Override
    public ConfigConnectionParameters getInfoByXml(String xmlPath) {
        return null;
    }
}

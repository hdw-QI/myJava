package utils.db.annotation.apt;

import utils.db.annotation.bean.ConfigConnectionParameters;
import utils.db.annotation.service.ConnectionParametersInfo;
import utils.db.annotation.service.impl.ConnectionParametersInfoImpl;

/**
 * @projectName: scond_stage
 * @package: utils.db.annotation
 * @className: PropertiesConfigPathAPT
 * @author: 胡代伟
 * @description:处理ConfigFilePath注解
 * @date: 2023/11/8 19:48
 * @version: 1.0
 */
public class ConfigFilePathAPT {
    /**
     * @param :
     * @return ConfigConnectionParameters
     * @author 胡代伟
     * @description 如果有@ConfigFilePath，则可以调用这个方法获取连接信息
     * @date 2023/11/8 20:14
     */
    public static ConfigConnectionParameters getPropertiesConfig(String propertiesPath){
        ConnectionParametersInfo connectionParametersInfo = new ConnectionParametersInfoImpl();
        if(propertiesPath.split("\\.")[1].equals("properties")){
            return connectionParametersInfo.getInfoByProperties(propertiesPath);
        }else if (propertiesPath.split("\\.")[1].equals("xml")){
            return connectionParametersInfo.getInfoByXml(propertiesPath);
        }else {
            throw new RuntimeException("文件类型错误！");
        }

    }
}

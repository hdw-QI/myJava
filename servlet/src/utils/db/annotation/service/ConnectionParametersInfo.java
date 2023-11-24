package utils.db.annotation.service;

import utils.db.annotation.bean.ConfigConnectionParameters;

/**
 * @projectName: scond_stage
 * @package: utils.db.annotation.service
 * @className: ConfigParametersInfo
 * @author: 胡代伟
 * @description: 和连接参数信息相关的父接口
 * @date: 2023/11/8 20:18
 * @version: 1.0
 */
public interface ConnectionParametersInfo {
    /**
     * @param propertiesPath:
     * @return ConfigConnectionParameters
     * @author 胡代伟
     * @description 通过properties文件的路径获取连接参数信息
     * @date 2023/11/9 8:32
     */
    public ConfigConnectionParameters getInfoByProperties(String propertiesPath);

    /**
     * @param xmlPath:
     * @return ConfigConnectionParameters
     * @author 胡代伟
     * @description 通过xml文件的路径获取连接参数信息
     * @date 2023/11/9 8:33
     */
    public ConfigConnectionParameters getInfoByXml(String xmlPath);
}

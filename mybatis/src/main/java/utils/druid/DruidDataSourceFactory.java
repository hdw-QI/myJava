package utils.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;

/**
 * @projectName: git
 * @package: utils.druid
 * @className: DruidDataSourceFactory
 * @author: 胡代伟
 * @description: mybatis使用druid连接池工具
 * @date: 2023/12/7 10:05
 * @version: 1.0
 */
public class DruidDataSourceFactory extends PooledDataSourceFactory {
    public DruidDataSourceFactory(){
        this.dataSource = new DruidDataSource();
    }
}

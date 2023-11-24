package utils.annotationScanner.result;

import java.util.List;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner.result
 * @className: ResultManager
 * @author: 胡代伟
 * @description: 获取扫描的结果
 * @date: 2023/11/23 18:05
 * @version: 1.0
 */
public interface ResultManager<T> {
    /**
     * @param packageName: 要扫描的全包名
     * @param annotationClass: 要扫描的注解class
     * @return List<String>
     * @author 胡代伟
     * @description 获取包含指定注解的全包名(用于不是多模块下)
     * @date 2023/11/23 18:27
     */
    public List<String> getPackageJavaFileList(String packageName,Class<T> annotationClass);

    public List<String> getPackageJavaFileListManyModel(String packageName,Class<T> annotationClass);
}

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
     * @description 获取包含指定注解的全包名
     * @date 2023/11/23 18:27
     */
    public List<String> getPackageJavaFileList(String packageName,Class<T> annotationClass);
    /**
     * @param packageName: 全包名
     * @param annotationClass: 要扫描的注解class
     * @return List<String>
     * @author 胡代伟
     * @description 在多模块项目中获取包含指定注解的全包名
     * @date 2023/11/28 16:00
     */
    public List<String> getPackageJavaFileListInManyModel(String packageName,Class<T> annotationClass);

    /**
     * @param packageName:
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 通过class文件获取包含指定注解的全包名
     * @date 2023/11/30 18:43
     */
    public List<String> getPackageClassFileList(String packageName,Class<T> annotationClass);
}

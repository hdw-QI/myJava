package utils.annotationScanner.annotation.apt;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner.annotation.apt
 * @className: ScannerApt
 * @author: 胡代伟
 * @description: 处理Scanner注解
 * @date: 2023/11/23 17:46
 * @version: 1.0
 */
public interface ScannerApt {
    /**
     * @param className: 调用scanner方法的类的全类名
     * @return String 相对于src的路径,且在src下的一个全包名
     * @author 胡代伟
     * @description 获取注解中的值
     * @date 2023/11/23 18:00
     */
    public String getPackageName(String className);
}

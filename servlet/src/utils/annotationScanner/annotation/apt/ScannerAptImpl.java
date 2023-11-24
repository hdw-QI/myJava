package utils.annotationScanner.annotation.apt;

import utils.annotationScanner.annotation.Scanner;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner.annotation.apt
 * @className: ScannerAptImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/23 17:48
 * @version: 1.0
 */
public class ScannerAptImpl implements ScannerApt{
    @Override
    public String getPackageName(String className) {
        String packageName;
        // 查看这个类是否有Scanner注解
        try {
            Class<?> aClass = Class.forName(className);
            Scanner annotation = aClass.getAnnotation(Scanner.class);
            if (annotation == null) {
                throw new RuntimeException("未指定扫描的包");
            }

            packageName=annotation.value();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return packageName;
    }
}

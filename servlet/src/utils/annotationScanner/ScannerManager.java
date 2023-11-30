package utils.annotationScanner;

import utils.annotationScanner.annotation.apt.ScannerApt;
import utils.annotationScanner.annotation.apt.ScannerAptImpl;
import utils.annotationScanner.result.ResultManager;
import utils.annotationScanner.result.ResultManagerImpl;

import java.util.List;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner
 * @className: ScannerManager
 * @author: 胡代伟
 * @description: 扫描管理器：开启扫描
 * @date: 2023/11/23 17:37
 * @version: 1.0
 */
public class ScannerManager<T>{
    ScannerApt scannerApt = new ScannerAptImpl();
    // 获取是哪个类在调用这个方法
    String className = new Exception().getStackTrace()[1].getClassName();
    String packageName = scannerApt.getPackageName(className);
    ResultManager<T> tResultManager = new ResultManagerImpl<>();
    /**
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 单模块项目中
     * @date 2023/11/30 18:48
     */
    public  List<String> scannerInOneModel(Class<T> annotationClass) {
        return tResultManager.getPackageJavaFileList(packageName, annotationClass);
    }

    /**
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 多模块项目在
     * @date 2023/11/30 18:49
     */
    public  List<String> scannerInManyModel(Class<T> annotationClass) {
        return tResultManager.getPackageJavaFileListInManyModel(packageName, annotationClass);
    }

    /**
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 万能
     * @date 2023/11/30 18:49
     */
    public  List<String> scanner(Class<T> annotationClass) {
        return tResultManager.getPackageClassFileList(packageName, annotationClass);
    }
}

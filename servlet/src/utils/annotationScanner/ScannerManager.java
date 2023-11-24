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
    ResultManager<T> tResultManager = new ResultManagerImpl<>();
    String className = new Exception().getStackTrace()[1].getClassName();
    String packageName = scannerApt.getPackageName(className);
    /**
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 工具类所在的模块就是父模块的扫描
     * @date 2023/11/23 20:36
     */
    public  List<String> scanner(Class<T> annotationClass) {
        return tResultManager.getPackageJavaFileList(packageName, annotationClass);
    }
    /**
     * @param annotationClass:
     * @return List<String>
     * @author 胡代伟
     * @description 工具类所在的模块是父模块下的其中一个模块的扫描
     * @date 2023/11/23 20:36
     */
    public  List<String> scannerInManyModel(Class<T> annotationClass) {
        return tResultManager.getPackageJavaFileListManyModel(packageName, annotationClass);
    }
}

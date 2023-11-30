package utils.annotationScanner.result;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @projectName: reflect
 * @package: utils.annotationScanner.result
 * @className: ResultManagerImpl
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/23 18:07
 * @version: 1.0
 */
public class ResultManagerImpl<T> implements ResultManager<T>{
    @Override
    public List<String> getPackageJavaFileList(String packageName, Class<T> annotationClass) {
        List<String> fileList = getFileList(packageName, ".java");
        return isHaveAnnotation(packageName,fileList,annotationClass);
    }

    @Override
    public List<String> getPackageJavaFileListInManyModel(String packageName, Class<T> annotationClass) {
        List<String> fileList = getFileListInManyModel(packageName, ".java");
        return isHaveAnnotation(packageName,fileList,annotationClass);
    }

    @Override
    public List<String> getPackageClassFileList(String packageName, Class<T> annotationClass) {
        List<String> fileList = getClassFileList(packageName, ".class");
        return isHaveAnnotation(packageName,fileList,annotationClass);
    }


    /**
     * @param packageName:要扫描的包
     * @param fileList:文件列表
     * @param annotationClass:注解的class
     * @return List<String>
     * @author 胡代伟
     * @description 获取含有指定注解的全包名
     * @date 2023/11/28 16:36
     */
    public List<String> isHaveAnnotation(String packageName,List<String> fileList,Class<T> annotationClass){
        List<String> javaFileNameList = new ArrayList<>();
        for (String fileName : fileList) {
            String substring = fileName.substring(0, fileName.indexOf("."));
            String resultPackageName=packageName +"."+ substring;
            try {
                Class<?> aClass = Class.forName(resultPackageName);

                Annotation annotation = aClass.getAnnotation((Class<? extends Annotation>) annotationClass);
                if (annotation!=null){
                    javaFileNameList.add(resultPackageName);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        return javaFileNameList;
    }

    /**
     * @param packageName: 全包名
     * @param suffixName: 文件后缀名，可为null。要加. 如：.java
     * @return List<String>
     * @author 胡代伟
     * @description 获取指定文件后缀名的文件列表。文件包含文件后缀名
     * @date 2023/11/23 18:32
     */
    public List<String> getFileList(String packageName,String suffixName){
        // 将格式转换从系统路径的格式
        String systemPageName = packageName.replace(".", "/");
        File file = new File("src/" + systemPageName);
        return getAllFile(file, suffixName);
    }

    /**
     * @param packageName: 全包名
     * @param suffixName: 文件后缀名，可为null。要加. 如：.java
     * @return List<String>
     * @author 胡代伟
     * @description 在多模块项目中获取指定文件后缀名的文件列表。文件包含文件后缀名
     * @date 2023/11/28 15:58
     */
    public List<String> getFileListInManyModel(String packageName,String suffixName){
        // 将格式转换从系统路径的格式
        String systemPageName = packageName.replace(".", "/");
        // 获取当前类的所在工程（编译）路径
        File modelFile = new File(Objects.requireNonNull(this.getClass().getResource("/")).getPath());
        // 获取当前类的所在工程的模块名
        String modelName=modelFile.getName();
        File file = new File(modelName+"/src/" + systemPageName);
        return getAllFile(file, suffixName);
    }
    public List<String> getClassFileList(String packageName,String suffixName){
        // 将格式转换从系统路径的格式
        String systemPageName = packageName.replace(".", "\\");
        // 获取当前类的所在工程（编译）路径
        File modelFile = new File(Objects.requireNonNull(this.getClass().getResource("/")).getPath());
//        System.out.println(modelFile.getPath());
        System.out.println(modelFile.getPath()+"\\" + systemPageName);
        File file = new File(modelFile.getPath()+"\\" + systemPageName);
//        System.out.println(file.exists());
        return getAllFile(file, suffixName);
    }
    /**
     * @param dir: file对象
     * @param suffixName: 文件后缀名
     * @return void
     * @author 胡代伟
     * @description 获取该文件下指定类型的所有文件
     * @date 2023/11/23 19:24
     */
    public  List<String> getAllFile(File dir,String suffixName){
        List<String> result = new ArrayList<>();
        File[] files = dir.listFiles((File pathname)->{
            if (suffixName != null) {
                return pathname.getName().toLowerCase().endsWith(suffixName);
            }else {
                return true;
            }
        });
        if (files != null) {
            for (File f : files) {
                //对遍历得到的File对象f进行判断,判断是否是文件夹
                if(!f.isDirectory()){
                    // 只扫描指定的文件夹，不扫描下面的文件夹
                    result.add(f.getName());
                }
            }
        }
        return result;
    }
}

注解扫描器
1、一个注解告诉我要扫描的包
2、扫描器返回有指定注解的类的全类名

注意：只扫描指定的文件夹，不扫描下面的文件夹

Scanner注解用于类上（在调用扫描方法的类的类上），用于标注要扫描那个包

ScannerManager<T>:扫描管理器：开启扫描
通过ScannerManager对象使用以下两个方法：
①scanner：获取包含指定注解的全包名
②scannerInManyModel：在多模块项目中获取包含指定注解的全包名

使用示例
```java
@Scanner("test")
public class Test {
    public static void main(String[] args) {
        List<String> scanner = new ScannerManager<TestAnnotation>().scanner(TestAnnotation.class);
        System.out.println(scanner);
    }
}
```

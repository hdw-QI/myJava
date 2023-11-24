package bean;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @projectName: three stage
 * @package: bean
 * @className: CreateBean
 * @author: 胡代伟
 * @description: TODO
 * @date: 2023/11/23 13:00
 * @version: 1.0
 */
public class CreateBean {
    public static void main(String[] args) {
        System.out.println(getInfo("bean.xml"));
        List<Map<String, Object>> info = getInfo("bean.xml");
        List<Object> objects = getUser(info);
        for (Object obj : objects) {
            System.out.println(obj);
        }
    }

    public static List<Map<String, Object>> getInfo(String path){
        List<Map<String, Object>> maps = new ArrayList<>();
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(path);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(systemResourceAsStream);
            Element rootElement = document.getRootElement();
            List<Element> bean = rootElement.elements("bean");
            for (Element element : bean) {
                HashMap<String, Object> map = new HashMap<>();
                Attribute aClass = element.attribute("class");
                String className=aClass.getValue();
                map.put("className", className);
                List<Element> property = element.elements("property");
                for (Element element1 : property) {
                    String name = element1.attribute("name").getValue();
                    String value = element1.attribute("value").getValue();
                    map.put(name, value);
                }
                maps.add(map);
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return maps;
    }
    public static List<Object> getUser(List<Map<String, Object>> info) {
        ArrayList<Object> objects = new ArrayList<>();
        for (Map<String, Object> map : info) {
            try {
                Class<?> aClass = Class.forName(map.get("className").toString());
                Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
                Object obj = declaredConstructor.newInstance();
                Set<String> strings = map.keySet();
                for (String string : strings) {
                    if (!string.equals("className")){
                        Field declaredField = aClass.getDeclaredField(string);
                        declaredField.setAccessible(true);
                        Object value ;
                        if (declaredField.getType()==int.class){
                            value = Integer.parseInt(map.get(string).toString());
                        }else {
                            value=map.get(string);
                        }

                        declaredField.set(obj, value);
                    }
                }
                objects.add(obj);

            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }
}

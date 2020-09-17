package com.moonzero.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 获取对象的工厂类
 *
 * @author MoonZero
 */
public class BeanFactory {
    // 通过一个全局变量接收解析后的xml信息,使用map取值比较方便
    private static Map<String, Object> beans = new HashMap<String, Object>();

    // 使用静态代码块在，在创建类的时候读取xml配置文件
    static {
        // 获取dom4j的SAXReader对象
        SAXReader sax = new SAXReader();
        try {
            // 获取Document对象
            Document document = sax.read(BeanFactory.class.getResourceAsStream("/mySpring.xml"));
            // 获取根标签
            Element root = document.getRootElement();
            // 获取子标签的集合
            @SuppressWarnings("unchecked")
            List<Element> elements = root.elements();
            // 遍历集合
            for (Element e : elements) {
                String id = e.attributeValue("id");
                String className = e.attributeValue("class");
                // 使用反射创建对象
                Object bean = Class.forName(className).newInstance();
                // 将id和对象放到全局变量map集合中
                beans.put(id, bean);
            }
            System.out.println(beans);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取反射创建的对象
     *
     * @param id
     * @return Object
     */
    public static Object getBean(String id) {
        return beans.get(id);
    }

}

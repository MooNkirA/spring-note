package com.moon.springsample.typefilter;

import com.moon.springsample.annotations.Scene;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;

import java.util.Properties;

/**
 * spring的自定义扫描规则 - 实现不同场景下不同实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-8-28 22:28
 * @description
 */
public class SceneTypeFilter extends AbstractTypeHierarchyTraversingFilter {

    // 定义路径校验类对象（Spring框架提供的工具类）
    private PathMatcher pathMatcher;

    /*
     *  定义场景名称，此值应该是通过配置去修改，不能使用硬编码方式
     *      需要注意：这里使用 @Value 注解的方式是获取不到配置值，
     *      因为Spring的生命周期里，负责填充属性值的 InstantiationAwareBeanPostProcessor 与 TypeFilter 的实例化过程两者没有任何关系
     */
    // @Value("${common.scene.name}")
    private String sceneName;

    /**
     * 定义构造函数，因为父类没有无参构造函数，所以必须要定义构造函数并调用父类的构造器
     */
    public SceneTypeFilter() {
        /*
         * 调用父类构造函数
         *  第1个参数considerInherited: 不考虑基类
         *  第2个参数considerInterfaces: 不考虑接口上的信息
         */
        super(false, false);

        // 借助Spring默认的Resource通配符路径方式
        this.pathMatcher = new AntPathMatcher();

        // 此处使用硬编码读取配置信息（实现应用时再想使用其他方式实现）
        try {
            // 使用spring工具类PropertiesLoaderUtils，读取根目录下的配置文件
            Properties properties = PropertiesLoaderUtils.loadAllProperties("scene.properties");
            // 读取配置文件中的场景名称
            this.sceneName = properties.getProperty("common.scene.name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写父类方法，注意此方法的作用是，将指定的类将注册为Exclude（排除过滤）, 返回
     *
     * @param className 校验的类的名称
     * @return true 代表排除此类，不加入到 spring 容器中；false 代表不排除
     */
    @Override
    protected boolean matchClassName(String className) {
        try {
            /*
             * 判断当前传入的类的名称（className）是否在指定的包路径上的类（即只处理本案例中相关的不场景的业务类）
             */
            if (!isPotentialPackageClass(className)) {
                // 类路径不符合本过滤器定义的扫描路径规则，即表示此类不需要排除，直接返回false
                return false;
            }

            /* 以上逻辑是：判断当前类上标识的自定义场景注解是否与配置文件中的场景名称一致，如不一致，则排除，不能注册到spring容器中 */
            // 根据类名称获取字节码对象
            Class<?> clazz = ClassUtils.forName(className, SceneTypeFilter.class.getClassLoader());
            // 通过反射获取当前类上的 @Scene 注解对象
            Scene scene = clazz.getAnnotation(Scene.class);
            // 判断此类上是否有 @Scene 注解
            if (scene == null) {
                // 无标识此注解，不需要排除
                return false;
            }

            // 获取标识 @Scene 注解中的value值
            String sceneValue = scene.value();

            // 校验，如果此类上标识的value属性值与配置文件中场景名称一致，则注册到spring ioc 容器中（即返回false）。排除则返回true
            return !this.sceneName.equalsIgnoreCase(sceneValue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
     * 定义潜在的满足条件的类的名称（本类逻辑能处理的类）, 指定在哪个 package下
     *  这里为了实现可以支持通配符方式的，借用spring框架中 ClassUtils 工具类
     */
    private static final String PATTERN_STANDARD =
            ClassUtils.convertClassNameToResourcePath("com.moon.springsample.service.impl.*.*");

    /**
     * 判断当前传入的类名称，是否在本类（本过滤规则）逻辑中可以处理的类
     *
     * @param className 待判断类的名称
     * @return true: 表示可以处理
     */
    private boolean isPotentialPackageClass(String className) {
        // 1. 将类名转换为资源路径, 以进行匹配测试
        String resourcePath = ClassUtils.convertClassNameToResourcePath(className);

        // 2. 使用工具类对资源的路径进行匹配校验
        return pathMatcher.match(PATTERN_STANDARD, resourcePath);
    }

}

package com.moon.spring.registrar;

import com.moon.spring.bean.Chocolate;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * DeferredImportSelector 接口实现示例
 * <p>
 * 具体方法的时序是：
 * DeferredImportSelector.getImportGroup.getImportGroup
 * --> DeferredImportSelector.Group.process
 * --> DeferredImportSelector.Group.selectImports
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-16 23:04
 * @description
 */
public class DeferredImportSelectorDemo implements DeferredImportSelector {

    /**
     * 此方法是DeferredImportSelector继承了父接口ImportSelector。
     * 此方法不会被调用，只能是通过手动调用
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("DeferredImportSelectorDemo.selectImports()方法执行了....");
        // 返回需要实例化的类全限定名数组
        return new String[]{Chocolate.class.getName()};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }

    /**
     * 返回一个实现了DeferredImportSelector.Group接口的类的Class对象
     *
     * @return
     */
    @Override
    public Class<? extends Group> getImportGroup() {
        return DeferredImportSelectorGroupImpl.class;
    }

    /**
     * 内部类，实现了DeferredImportSelector.Group接口
     */
    private static class DeferredImportSelectorGroupImpl implements DeferredImportSelector.Group {

        private List<Entry> list = new ArrayList<>();

        /**
         * 处理流程，收集需要加入到Spring容器实例化的类
         *
         * @param metadata 使用@Import注解导入的类的所有注解元数据
         * @param selector 外部类 DeferredImportSelectorDemo 实例
         */
        @Override
        public void process(AnnotationMetadata metadata, DeferredImportSelector selector) {
            System.out.println("DeferredImportSelectorGroupImpl.process()方法执行了....");
            // 调用外部类方法，将selectImports方法，获取要创建的类集合
            for (String s : selector.selectImports(metadata)) {
                // 创建Entry对象增加到内部属性集合中
                list.add(new Entry(metadata, s));
            }
        }

        /**
         * 将process收集到的数据(封装成Group.Entry对象)，返回
         *
         * @return
         */
        @Override
        public Iterable<Entry> selectImports() {
            System.out.println("DeferredImportSelectorGroupImpl.selectImports()方法执行了....");
            // 返回process方法收集的数据
            return this.list;
        }
    }

}

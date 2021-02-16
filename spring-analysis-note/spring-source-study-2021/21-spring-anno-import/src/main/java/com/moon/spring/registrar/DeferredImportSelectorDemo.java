package com.moon.spring.registrar;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * DeferredImportSelector 接口实现示例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-2-16 23:04
 * @description
 */
public class DeferredImportSelectorDemo implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[0];
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }

    @Override
    public Class<? extends Group> getImportGroup() {
        return null;
    }

}

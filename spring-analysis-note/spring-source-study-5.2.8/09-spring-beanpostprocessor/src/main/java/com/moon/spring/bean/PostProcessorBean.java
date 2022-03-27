package com.moon.spring.bean;

import com.moon.spring.component.ComponentA;
import com.moon.spring.component.ComponentB;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试 BeanPostProcessor 素材
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-31 11:38
 * @description
 */
@Data
@Component
public class PostProcessorBean {

    @Autowired
    private ComponentA componentA;
    @Autowired
    private ComponentB componentB;

}

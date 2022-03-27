package com.moon.spring.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用于测试通过 BeanDefinitionRegistryPostProcessor 移除原注册到容器的 BeanDefinition
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-13 22:45
 * @description
 */
@Data
@Component
public class BeanToDelete {

    private String value = "我被手动删除了";

}

package com.moon.spring.test;

import com.moon.spring.bean.Fish;
import com.moon.spring.component.AutowiredConstructorComponent;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Spring @Autowired 测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-1-24 17:16
 * @description
 */
public class AutowiredTest {

    private final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("com.moon.spring");

    @Test
    public void testAutowiredConstructor() {
        Map<String, AutowiredConstructorComponent> beansOfType = context.getBeansOfType(AutowiredConstructorComponent.class);
        for (Map.Entry<String, AutowiredConstructorComponent> entry : beansOfType.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }

    private final List<Function<Map<String, Object>, Boolean>> checkList = new ArrayList<>();

    @Before
    public void init() {
        checkList.add(map -> StringUtils.isEmpty(map.get("a")) && setMessage(map, "a为空"));
        checkList.add(map -> map.get("b") == null || Integer.parseInt(map.get("b").toString()) < 1 && setMessage(map, "b不合法"));
        checkList.add(map -> {
            Object c = map.get("c");
            return c == null || "".equals(c.toString().trim()) && setMessage(map, "c为空");
        });
        checkList.add(map -> map.get("d") == null && setMessage(map, "d为空"));
    }

    private boolean setMessage(Map<String, Object> map, String msg) {
        map.put("retMsg", msg);
        return true;
    }

    private int check(Map<String, Object> map) {
        for (Function<Map<String, Object>, Boolean> function : checkList) {
            Boolean bool = function.apply(map);
            System.out.println(bool);
            if (bool) {
                return 1;
            }
        }
        return 0;
    }

    @Test
    public void workTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("retCode", "0");
        map.put("retMsg", "");
        map.put("a", "1");
        map.put("b", 1);
        map.put("c", "   ");
        map.put("d", new Fish());

        System.out.println(check(map));
        System.out.println(map);
    }

}

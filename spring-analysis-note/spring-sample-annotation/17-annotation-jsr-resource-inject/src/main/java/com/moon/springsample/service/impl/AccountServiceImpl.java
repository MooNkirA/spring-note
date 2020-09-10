package com.moon.springsample.service.impl;

import com.moon.springsample.dao.AccountDao;
import com.moon.springsample.service.AccountService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 模拟账号业务接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-2 14:08
 * @description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    /* 实现依赖注入方式一：使用@Autowired按类型自动注入 */
    // @Autowired
    // @Qualifier("accountDaoImplOne") // 因为AccountDao的两个实现存入ioc容器，此时会报错。需要配置@Qualifier注解指定bean对象id来实现自动注入

    /* 实现依赖注入方式二：使用@Resource注解 */
    // @Resource // 1. 如果不设置任何属性，默认是按类型去匹配注入，其结果与@Autowired注解是一样，报存在两个相同类型的bean实例的错误
    // @Resource(name = "accountDaoImplTwo") // 2. 设置name属性，按bean实例的名称去匹配，匹配成功则注入，否则报错
    // @Resource(type = AccountDao.class) // 3. 设置type属性，按指定的类型去匹配，如果匹配到唯一对象实例时则成功注入，否则报错
    // @Resource(type = AccountDao.class, name = "accountDaoImplThree") // 4. 同时设置name属性与type属性，按指定类型与bean实例的名称同时匹配，匹配成功则注入，否则报错

    /* 实现依赖注入方式三：使用@Inject注解 */
    // @Inject // @Inject注解没有任何属性，只能按类型匹配注入，如果容器中存在多个同类型的bean实例，此时会报错
    // @Named("accountDaoImplTwo") // 配合JSR-330规范中的@Named注解使用之后，可以变成根据bean名称（byName）去匹配注入。(注：@Named不能脱离@Inject单独使用)
    // @Qualifier("accountDaoImplOne") // @Inject也可以配合 @Qualifier 注解指定bean对象id来实现按名称（byName）自动注入（注：同理@Autowired与@Named也可以配合使用）
    private AccountDao accountDao;

    /* 实现依赖注入方式三：使用@Inject注解（标识在构造方法上） */
    @Inject // 此时要求容器中必须有AccountDao对象，但是需要注意的是：因为JRE无法决定构造方法注入的优先级，所以规范中规定类中只能有一个构造方法标识`@Inject`注解
    @Named("accountDaoImplOne")
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void save() {
        accountDao.save();
    }

}

package com.moon.springsample.listener;

import com.moon.springsample.event.MyApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;

/**
 * 事务的事件监听器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-16 14:11
 * @description
 */
@Component
public class MyTransactionalEventListener {

    /**
     * 当事务提交之后执行
     *
     * @param event
     */
    /* phase属性是定义方法的执行时机，TransactionPhase.AFTER_COMMIT为默认值，不设置也一样的效果 */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void doSomethingAfterCommit(MyApplicationEvent event) {
        // 1. 从事件对象中获取事件源
        Map map = (Map) event.getSource();
        // 2. 获取相关信息
        Object sourceName = map.get("sourceName");
        Object targetName = map.get("targetName");
        Object money = map.get("money");
        // 3. 输出
        System.out.println("事务提交了，" + sourceName + "给" + targetName + "转了" + String.valueOf(money) + "钱！转账成功");
    }

    /**
     * 当事务回滚之后执行
     *
     * @param event
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void doSomethingAfterRollback(MyApplicationEvent event) {
        // 1. 从事件对象中获取事件源
        Map map = (Map) event.getSource();
        // 2. 获取相关信息
        Object sourceName = map.get("sourceName");
        Object targetName = map.get("targetName");
        Object money = map.get("money");
        // 3. 输出
        System.out.println("事务回滚了，" + sourceName + "给" + targetName + "转了" + String.valueOf(money) + "钱！转账失败");
    }

}

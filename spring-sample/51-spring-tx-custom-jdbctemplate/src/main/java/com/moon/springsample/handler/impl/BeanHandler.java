package com.moon.springsample.handler.impl;

import com.moon.springsample.handler.ResultSetHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 单个对象结果处理器实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-15 13:57
 * @description
 */
public class BeanHandler<T> implements ResultSetHandler {

    // 定义数据封装实体类型字节码对象
    private final Class<T> requiredType;

    // 定义有参构造函数，覆盖默认无参构造。当创建BeanHandler对象时，就需要提供封装到的实体类类型字节码
    public BeanHandler(Class<T> requiredType) {
        this.requiredType = requiredType;
    }

    @Override
    public Object handler(ResultSet resultSet) {
        // 1. 定义返回值
        T bean = null;
        try {
            // 2. 判断结果集有值（由于是查询一个，所以只需判断rs能往下走，不用while循环即可）
            if (resultSet.next()) {
                // 3. 实例化返回值对象
                bean = requiredType.newInstance();
                // 4. 获取结果集的元信息
                ResultSetMetaData metaData = resultSet.getMetaData();
                // 5. 获取结果集的列数
                int columnCount = metaData.getColumnCount();
                // 6. 遍历结果集列个数
                for (int i = 0; i < columnCount; i++) {
                    // 7. 取出列的标题
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    // 8. 根据当前列标题，获取对应的数据内容
                    Object value = resultSet.getObject(columnLabel);
                    // 9. 借助java的内省机制，创建实体类的属性描述器，使用内省填充对象数据
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnLabel, requiredType);
                    // 10. 获取属性的写方法
                    Method method = propertyDescriptor.getWriteMethod();
                    // 11. 执行方法，填充数据
                    method.invoke(bean, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回封装后对象
        return bean;
    }

}

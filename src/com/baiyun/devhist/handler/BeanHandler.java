package com.baiyun.devhist.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 实体处理
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:20
 **/
public class BeanHandler<T>  implements ResultSetHandler<T>{

    /**
     * 结果集对象类
     */
    private Class<T> clz;

    public BeanHandler(Class<T> clz) {
        this.clz = clz;
    }

    /**
     * 结果集处理
     *
     * @param res 结果集
     * @return T
     * @throws Exception
     */
    @Override
    public T handle(ResultSet res) throws Exception {
        if (res.next()) {
            // 字节码创建对象
            T obj = this.clz.newInstance();
            // 获取指定字节码信息
            BeanInfo beanInfo = Introspector.getBeanInfo( this.clz, Object.class);
            // 获取所有属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                // 获取结果集中的属性值
                Object val = res.getObject(pd.getName());
                // 设置属性信息
                pd.getWriteMethod().invoke(obj, val);
            }
            return obj;
        }
        return null;
    }
}

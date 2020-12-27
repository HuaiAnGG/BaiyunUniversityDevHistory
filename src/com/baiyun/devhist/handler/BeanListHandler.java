package com.baiyun.devhist.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 实体列表处理器
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:22
 **/
public class BeanListHandler<T> implements ResultSetHandler<List<T>> {

    private Class<T> clz;

    public BeanListHandler(Class<T> clz) {
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
    public List<T> handle(ResultSet res) throws Exception {
        List<T> list = new ArrayList<>();
        while (res.next()) {
            T obj = this.clz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(this.clz, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                Object val = res.getObject(pd.getName());
                pd.getWriteMethod().invoke(obj, val);
            }
            list.add(obj);
        }
        return list;
    }
}

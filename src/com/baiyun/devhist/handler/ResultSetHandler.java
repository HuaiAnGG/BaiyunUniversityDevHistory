package com.baiyun.devhist.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * @program: BaiyunUniversityDevHistory
 * @description: 结果集处理器
 * @author: HuaiAnGG
 * @create: 2020-12-23 13:19
 **/
public interface ResultSetHandler<T> {
    /**
     * 结果集处理
     * @param res 结果集
     * @return T
     * @throws Exception
     */
    T handle(ResultSet res) throws Exception;
}

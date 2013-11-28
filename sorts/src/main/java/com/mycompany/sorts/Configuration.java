/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sorts;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Pierre
 */
public class Configuration {

    private final Map<String, Object> data = new LinkedHashMap<>();

    Object put(String propertyName, Object value) {
        return data.put(propertyName, value);
    }

    <T> T configure(T object) {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                final Object value = data.get(pd.getName());
                if (value != null) {
                    pd.getWriteMethod().invoke(object, value);
                }
            }
        } catch (IntrospectionException | ReflectiveOperationException | IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        return object;
    }

    void writeTo(Report report) {
        for(Entry<String,Object> e:data.entrySet()){
            report.write(e.getKey(), e.getValue());
        }
    }

}

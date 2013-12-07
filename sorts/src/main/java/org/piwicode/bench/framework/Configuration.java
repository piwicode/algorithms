/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import com.google.common.collect.ImmutableMap;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map.Entry;

/**
 *
 * @author Pierre
 */
class Configuration {

    private final ImmutableMap<String, Object> data;

    private Configuration(ImmutableMap<String, Object> in) {
        data = ImmutableMap.copyOf(in);
    }

    <T> T configure(T object) {
        try {
            final BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                final Object value = data.get(pd.getName());
                if (value != null && pd.getWriteMethod() != null) {
                    pd.getWriteMethod().invoke(object, value);
                }
            }
        } catch (IntrospectionException | ReflectiveOperationException | IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        }
        return object;
    }

    void writeTo(Report report) {
        for (Entry<String, Object> e : data.entrySet()) {
            report.write(e.getKey(), e.getValue());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Entry<String, Object> e : data.entrySet()) {
            if (sb.length() != 0) {
                sb.append(" - ");
            }
            sb.append(e.getKey()).append(":").append(e.getValue());
        }
        return sb.toString();
    }

    Object spawn() {
        try {
            final Class clazz = (Class) data.get("class");
            final Object bench = clazz.newInstance();
            return configure(bench);
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Configuration create(Iterable<Entry<String, Object>> entries) {
        ImmutableMap.Builder<String, Object> builder = new ImmutableMap.Builder<>();
        for (Entry<String, Object> entry : entries) {
            builder.put(entry);
        }
        return new Configuration(builder.build());
    }
}

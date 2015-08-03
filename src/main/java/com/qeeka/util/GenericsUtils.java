package com.qeeka.util;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Neal on 8/3 0003.
 */
public class GenericsUtils {
    public static Class getSuperClassGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("You index " + (index < 0 ? "less 0" : "pass parameter total"));
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public static Class getSuperClassGenericType(Class clazz) {
        //if zero , get first
        return getSuperClassGenericType(clazz, 0);
    }

    public static String getEntityName(Class clazz) {
        Class superClassGenericType = getSuperClassGenericType(clazz, 0);
        String simpleName = superClassGenericType.getSimpleName();
        Annotation annotation = superClassGenericType.getAnnotation(Entity.class);
        if (annotation instanceof Entity) {
            Entity entity = (Entity) annotation;
            if (entity.name() != null && !"".equals(entity.name())) {
                return entity.name();
            }
        }
        throw new IllegalArgumentException("You Class Can't Support Entity Annotation!");
    }
}

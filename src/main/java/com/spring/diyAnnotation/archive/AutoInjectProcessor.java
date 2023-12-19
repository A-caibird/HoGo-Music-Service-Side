package com.spring.diyAnnotation.archive;

import org.springframework.beans.factory.annotation.Autowired;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AutoInjectProcessor {
    public static void process(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                String fieldName = field.getName();
                String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                try {
                    Method setterMethod = clazz.getDeclaredMethod(setterMethodName, field.getType());
                    setterMethod.setAccessible(true);

                    if (setterMethod.getParameterCount() == 1) {
                        setterMethod.invoke(object, field.getType().newInstance());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
package org.hillel.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class AppContext {


    private static Map<String, Object> beanStorage = new HashMap<>();
    private static Properties properties = new Properties();

    private AppContext() {
    }

    public static void load(final String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) throw new IllegalArgumentException("file name must be set");
        try (InputStream is = AppContext.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(is);
        }
    }

    public static <T> T getBean(final String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanStorage.containsKey(beanName)) return (T) beanStorage.get(beanName);
        final String property = properties.getProperty(beanName);
        if(property == null || property.isEmpty()) throw new IllegalArgumentException("bean with name " + beanName + "not found");
        final T bean = (T) Class.forName(property).newInstance();
        beanStorage.put(beanName, bean);
        return bean;
    }

}
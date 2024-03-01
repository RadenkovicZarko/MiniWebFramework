package SpringMechanism;

import anotacije.Bean;
import anotacije.Service;
import anotacije.Component;
import anotacije.Qualifier;

import java.awt.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DependencyContainer {

    private Map<String, Object> singletons = new HashMap<>();
    private Map<String, Class<?>> prototypes = new HashMap<>();
    private Map<String, Class<?>> qualifiers = new HashMap<>();


    public void scanProject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Set<Class<?>> allClasses = scanProjectForClasses("target/classes");
        for (Class<?> clazz : allClasses) {
            if (clazz.isAnnotationPresent(Bean.class)) {
                Bean beanAnnotation = clazz.getAnnotation(Bean.class);
                if ("singleton".equals(beanAnnotation.scope())) {
                    singletons.put(clazz.getName(), clazz.getDeclaredConstructor().newInstance());
                } else {
                    prototypes.put(clazz.getName(), clazz);
                }
            } else if (clazz.isAnnotationPresent(Service.class)) {
                singletons.put(clazz.getName(), clazz.getDeclaredConstructor().newInstance());
            } else if (clazz.isAnnotationPresent(Component.class)) {
                prototypes.put(clazz.getName(), clazz);
            }

            if (clazz.isAnnotationPresent(Qualifier.class)) {
                Qualifier qualifierAnnotation = clazz.getAnnotation(Qualifier.class);
                String value = qualifierAnnotation.value();
                qualifiers.put(value, clazz);
            }
        }
    }
    public static Set<Class<?>> scanProjectForClasses(String pathToFirstPackage) {
        File packageDir = new File(pathToFirstPackage);
        return scanPackage("", packageDir);
    }

    private static Set<Class<?>> scanPackage(String packageName, File packageDir) {
        Set<Class<?>> classes = new HashSet<>();
        for (File file : packageDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    classes.add(clazz);
                } catch (ClassNotFoundException e) {
                }
            } else if (file.isDirectory()) {
                String subPackageName = packageName + "." + file.getName();
                File subPackageDir = new File(file.getAbsolutePath());
                classes.addAll(scanPackage(subPackageName, subPackageDir));
            }
        }
        return classes;
    }

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    public void setSingletons(Map<String, Object> singletons) {
        this.singletons = singletons;
    }

    public Map<String, Class<?>> getPrototypes() {
        return prototypes;
    }

    public void setPrototypes(Map<String, Class<?>> prototypes) {
        this.prototypes = prototypes;
    }

    public Map<String, Class<?>> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Map<String, Class<?>> qualifiers) {
        this.qualifiers = qualifiers;
    }

}

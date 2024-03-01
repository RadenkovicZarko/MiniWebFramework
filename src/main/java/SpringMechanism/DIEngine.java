package SpringMechanism;

import anotacije.Autowired;
import anotacije.Bean;
import anotacije.Qualifier;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DIEngine {
    private DependencyContainer container;

    public DIEngine(DependencyContainer container) {
        this.container = container;
    }



    public void initializeDependencies(Object instance) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependency = resolveDependency(field);
                    if (dependency != null) {
                        field.setAccessible(true);
                        try {
                            field.set(instance, dependency);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    private Object resolveDependency(Field field) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (field.isAnnotationPresent(Qualifier.class)) {
            Qualifier qualifier = field.getAnnotation(Qualifier.class);
            String qualifierName = qualifier.value();
            return container.getQualifiers().get(qualifierName);
        } else {
            Class<?> fieldType = field.getType();
            if(container.getSingletons().get(fieldType.getName())!=null)
                return container.getSingletons().get(fieldType.getName());

            return container.getPrototypes().get(fieldType.getName()).getDeclaredConstructor().newInstance();
        }
    }






















//    private Map<Class<?>, Object> singletonInstances = new HashMap<>();
//
//    public DIEngine(DependencyContainer dependencyContainer) {
//        this.dependencyContainer = dependencyContainer;
//    }
//
//    public void initializeDependencies(Object instance) {
//        // Get the class of the instance
//        Class<?> instanceClass = instance.getClass();
//
//        // Use reflection to inspect the fields of the instance
//        Field[] fields = instanceClass.getDeclaredFields();
//
//        for (Field field : fields) {
//            // Check if the field is annotated with @Autowired
//            if (field.isAnnotationPresent(Autowired.class)) {
//                // Resolve the concrete implementation class for the field
//                Class<?> fieldType = field.getType();
//                String qualifier = null; // Initialize the qualifier if needed
//
//                // Use the DependencyContainer to get the implementation class
//                Class<?> implementationClass = dependencyContainer.getImplementation(fieldType, qualifier);
//
//                if (implementationClass != null) {
//                    try {
//                        // Create an instance of the implementation class
//                        Object dependencyInstance = implementationClass.getDeclaredConstructor().newInstance();
//
//                        // Make the field accessible to set its value
//                        field.setAccessible(true);
//
//                        // Inject the created instance into the field
//                        field.set(instance, dependencyInstance);
//                    } catch (Exception e) {
//                        e.printStackTrace(); // Handle exceptions appropriately
//                    }
//                }
//            }
//        }
//    }
//
//    private Object createInstance(Class<?> clazz) {
//        String beanScope = getBeanScope(clazz);
//
//        if ("singleton".equalsIgnoreCase(beanScope)) {
//            // Check if an instance already exists in the singleton scope
//            Object existingInstance = singletonInstances.get(clazz);
//            if (existingInstance != null) {
//                return existingInstance; // Return the existing instance
//            }
//        }
//
//        // If no instance exists or it's a prototype scope, create a new instance
//        try {
//            Object newInstance = clazz.getDeclaredConstructor().newInstance();
//
//            if ("singleton".equalsIgnoreCase(beanScope)) {
//                // If it's a singleton, store the instance in the singleton scope
//                singletonInstances.put(clazz, newInstance);
//            }
//
//            return newInstance;
//        } catch (Exception e) {
//            e.printStackTrace(); // Handle exceptions appropriately
//            return null;
//        }
//    }
//
//
//    private String getBeanScope(Class<?> clazz) {
//        // Check if the class is annotated with @Bean
//        if (clazz.isAnnotationPresent(Bean.class)) {
//            Bean beanAnnotation = clazz.getAnnotation(Bean.class);
//            return beanAnnotation.scope().toLowerCase();
//        }
//        return "singleton";
//    }


//    private void checkAndInitializeSingletons(Class<?> clazz) {
//        if (clazz.isAnnotationPresent(Singleton.class)) {
//            // Initialize the singleton instance if not already created.
//            if (!singletonInstances.containsKey(clazz)) {
//                Object instance = createNewInstance(clazz);
//                singletonInstances.put(clazz, instance);
//            }
//        }
//    }

}

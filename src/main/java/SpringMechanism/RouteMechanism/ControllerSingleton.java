package SpringMechanism.RouteMechanism;

import anotacije.GET;
import anotacije.POST;
import anotacije.Path;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerSingleton {

    private static ControllerSingleton instance;
    private static final Map<Class<?>, Object> controllerInstances = new HashMap<>();
    public static final Map<RouteMethod, ControllerMethod> routeMappings = new HashMap<>();


    private ControllerSingleton(){}

    public static ControllerSingleton getInstance()
    {
        if(instance==null)
        {
            instance = new ControllerSingleton();
        }
        return instance;
    }

    public static <T> T getControllerInstance(Class<T> controllerClass) {
        if (!controllerInstances.containsKey(controllerClass)) {
            synchronized (ControllerSingleton.class) {
                if (!controllerInstances.containsKey(controllerClass)) {
                    try {
                        T controllerInstance = controllerClass.getDeclaredConstructor().newInstance();
                        controllerInstances.put(controllerClass, controllerInstance);
                        mapRoutesForController(controllerInstance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return controllerClass.cast(controllerInstances.get(controllerClass));
    }

    private static void mapRoutesForController(Object controllerInstance) {
        Class<?> controllerClass = controllerInstance.getClass();
        for (Method method : controllerClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(GET.class)) {
                String route = "";
                if (method.isAnnotationPresent(Path.class)) {
                    Path pathAnnotation = method.getAnnotation(Path.class);
                    route = pathAnnotation.value();
                }
                if(!route.equals(""))
                    routeMappings.put(new RouteMethod(route, HttpMethod.GET), new ControllerMethod(controllerInstance,method));
            } else if (method.isAnnotationPresent(POST.class)) {
                String route = "";
                if (method.isAnnotationPresent(Path.class)) {
                    Path pathAnnotation = method.getAnnotation(Path.class);
                    route = pathAnnotation.value();
                }
                if(!route.equals(""))
                    routeMappings.put(new RouteMethod(route,HttpMethod.POST), new ControllerMethod(controllerInstance,method));
            }
        }
    }
}

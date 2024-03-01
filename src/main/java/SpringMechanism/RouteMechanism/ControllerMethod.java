package SpringMechanism.RouteMechanism;

import java.lang.reflect.Method;

public class ControllerMethod {
    private final Object controllerInstance;
    private final Method method;

    public ControllerMethod(Object controllerInstance, Method method) {
        this.controllerInstance = controllerInstance;
        this.method = method;
    }

    public Object getControllerInstance() {
        return controllerInstance;
    }

    public Method getMethod() {
        return method;
    }

}

package SpringMechanism.RouteMechanism;

import anotacije.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerDiscovery {

    public static void  discoverControllers() {
        List<Class<?>> controllerClasses = new ArrayList<>();
        List<Object> controllerInstances = new ArrayList<>();

        String projectPath = System.getProperty("user.dir");
        File root = new File(projectPath + File.separator + "target/classes");

        discoverControllersInDirectory(root, "", controllerClasses);

        for (Class<?> controllerClass : controllerClasses) {
            Object controllerInstance = ControllerSingleton.getInstance().getControllerInstance(controllerClass);
            controllerInstances.add(controllerInstance);
        }
//        return controllerInstances;
    }

    private static void discoverControllersInDirectory(File directory, String basePackage, List<Class<?>> controllerClasses) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String packageName;
                    if(Objects.equals(basePackage, ""))
                        packageName = file.getName();
                    else
                        packageName = basePackage + "." + file.getName();
                    discoverControllersInDirectory(file, packageName, controllerClasses);
                } else if (file.getName().endsWith(".class")) {
                        String className;
                        if(Objects.equals(basePackage, ""))
                            className = file.getName().replace(".class", "");
                        else
                            className = basePackage + "." + file.getName().replace(".class", "");
                    try {

                        Class<?> clazz = Class.forName(className);
                        if (clazz.isAnnotationPresent(Controller.class)) {
                            controllerClasses.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

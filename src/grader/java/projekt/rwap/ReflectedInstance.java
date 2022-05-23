package projekt.rwap;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectedInstance {
    private final Class<?> clazz;
    private final Object instance;

    private ReflectedInstance(Class<?> clazz, Object instance) {
        this.clazz = clazz;
        this.instance = instance;
    }

    public static ConstructorWrapper getConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        var constructor = clazz.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        return initargs ->
            new ReflectedInstance(clazz, constructor.newInstance(initargs));
    }

    public MethodWrapper getMethod(String name, Class<?>... parameterTypes) throws ReflectiveOperationException {
        var method = findMethod(name, parameterTypes);
        return args -> {
            var result = method.invoke(instance, args);

            if (method.getReturnType() == void.class) {
                return null;
            }

            return new ReflectedInstance(method.getReturnType(), result);
        };
    }

    @NotNull
    private Method findMethod(String name, Class<?>[] parameterTypes) throws NoSuchMethodException {
        Method method;

        try {
            method = clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            method = clazz.getDeclaredMethod(name, parameterTypes);
        }

        method.setAccessible(true);
        return method;
    }

    private Class<?>[] argsToTypes(Object... args) {
        return Arrays.stream(args)
            .map(Object::getClass)
            .toArray(Class[]::new);
    }

    public Object getInstance() {
        return instance;
    }
}

package projekt.rwap;

import org.opentest4j.AssertionFailedError;
import org.opentest4j.TestAbortedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectTestUtils {

    private ReflectTestUtils() {}


    /**
     * Creates a new instance using {@code constructor} and {@code params} as parameters to pass to the constructor.
     *
     * @param constructor the constructor to use
     * @param params      parameters to pass to the constructor when instantiating
     * @return a new instance of the class {@code constructor} belongs to
     * @throws AssertionFailedError if any exception is thrown during the invocation of {@code constructor},
     *     the constructor is not accessible or the class could not be instantiated for any reason
     */
    public static Object newInstance(Constructor<?> constructor, Object... params) {
        try {
            return constructor.newInstance(params);
        } catch (InstantiationException e) {
            throw new AssertionFailedError("Could not create instance of " + constructor.getDeclaringClass(), e);
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access constructor of class " + constructor.getDeclaringClass(), e);
        } catch (InvocationTargetException e) {
            throw new AssertionFailedError("An exception occurred while instantiating " + constructor.getDeclaringClass(),
                e.getCause());
        }
    }

    public static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new TestAbortedException("Class %s could not be found".formatted(className), e);
        }
    }
}

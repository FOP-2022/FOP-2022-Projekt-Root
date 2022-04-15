package projekt.utils;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Extensions for JUnit's {@link Assertions} class.
 * This class contains assertions for classes, fields and methods using Java reflections.
 */
@SuppressWarnings("unused")
public class TutorAssertions extends Assertions {

    /**
     * A mapping of modifiers in {@link Modifier} to their actual name.
     */
    private static final Map<Integer, String> MODIFIER_STRING = Map.ofEntries(
        Map.entry(Modifier.PUBLIC, "PUBLIC"),
        Map.entry(Modifier.PRIVATE, "PRIVATE"),
        Map.entry(Modifier.PROTECTED, "PROTECTED"),
        Map.entry(Modifier.STATIC, "STATIC"),
        Map.entry(Modifier.FINAL, "FINAL"),
        Map.entry(Modifier.SYNCHRONIZED, "SYNCHRONIZED"),
        Map.entry(Modifier.VOLATILE, "VOLATILE"),
        Map.entry(Modifier.TRANSIENT, "TRANSIENT"),
        Map.entry(Modifier.NATIVE, "NATIVE"),
        Map.entry(Modifier.INTERFACE, "INTERFACE"),
        Map.entry(Modifier.ABSTRACT, "ABSTRACT"),
        Map.entry(Modifier.STRICT, "STRICT"),
        Map.entry(0x1000, "SYNTHETIC")
    );

    /*================================================*
     * Classes                                        *
     *================================================*/

    /**
     * Asserts that a class with the fully qualified name {@code className} exists.
     *
     * @param className the fully qualified name of a class
     * @return a {@link Class} object if the class exists
     */
    public static Class<?> assertClassExists(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new AssertionFailedError("Class '%s' could not be found".formatted(className), e);
        }
    }

    /**
     * Asserts that {@code clazz} has the correct modifiers.
     *
     * @param clazz     the class to get the actual modifiers from
     * @param modifiers the modifiers the class must have
     */
    public static void assertClassHasModifiers(Class<?> clazz, int modifiers) {
        assertModifiers(modifiers, clazz.getModifiers() & modifiers,
            "Class '%s' does not have expected modifiers".formatted(clazz.getName()));
    }

    /**
     * Asserts that {@code clazz} only has the correct modifiers.
     *
     * @param clazz     the class to get the actual modifiers from
     * @param modifiers the exact modifiers the class must have
     */
    public static void assertClassHasExactModifiers(Class<?> clazz, int modifiers) {
        assertModifiers(modifiers, clazz.getModifiers(),
            "Class '%s' does not have expected modifiers".formatted(clazz.getName()));
    }

    /**
     * Asserts that {@code clazz} is not generic, meaning it has no type parameters.
     *
     * @param clazz the class to assert non-genericity for
     */
    public static void assertClassNotGeneric(Class<?> clazz) {
        if (clazz.getTypeParameters().length != 0) {
            fail("Class '%s' is not supposed to be generic but has the following type parameters: %s".formatted(
                clazz.getName(),
                Arrays.stream(clazz.getTypeParameters()).map(TypeVariable::getName).collect(Collectors.joining(", "))
            ));
        }
    }

    /**
     * Asserts that {@code clazz} is generic and has the exact the type parameters listed in {@code identifiers}. Furthermore,
     * this method asserts that each type parameter has the correct bounds, meaning both the same number of bounds in {@code
     * bounds[i]} as well as the bounds themselves (or rather, their name) in any order. Bounds must be specified as the fully
     * qualified name of the type, e.g. {@code "java.lang.String"}.
     *
     * @param clazz       the class to assert genericity for
     * @param identifiers the names of the expected type parameters
     * @param bounds      a matrix of bounds; mapping of bounds to the corresponding
     *                    type parameter in {@code identifiers}
     */
    public static void assertClassTypeParameters(Class<?> clazz, String[] identifiers, String[][] bounds) {
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();

        assertEquals(identifiers.length, typeParameters.length,
            "Class '%s' is either not generic or does not have the expected number of type parameters"
                .formatted(clazz.getName()));

        for (int i = 0; i < identifiers.length; i++) {
            TypeVariable<?> typeParameter = typeParameters[i];
            assertEquals(identifiers[i], typeParameter.getName(),
                "Type parameter names of class '%s' do not match at position %d".formatted(clazz.getName(), i + 1));

            Set<String> actualBounds = Arrays.stream(typeParameter.getBounds())
                .map(Type::getTypeName)
                .collect(Collectors.toUnmodifiableSet());
            assertEquals(bounds[i].length, actualBounds.size(),
                "Type parameter '%s' of class '%s' at position %d does not have the expected number of bounds"
                    .formatted(typeParameter.getName(), clazz.getName(), i + 1));

            for (String bound : bounds[i]) {
                if (!actualBounds.contains(bound)) {
                    fail("Type parameter '%s' of class '%s' at position %d is not bound by '%s'"
                        .formatted(typeParameter.getName(), clazz.getName(), i + 1, bound));
                }
            }
        }
    }

    /**
     * Asserts that {@code clazz} directly extends {@code superclass}.
     *
     * @param clazz      the class to assert the superclass for
     * @param superclass fully qualified name of the superclass
     */
    public static void assertClassExtends(Class<?> clazz, String superclass) {
        var actualSuperClass = clazz.getGenericSuperclass();
        assertNotNull(actualSuperClass,
            "Class does not extend any other class - is it an interface, or java.lang.Object.class?");

        if (!superclass.equals(actualSuperClass.getTypeName())) {
            fail("Superclass of class '%s' does not match given superclass".formatted(clazz.getSimpleName()));
        }
    }

    /**
     * Asserts that {@code clazz} implements all interfaces in {@code interfaces}.
     * {@code clazz} may be an interface itself.
     *
     * @param clazz      the class to assert the implemented interfaces for
     * @param interfaces fully qualified names of the interfaces implemented by {@code clazz}
     */
    public static void assertClassImplements(Class<?> clazz, String... interfaces) {
        Set<String> actualInterfaceTypes = Arrays.stream(clazz.getGenericInterfaces())
            .map(Type::getTypeName)
            .collect(Collectors.toUnmodifiableSet());

        Arrays.stream(interfaces)
            .forEach(typeName -> actualInterfaceTypes
                .stream()
                .filter(typeName::equals)
                .findAny()
                .orElseThrow(() -> new AssertionFailedError(
                    "Class '%s' does not implement interface '%s'".formatted(clazz.getName(), typeName)
                )));
    }

    /*================================================*
     * Fields                                         *
     *================================================*/

    /**
     * Asserts that {@code clazz} has a field with identifier {@code identifier}.
     *
     * @param clazz      the class that has the field
     * @param identifier identifier of the field
     * @return the {@link Field}, if it exists
     */
    public static Field assertClassHasField(Class<?> clazz, String identifier) {
        return Stream.concat(Arrays.stream(clazz.getDeclaredFields()), Arrays.stream(clazz.getFields()))
            .distinct()
            .filter(field -> field.getName().equals(identifier))
            .findAny()
            .orElseThrow(() -> new AssertionFailedError("No field with identifier '%s' was found".formatted(identifier)));
    }

    /**
     * Asserts that {@code clazz} has a field that matches {@code predicate}.
     *
     * @param clazz     the class the field belongs to
     * @param predicate a predicate for matching required criteria
     * @return the {@link Field}, if it exists
     */
    public static Field assertClassHasField(Class<?> clazz, Predicate<Field> predicate) {
        Set<Field> filteredFields = Stream.concat(Arrays.stream(clazz.getFields()), Arrays.stream(clazz.getDeclaredFields()))
            .filter(predicate)
            .collect(Collectors.toUnmodifiableSet());

        if (filteredFields.size() == 0) {
            throw new AssertionFailedError("No fields matching the given predicate were found"
                + (predicate instanceof DescriptivePredicate<Field> p ? ".\n" + p.getDescription() : ""));
        } else if (filteredFields.size() != 1) {
            throw new AssertionFailedError("Multiple fields matching the given predicate were found - cannot resolve ambiguity"
                + (predicate instanceof DescriptivePredicate<Field> p ? ".\n" + p.getDescription() : ""));
        } else {
            return filteredFields.iterator().next();
        }
    }

    /**
     * Asserts that a field has correct modifiers. Checks involving parameters with the {@link Nullable} annotation will be
     * skipped if they are {@code null}.
     *
     * @param field         the field to assert the things above for
     * @param modifiers     the modifiers the field should have
     */
    public static void assertField(Field field, @Nullable Integer modifiers) {
        assertField(field, modifiers, null, null);
    }

    /**
     * Asserts that a field has correct modifiers and type. Checks involving parameters with the {@link Nullable} annotation
     * will be skipped if they are {@code null}.
     *
     * @param field         the field to assert the things above for
     * @param modifiers     the modifiers the field should have
     * @param typePredicate a predicate for asserting the type of the given field
     */
    public static void assertField(Field field, @Nullable Integer modifiers, @Nullable Predicate<Type> typePredicate) {
        assertField(field, modifiers, typePredicate, null);
    }

    /**
     * Asserts that a field has correct modifiers, type and name. Checks involving parameters with the {@link Nullable}
     * annotation will be skipped if they are {@code null}.
     *
     * @param field         the field to assert the things above for
     * @param modifiers     the modifiers the field should have
     * @param typePredicate a predicate for asserting the type of the given field
     * @param fieldName     the name the given field should have
     */
    public static void assertField(Field field, @Nullable Integer modifiers, @Nullable Predicate<Type> typePredicate,
                                   @Nullable String fieldName) {
        if (modifiers != null) {
            assertModifiers(modifiers, field.getModifiers(),
                "Modifiers of field '%s' in class '%s' don't match the expected ones"
                    .formatted(field.getName(), field.getDeclaringClass().getName()));
        }
        if (typePredicate != null && !typePredicate.test(field.getGenericType())) {
            fail("Field '%s' in class '%s' does not have correct type"
                .formatted(field.getName(), field.getDeclaringClass().getName())
                + (typePredicate instanceof DescriptivePredicate<Type> p ? ".\n" + p.getDescription() : ""));
        }
        if (fieldName != null) {
            assertEquals(fieldName, field.getName(), "Name of Field '%s' in class '%s' does not match expected name"
                    .formatted(field.getName(), field.getDeclaringClass().getName()));
        }
    }

    /**
     * Asserts that {@code field} matches {@code expected} for the specified field in {@code instance}.
     *
     * @param field    the field to assert the value for
     * @param instance the instance to get the field value from (is {@code null}, if the field is static)
     * @param expected the predicate to test the field value
     * @return the value of {@code field}
     */
    public static Object assertFieldValue(Field field, @Nullable Object expected, @Nullable Object instance) {
        try {
            Object value = field.get(instance);
            assertEquals(expected, value, "Actual value of field '%s' in class '%s' did not match the expected value"
                    .formatted(field.getName(), field.getDeclaringClass().getName()));
            return value;
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access field '%s' in class '%s'"
                .formatted(field.getName(), field.getDeclaringClass().getName()));
        } catch (InaccessibleObjectException e) {
            throw new AssertionFailedError("Could not make field '%s' in class '%s' accessible"
                .formatted(field.getName(), field.getDeclaringClass().getName()));
        }
    }

    /*================================================*
     * Constructor                                    *
     *================================================*/

    /**
     * Asserts that {@code clazz} has a constructor with signature {@code signature}.
     *
     * @param clazz     the class to check for the constructor
     * @param signature the signature of the constructor
     * @return the {@link Constructor}, if it exists
     */
    public static Constructor<?> assertClassHasConstructor(Class<?> clazz, String signature) {
        return Stream.concat(Arrays.stream(clazz.getDeclaredConstructors()), Arrays.stream(clazz.getConstructors()))
            .filter(constructor -> executableToString(constructor).equals(signature))
            .findAny()
            .orElseThrow(() -> new AssertionFailedError("Class '%s' does not have a constructor with signature '%s'"
                .formatted(clazz.getName(), signature)));
    }

    /**
     * Asserts that {@code clazz} has exactly one constructor that matches a given predicate.
     *
     * @param clazz     the class to check for the constructor
     * @param predicate the predicate
     * @return the {@link Constructor}, if it exists
     */
    public static Constructor<?> assertClassHasConstructor(Class<?> clazz, Predicate<Constructor<?>> predicate) {
        Set<Constructor<?>> constructors = Stream
            .concat(Arrays.stream(clazz.getDeclaredConstructors()), Arrays.stream(clazz.getConstructors()))
            .filter(predicate)
            .collect(Collectors.toUnmodifiableSet());

        if (constructors.size() == 0) {
            throw new AssertionFailedError("No constructors in class '%s' matched the provided predicate"
                .formatted(clazz.getName())
                + (predicate instanceof DescriptivePredicate<Constructor<?>> p ? ".\n" + p.getDescription() : ""));
        } else if (constructors.size() != 1) {
            throw new AssertionFailedError(
                "Multiple constructors in class '%s' matching the provided predicate were found - cannot resolve ambiguity"
                    .formatted(clazz.getName())
                    + (predicate instanceof DescriptivePredicate<Constructor<?>> p ? ".\n" + p.getDescription() : ""));
        } else {
            return constructors.iterator().next();
        }
    }

    /**
     * Asserts that {@code constructor} has correct modifiers and parameters types.
     * Checks on {@link Nullable}-annotated parameters will be skipped if {@code null} is passed as the actual value.
     *
     * @param constructor         the constructor to assert the things above for
     * @param modifiers           the modifiers the constructor should have
     * @param parameterPredicates predicates for the constructor's parameters in the same order they are declared
     */
    @SafeVarargs
    public static void assertConstructor(Constructor<?> constructor, @Nullable Integer modifiers,
                                         @Nullable Predicate<Type>... parameterPredicates) {
        Type[] actualParameterTypes = constructor.getGenericParameterTypes();

        if (modifiers != null) {
            assertModifiers(modifiers, constructor.getModifiers(),
                "Modifiers of constructor '%s' in class '%s' don't match the expected ones"
                    .formatted(executableToString(constructor), constructor.getDeclaringClass().getName()));
        }
        if (parameterPredicates != null) {
            assertEquals(parameterPredicates.length,  actualParameterTypes.length,
                "Constructor '%s' in class '%s' does not have expected number of parameters"
                    .formatted(executableToString(constructor), constructor.getDeclaringClass().getName()));
            for (int i = 0; i < parameterPredicates.length; i++) {
                Predicate<Type> predicate = parameterPredicates[i];
                if (predicate != null && !predicate.test(actualParameterTypes[i])) {
                    fail("Parameter at position %d for constructor '%s' in class '%s' does not match predicate"
                        .formatted(i + 1, executableToString(constructor), constructor.getDeclaringClass().getName())
                        + (predicate instanceof DescriptivePredicate<Type> p ? ".\n" + p.getDescription() : ""));
                }
            }
        }
    }

    /*================================================*
     * Methods                                        *
     *================================================*/

    /**
     * Asserts that {@code clazz} has a method with signature {@code signature}.
     *
     * @param clazz     the class to check for the method
     * @param signature the signature of the method
     * @return the {@link Method}, if it exists
     */
    public static Method assertClassHasMethod(Class<?> clazz, String signature) {
        return Stream.concat(Arrays.stream(clazz.getDeclaredMethods()), Arrays.stream(clazz.getMethods()))
            .filter(method -> executableToString(method).equals(signature))
            .findAny()
            .orElseThrow(() -> new AssertionFailedError(
                "No method with signature '%s' was found in class '%s'".formatted(signature, clazz.getName())));
    }

    /**
     * Asserts that {@code clazz} has exactly one method matching {@code predicate} and returns it.
     *
     * @param clazz     the class the method belongs to
     * @param predicate a predicate for matching required criteria
     * @return the {@link Method}, if it exists
     */
    public static Method assertClassHasMethod(Class<?> clazz, Predicate<Method> predicate) {
        Set<Method> filteredMethods = Stream
            .concat(Arrays.stream(clazz.getMethods()), Arrays.stream(clazz.getDeclaredMethods()))
            .filter(predicate)
            .collect(Collectors.toUnmodifiableSet());

        if (filteredMethods.size() == 0) {
            throw new AssertionFailedError("No methods matching the given predicate were found in class '%s'"
                .formatted(clazz.getName())
                + (predicate instanceof DescriptivePredicate<Method> p ? ".\n" + p.getDescription() : ""));
        } else if (filteredMethods.size() != 1) {
            throw new AssertionFailedError(
                "Multiple methods matching the given predicate were found in class '%s' - cannot resolve ambiguity"
                    .formatted(clazz.getName())
                    + (predicate instanceof DescriptivePredicate<Method> p ? ".\n" + p.getDescription() : ""));
        } else {
            return filteredMethods.iterator().next();
        }
    }

    /**
     * Asserts that a method has correct modifiers.
     *
     * @param method              the method to assert the things above for
     * @param modifiers           the modifiers the method should have
     */
    public static void assertMethod(Method method, @Nullable Integer modifiers) {
        assertMethod(method, modifiers, null, null, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has correct the return type.
     *
     * @param method              the method to assert the things above for
     * @param returnTypePredicate a predicate for asserting the type of the given method
     */
    public static void assertMethod(Method method, @Nullable Predicate<Type> returnTypePredicate) {
        assertMethod(method, null, returnTypePredicate, null, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has the correct name.
     *
     * @param method              the method to assert the things above for
     * @param methodName          the name the given method should have
     */
    public static void assertMethod(Method method, @Nullable String methodName) {
        assertMethod(method, null, null, methodName, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has correct modifiers and return type.
     *
     * @param method              the method to assert the things above for
     * @param modifiers           the modifiers the method should have
     * @param returnTypePredicate a predicate for asserting the type of the given method
     */
    public static void assertMethod(Method method, @Nullable Integer modifiers, @Nullable Predicate<Type> returnTypePredicate) {
        assertMethod(method, modifiers, returnTypePredicate, null, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has correct modifiers and name.
     *
     * @param method              the method to assert the things above for
     * @param modifiers           the modifiers the method should have
     * @param methodName          the name the given method should have
     */
    public static void assertMethod(Method method, @Nullable Integer modifiers, @Nullable String methodName) {
        assertMethod(method, modifiers, null, methodName, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has the correct return type and name.
     *
     * @param method              the method to assert the things above for
     * @param returnTypePredicate a predicate for asserting the type of the given method
     * @param methodName          the name the given method should have
     */
    public static void assertMethod(Method method, @Nullable Predicate<Type> returnTypePredicate, @Nullable String methodName) {
        assertMethod(method, null, returnTypePredicate, methodName, (Predicate<Type>[]) null);
    }

    /**
     * Asserts that a method has correct modifiers, return type, name and parameter types. Checks
     * involving parameters with the {@link Nullable} annotation will be skipped if they are {@code null}.
     *
     * @param method              the method to assert the things above for
     * @param modifiers           the modifiers the method should have
     * @param returnTypePredicate a predicate for asserting the type of the given method
     * @param methodName          the name the given method should have
     * @param parameterPredicates predicates for the methods' parameters in the same order they
     *                            are declared
     */
    @SafeVarargs
    public static void assertMethod(Method method,
                                    @Nullable Integer modifiers,
                                    @Nullable Predicate<Type> returnTypePredicate,
                                    @Nullable String methodName,
                                    @Nullable Predicate<Type>... parameterPredicates) {
        Type[] actualParameterTypes = method.getGenericParameterTypes();

        if (modifiers != null) {
            assertModifiers(modifiers, method.getModifiers(),
                "Modifiers of method '%s' in class '%s' don't match the expected ones"
                    .formatted(executableToString(method), method.getDeclaringClass().getName()));

        }
        if (returnTypePredicate != null && !returnTypePredicate.test(method.getGenericReturnType())) {
            fail("Method '%s' in class '%s' does not have the correct return type"
                .formatted(executableToString(method), method.getDeclaringClass().getName())
                + (returnTypePredicate instanceof DescriptivePredicate<Type> p ? ".\n" + p.getDescription() : ""));
        }
        if (parameterPredicates != null) {
            assertEquals(parameterPredicates.length, actualParameterTypes.length,
                "Method '%s' in class '%s' does not have expected number of parameters"
                    .formatted(executableToString(method), method.getDeclaringClass().getName()));
            for (int i = 0; i < parameterPredicates.length; i++) {
                Predicate<Type> predicate = parameterPredicates[i];
                if (predicate != null && !predicate.test(actualParameterTypes[i])) {
                    fail("Parameter #%d of Method '%s' in class '%s' does not match given predicate"
                        .formatted(i + 1, executableToString(method), method.getDeclaringClass().getName())
                        + (predicate instanceof DescriptivePredicate<Type> p ? ".\n" + p.getDescription() : ""));
                }
            }
            if (methodName != null) {
                assertEquals(methodName, method.getName(),
                    "Name of Method '%s' in class '%s' does not match expected name"
                        .formatted(executableToString(method), method.getDeclaringClass().getName()));
            }
        }
    }

    /**
     * Asserts that {@code method} returns an object that equals {@code expected} when invoked.
     * {@code instance} may only be {@code null} if {@code method} is static.
     *
     * @param method     the method that will be invoked
     * @param expected   the expected object
     * @param instance   the execution context
     * @param parameters the parameters that will be passed to the method
     */
    public static Object assertMethodReturnValue(Method method, @Nullable Object expected, @Nullable Object instance,
                                               Object... parameters) {
        try {
            Object result = method.invoke(instance, parameters);
            assertEquals(expected, result, "Actual return value of method '%s' in class '%s' did not match expected value"
                .formatted(executableToString(method), method.getDeclaringClass().getName()));
            return result;
        } catch (InaccessibleObjectException e) {
            throw new AssertionFailedError("Could not make method '%s' in class '%s' accessible"
                .formatted(executableToString(method), method.getDeclaringClass().getName()), e);
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access method '%s' in class '%s'"
                .formatted(executableToString(method), method.getDeclaringClass().getName()), e);
        } catch (InvocationTargetException e) {
            throw new AssertionFailedError("An exception occurred while invoking method '%s' in class '%s'"
                .formatted(executableToString(method), method.getDeclaringClass().getName()), e);
        }
    }

    /**
     * Asserts that {@code method} is annotated with the annotations in {@code annotations}.
     *
     * @param method      the method to check annotations for
     * @param annotations an array of annotations the method must have
     */
    public static void assertAnnotations(Method method, Class<?>... annotations) {
        Set<Class<?>> methodAnnotations = Stream
            .concat(Arrays.stream(method.getAnnotations()), Arrays.stream(method.getDeclaredAnnotations()))
            .map(Annotation::annotationType)
            .collect(Collectors.toUnmodifiableSet());

        for (Class<?> annotation : annotations) {
            if (!methodAnnotations.contains(annotation)) {
                throw new AssertionFailedError("Method '%s' in class '%s' is not annotated with '%s'"
                    .formatted(method.getName(), method.getDeclaringClass().getName(), annotation.getName()));
            }
        }
    }

    /*================================================*
     * Utility methods                                *
     *================================================*/

    /**
     * Asserts that the given modifiers match and throws an {@link AssertionFailedError} in case they don't.
     *
     * @param expected         the expected modifiers
     * @param actual           the actual modifiers
     * @param exceptionMessage the exception message
     */
    private static void assertModifiers(int expected, int actual, String exceptionMessage) {
        if (actual != expected) {
            throw new AssertionFailedError(exceptionMessage, modifiersToString(expected), modifiersToString(actual));
        }
    }

    /**
     * Extracts all modifiers in {@code modifiers} in human-readable form.
     *
     * @param modifiers the modifiers
     * @return the human-readable modifiers
     */
    private static String modifiersToString(int modifiers) {
        StringJoiner joiner = new StringJoiner(", ");

        for (int i = 0; i < MODIFIER_STRING.size(); i++) {
            if ((1 << i & modifiers) != 0) {
                joiner.add(MODIFIER_STRING.get(1 << i));
            }
        }

        return joiner.toString();
    }

    private static String executableToString(Executable executable) {
        return "%s(%s)".formatted(executable.getName(), parametersToString(executable.getGenericParameterTypes()));
    }

    /**
     * Returns the type names of the given types joined together with commas.
     *
     * @param parameterTypes an array of types
     * @return the joined type names
     */
    private static String parametersToString(Type... parameterTypes) {
        return Arrays.stream(parameterTypes).map(Type::getTypeName).collect(Collectors.joining(", "));
    }
}

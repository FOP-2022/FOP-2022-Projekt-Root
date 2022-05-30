package projekt.utils;

import kotlin.Pair;
import org.opentest4j.AssertionFailedError;
import org.opentest4j.TestAbortedException;
import projekt.rwap.ReflectTestUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * A utility class for all test classes to reduce redundancy.
 *
 * <p>
 * This class can not be instantiated directly, but by test classes extending it to perform checks on classes and their
 * members.
 * To instantiate this class, at the very least the name of the class that will be tested is needed. However, this means that
 * the class' members can not be tested through the methods in this class directly. The constructor is overloaded to allow
 * different test cases, e.g., asserting that a class exists and has specific fields. The class member mappings
 * {@link #constructorMap}, {@link #fieldMap} and {@link #methodMap} cannot be changed after instantiation.
 * </p>
 */
@SuppressWarnings("unused")
public class TestClass {

    /**
     * The name of the class that is tested.
     */
    protected final String className;

    /**
     * The {@link Class} object of the tested class.
     */
    protected final Class<?> clazz;

    /**
     * A map of constructors assigned to their signatures.
     */
    protected Map<String, Constructor<?>> constructorMap;

    /**
     * A map of fields assigned to their identifiers.
     */
    protected final Map<String, Field> fieldMap;

    /**
     * A map of methods assigned to their signatures.
     */
    protected final Map<String, Method> methodMap;

    /**
     * Creates a new abstract test class for {@code className}.
     *
     * @param className the fully qualified name of the class that will be tested
     */
    protected TestClass(String className) {
        this(className, Collections.emptyMap(), Collections.emptyMap(), Collections.emptyMap());
    }

    /**
     * Creates a new abstract test class for {@code className}.
     *
     * @param className             the fully qualified name of the class that will be tested
     * @param constructorPredicates a mapping of signatures to predicates that match a constructor
     *                              with that specific signature
     */
    protected TestClass(String className, Map<String, Predicate<Constructor<?>>> constructorPredicates) {
        this(className, constructorPredicates, Collections.emptyMap(), Collections.emptyMap());
    }

    /**
     * Creates a new abstract test class for {@code className}.
     *
     * @param className             the fully qualified name of the class that will be tested
     * @param constructorPredicates a mapping of signatures to predicates that match a constructor
     *                              with that specific signature
     * @param methodPredicates      a mapping of signatures to predicates that match a method
     *                              with that specific signature
     */
    protected TestClass(String className,
                        Map<String, Predicate<Constructor<?>>> constructorPredicates,
                        Map<String, Predicate<Method>> methodPredicates) {
        this(className, constructorPredicates, Collections.emptyMap(), methodPredicates);
    }

    /**
     * Creates a new abstract test class for {@code className}.
     *
     * @param className             the fully qualified name of the class that will be tested
     * @param constructorPredicates a mapping of signatures to predicates that match a constructor
     *                              with that specific signature
     * @param fieldPredicates       a mapping of identifiers to predicates that match a field with
     *                              that specific identifier
     * @param methodPredicates      a mapping of signatures to predicates that match a method
     *                              with that specific signature
     */
    protected TestClass(String className,
                        Map<String, Predicate<Constructor<?>>> constructorPredicates,
                        Map<String, Predicate<Field>> fieldPredicates,
                        Map<String, Predicate<Method>> methodPredicates) {
        this.className = className;
        this.clazz = ReflectTestUtils.getClassForName(className);
        setConstructorPredicates(constructorPredicates);
        this.fieldMap = mapPredicates(fieldPredicates, clazz.getFields(), clazz.getDeclaredFields());
        this.methodMap = mapPredicates(methodPredicates, clazz.getMethods(), clazz.getDeclaredMethods());
    }

    protected void setConstructorPredicates(Map<String, Predicate<Constructor<?>>> constructorPredicates) {
        this.constructorMap = mapPredicates(constructorPredicates, clazz.getConstructors(), clazz.getDeclaredConstructors());
    }

    /**
     * Returns the {@link Class} object of the tested class.
     *
     * @return the {@link Class} object
     */
    public Class<?> getTestedClass() {
        return clazz;
    }

    /**
     * Returns the {@link Constructor} object for a given signature if it has been previously matched during instantiation.
     *
     * @param signature the signature of the constructor
     * @return the {@link Constructor} object corresponding to {@code signature}
     * @throws TestAbortedException if {@link #constructorMap} does not have a mapping for {@code signature}
     */
    public Constructor<?> getConstructor(String signature) {
        if (constructorMap.containsKey(signature)) {
            return constructorMap.get(signature);
        } else {
            throw new TestAbortedException("No constructor with signature %s was found".formatted(signature));
        }
    }

    /**
     * Returns the {@link Field} object for a given identifier if it has been previously matched during instantiation.
     *
     * @param identifier the identifier of the field
     * @return the {@link Field} object corresponding to {@code identifier}
     * @throws TestAbortedException if {@link #fieldMap} does not have a mapping for {@code identifier}
     */
    public Field getField(String identifier) {
        if (fieldMap.containsKey(identifier)) {
            return fieldMap.get(identifier);
        } else {
            throw new TestAbortedException("No field with identifier %s was found".formatted(identifier));
        }
    }

    /**
     * Returns the {@link Method} object for a given signature if it has been previously matched during instantiation.
     *
     * @param signature the signature of the method
     * @return the {@link Method} object corresponding to {@code signature}
     * @throws TestAbortedException if {@link #methodMap} does not have a mapping for {@code signature}
     */
    public Method getMethod(String signature) {
        if (methodMap.containsKey(signature)) {
            return methodMap.get(signature);
        } else {
            throw new TestAbortedException("No method with signature %s was found".formatted(signature));
        }
    }

    /**
     * Creates a new instance using {@code constructor} and {@code params} as parameters to pass to the constructor.
     *
     * @param constructor the constructor to use
     * @param params      parameters to pass to the constructor when instantiating
     * @return a new instance of the class {@code constructor} belongs to
     * @throws AssertionFailedError if any exception is thrown during the invocation of {@code constructor},
     *                              the constructor is not accessible or the class could not be instantiated for any reason
     */
    public Object newInstance(Constructor<?> constructor, Object... params) {
        try {
            return constructor.newInstance(params);
        } catch (InstantiationException e) {
            throw new AssertionFailedError("Could not create instance of " + className, e);
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access constructor of class " + className, e);
        } catch (InvocationTargetException e) {
            throw new AssertionFailedError("An exception occurred while instantiating " + className,
                e.getCause());
        }
    }

    /**
     * Invokes {@code method} with {@code params} as parameters and returns the value returned by the invoked method.
     *
     * @param method   the method to invoke
     * @param instance the context, i.e. instance of a class with this field
     * @param params   the parameters to pass to {@code method}
     * @param <T>      the return type
     * @return the value returned by invoking {@code method}
     * @throws AssertionFailedError if any exception is thrown during the invocation of {@code method}
     *                              or the method is not accessible
     */
    @SuppressWarnings("unchecked")
    public <T> T invokeMethod(Method method, Object instance, Object... params) {
        try {
            return (T) method.invoke(instance, params);
        } catch (InvocationTargetException e) {
            throw new AssertionFailedError(
                "An exception occurred while invoking method %s".formatted(method.getName()),
                e.getCause());
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access method %s".formatted(method.getName()), e);
        }
    }

    /**
     * Returns the value of {@code field} in the context of {@code instance}.
     *
     * @param field    the field
     * @param instance the context, i.e. instance of a class with this field
     * @param <T>      the return type
     * @return the value of {@code field}
     * @throws AssertionFailedError if the field is not accessible
     */
    @SuppressWarnings("unchecked")
    public <T> T getFieldValue(Field field, Object instance) {
        try {
            return (T) field.get(instance);
        } catch (IllegalAccessException e) {
            throw new AssertionFailedError("Could not access field %s".formatted(field.getName()), e);
        }
    }

    /**
     * Creates a predicate from a given signature that matches the name and generic parameters of an {@link Executable}.
     *
     * @param signature the signature of a constructor or method
     * @return a predicate matching a method or constructor with the given signature
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected static <T extends Executable> Predicate<T> predicateFromSignature(String signature) {
        Matcher matcher = Pattern.compile("(?<name>[\\w.]+)\\((?<parameters>.*)\\)").matcher(signature);
        matcher.matches(); // what the hell, Java? Why???
        return t -> t.getName().equals(matcher.group("name"))
            && Arrays.equals(
            Arrays.stream(t.getGenericParameterTypes()).map(Type::getTypeName).toArray(String[]::new),
            splitParameters(matcher.group("parameters"))
        );
    }

    /**
     * Creates a {@link Map} of {@link Predicate}s from a given signatures that matches
     * the name and generic parameters of an {@link Executable}.
     *
     * @param signatures the signatures of the constructors or methods
     * @return A map of predicates
     */
    protected static <T extends Executable> Map<String, Predicate<T>> predicatesFromSignatures(String... signatures) {
        return Arrays.stream(signatures)
            .collect(Collectors.toMap(
                Function.identity(),
                TestClass::predicateFromSignature
            ));
    }

    protected static Predicate<Field> predicateFromIdentifier(String identifier) {
        return field -> field.getName().equals(identifier);
    }

    protected static Map<String, Predicate<Field>> predicateFromIdentifiers(String... identifiers) {
        return Arrays.stream(identifiers)
            .collect(Collectors.toMap(
                Function.identity(),
                TestClass::predicateFromIdentifier
            ));
    }

    /**
     * Creates a descriptive predicate from a given signature that matches the name and generic parameters of an
     * {@link Executable}. The description can be retrieved using {@link DescriptivePredicate#getDescription()}.
     *
     * @param signature   the signature of a constructor or method
     * @param description the description of this predicate
     * @return a new {@link DescriptivePredicate}
     */
    protected static <T extends Executable> DescriptivePredicate<T> descriptivePredicateFromSignature(String signature,
                                                                                                      String description) {
        return new DescriptivePredicate<>(predicateFromSignature(signature), description);
    }

    /**
     * Splits comma-seperated parameters while keeping type parameters inside angled brackets intact.
     * Comma-seperated type parameters will have their separator extended / trimmed to a comma directly followed by a space.
     *
     * @param parameters the parameters of a method or constructor signature
     * @return an array of parameters
     */
    private static String[] splitParameters(String parameters) {
        LinkedList<StringBuilder> stringList = new LinkedList<>();
        stringList.add(new StringBuilder());
        int openBrackets = 0;
        for (char c : parameters.toCharArray()) {
            if (c == ',' && openBrackets == 0) {
                stringList.add(new StringBuilder());
            } else {
                openBrackets += c == '<' ? 1 : c == '>' ? -1 : 0;
                stringList.getLast().append(c);
            }
        }
        return stringList.stream()
            .map(sb -> sb.toString().trim().replaceAll(", *", ", "))
            .filter(s -> !s.isBlank())
            .toArray(String[]::new);
    }

    /**
     * Maps the elements of {@code ts} to the entries of {@code predicateMap}. More accurately, for each match in the cross
     * product of {@code ts} and the predicates in {@code predicateMap}, a pair of the matching predicate's key and the value
     * in {@code ts} is inserted into the newly created map that will be returned. <br>
     * If any value in {@code ts} is not matched by any predicate, it will not be contained in the returned map.
     * Additionally, {@link AccessibleObject#setAccessible(boolean)} will be called on each value in the returned map to make
     * the object accessible.
     * If the resulting map would contain duplicates, a {@link IllegalStateException} will be thrown
     * (see {@link Collectors#toUnmodifiableMap(Function, Function)}).
     *
     * @param predicateMap a map of identifiers (keys) and their corresponding predicates (values)
     * @param ts           the values
     * @param <T>          the type of value
     * @return a mapping of identifiers and the values their predicate matched
     */
    @SafeVarargs
    private static <T extends AccessibleObject> Map<String, T> mapPredicates(Map<String, Predicate<T>> predicateMap, T[]... ts) {
        return Arrays.stream(ts)
            .flatMap(Arrays::stream)
            // filter synthetic members such as bridge methods
            .filter(t -> !(t instanceof Member member) || !member.isSynthetic())
            .distinct()
            .map(t -> {
                for (Map.Entry<String, Predicate<T>> entry : predicateMap.entrySet()) {
                    if (entry.getValue().test(t)) {
                        return new Pair<>(entry.getKey(), t);
                    }
                }
                return null;
            })
            .filter(Objects::nonNull)
            .peek(pair -> pair.getSecond().setAccessible(true))
            .collect(Collectors.toUnmodifiableMap(Pair::getFirst, Pair::getSecond));
    }
}

package projekt.utils;

import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TypeUtils {

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

    private TypeUtils() {}

    /**
     * Extracts all modifiers in {@code modifiers} in human-readable form.
     *
     * @param modifiers the modifiers
     * @return the human-readable modifiers
     */
    static String modifiersToString(int modifiers) {
        StringJoiner joiner = new StringJoiner(", ");

        for (int i = 0; i < MODIFIER_STRING.size(); i++) {
            if ((1 << i & modifiers) != 0) {
                joiner.add(MODIFIER_STRING.get(1 << i));
            }
        }

        return joiner.toString();
    }

    static String executableToString(Executable executable) {
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

    /**
     * @param expectedName name of the expected type
     * @return A {@link Predicate} matching the given typename
     */
    public static DescriptivePredicate<Type> hasType(String expectedName) {
        return new DescriptivePredicate<>(
            returnType ->
                returnType.getTypeName().equals(expectedName),
            "Return type must be '%s'".formatted(expectedName));
    }

    /**
     * @param expectedType Class of the expected type
     * @return A {@link Predicate} matching the given typename
     */
    public static DescriptivePredicate<Type> hasType(Class<?> expectedType) {
        return hasType(expectedType.getName());
    }
}

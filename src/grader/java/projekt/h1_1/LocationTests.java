package projekt.h1_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static projekt.utils.TutorAssertions.assertClassHasConstructor;
import static projekt.utils.TutorAssertions.assertClassHasField;
import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertClassNotGeneric;
import static projekt.utils.TutorAssertions.assertConstructor;
import static projekt.utils.TutorAssertions.assertEquals;
import static projekt.utils.TutorAssertions.assertField;
import static projekt.utils.TutorAssertions.assertMethod;

@TestForSubmission("projekt")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class LocationTests extends TestClass {

    public static final String FIELD_X_IDENTIFIER = "x";
    public static final String FIELD_Y_IDENTIFIER = "y";
    public static final String CONSTRUCTOR_SIGNATURE = "projekt.base.Location(int, int)";
    public static final String METHOD_GET_X_SIGNATURE = "getX()";
    public static final String METHOD_GET_Y_SIGNATURE = "getY()";
    public static final String METHOD_ADD_SIGNATURE = "add(projekt.base.Location)";
    public static final String METHOD_SUBTRACT_SIGNATURE = "subtract(projekt.base.Location)";

    private static final Object[][] INSTANTIATION_PARAMETERS = {
        {0, 0},
        {-1, 0},
        {-123, 456},
        {Integer.MIN_VALUE, Integer.MAX_VALUE}
    };

    public LocationTests() {
        super(
            "projekt.base.Location",
            predicatesFromSignatures(CONSTRUCTOR_SIGNATURE),
            predicateFromIdentifiers(
                FIELD_X_IDENTIFIER,
                FIELD_Y_IDENTIFIER
            ),
            predicatesFromSignatures(
                METHOD_GET_X_SIGNATURE,
                METHOD_GET_Y_SIGNATURE,
                METHOD_ADD_SIGNATURE,
                METHOD_SUBTRACT_SIGNATURE
            )
        );
    }

    public static Stream<Arguments> provideConstructorParameters() {
        return Arrays
            .stream(INSTANTIATION_PARAMETERS)
            .map(Arguments::of);
    }

    @Test
    @DisplayName("1 | Class, fields, constructor and methods")
    public void testDefinition() {
        assertClassNotGeneric(clazz);

        Field x = assertClassHasField(clazz, FIELD_X_IDENTIFIER);
        assertField(x, Modifier.PRIVATE | Modifier.FINAL, TypeUtils.hasType(int.class));
        Field y = assertClassHasField(clazz, FIELD_Y_IDENTIFIER);
        assertField(y, Modifier.PRIVATE | Modifier.FINAL, TypeUtils.hasType(int.class));

        Constructor<?> constructor = assertClassHasConstructor(clazz, CONSTRUCTOR_SIGNATURE);
        assertConstructor(constructor, Modifier.PUBLIC, (Predicate<Type>[]) null);

        Method getX = assertClassHasMethod(clazz, METHOD_GET_X_SIGNATURE);
        assertMethod(getX, Modifier.PUBLIC, TypeUtils.hasType(int.class));
        Method getY = assertClassHasMethod(clazz, METHOD_GET_Y_SIGNATURE);
        assertMethod(getY, Modifier.PUBLIC, TypeUtils.hasType(int.class));
        Method add = assertClassHasMethod(clazz, METHOD_ADD_SIGNATURE);
        assertMethod(add, Modifier.PUBLIC, TypeUtils.hasType(className));
        Method subtract = assertClassHasMethod(clazz, METHOD_SUBTRACT_SIGNATURE);
        assertMethod(subtract, Modifier.PUBLIC, TypeUtils.hasType(className));
    }

    @DisplayName("2 | Instance")
    @ParameterizedTest
    @MethodSource("provideConstructorParameters")
    public void testInstance(Integer x, Integer y) {
        Object instance = newInstance(x, y);

        assertEquals(x, getFieldValue(getField(FIELD_X_IDENTIFIER), instance),
            "Field 'x' in class %s does not have the same value as first parameter of constructor".formatted(className));

        assertEquals(y, getFieldValue(getField(FIELD_Y_IDENTIFIER), instance),
            "Field 'y' in class %s does not have the same value as second parameter of constructor".formatted(className));
    }

    private Object newInstance(Integer x, Integer y) {
        return newInstance(getConstructor(CONSTRUCTOR_SIGNATURE), x, y);
    }

    @DisplayName("3 | getX() and getY()")
    @ParameterizedTest
    @MethodSource("provideConstructorParameters")
    public void testGetters(Integer x, Integer y) {
        Object instance = newInstance(x, y);

        assertEquals(x, invokeMethod(getMethod(METHOD_GET_X_SIGNATURE), instance),
            "Method 'getX' in class %s did not return the same value as first parameter of constructor".formatted(className));

        assertEquals(y, invokeMethod(getMethod(METHOD_GET_Y_SIGNATURE), instance),
            "Method 'getY' in class %s did not return the same value as second parameter of constructor".formatted(className));
    }

    @DisplayName("4 | add()")
    @ParameterizedTest
    @MethodSource("provideConstructorParameters")
    public void testAdd(Integer x, Integer y) {
        Object a = newInstance(x, y);
        Object b = newInstance(y, x);

        Object sum = invokeMethod(getMethod(METHOD_ADD_SIGNATURE), a, b);
        Object expected = x + y;

        assertEquals(expected, getFieldValue(getField(FIELD_X_IDENTIFIER), sum),
            "Field 'x' in class %s does not have the same value the expected sum".formatted(className));

        assertEquals(expected, getFieldValue(getField(FIELD_Y_IDENTIFIER), sum),
            "Field 'y' in class %s does not have the same value the expected sum".formatted(className));
    }

    @DisplayName("4 | add()")
    @ParameterizedTest
    @MethodSource("provideConstructorParameters")
    public void testSub(Integer x, Integer y) {
        Object a = newInstance(x, y);
        Object b = newInstance(y, x);

        Object difference = invokeMethod(getMethod(METHOD_SUBTRACT_SIGNATURE), a, b);

        Object expectedX = x - y;
        assertEquals(expectedX, getFieldValue(getField(FIELD_X_IDENTIFIER), difference),
            "Field 'x' in class %s does not have the same value the expected difference".formatted(className));

        Object expectedY = y - x;
        assertEquals(expectedY, getFieldValue(getField(FIELD_Y_IDENTIFIER), difference),
            "Field 'y' in class %s does not have the same value the expected difference".formatted(className));
    }
}

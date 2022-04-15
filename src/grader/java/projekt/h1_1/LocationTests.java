package projekt.h1_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.*;
import java.util.Map;
import java.util.function.Predicate;

import static projekt.utils.TutorAssertions.*;

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
            Map.of(CONSTRUCTOR_SIGNATURE, predicateFromSignature(CONSTRUCTOR_SIGNATURE)),
            Map.of(
                FIELD_X_IDENTIFIER, field -> field.getName().equals(FIELD_X_IDENTIFIER),
                FIELD_Y_IDENTIFIER, field -> field.getName().equals(FIELD_Y_IDENTIFIER)
            ),
            Map.of(
                METHOD_GET_X_SIGNATURE, predicateFromSignature(METHOD_GET_X_SIGNATURE),
                METHOD_GET_Y_SIGNATURE, predicateFromSignature(METHOD_GET_Y_SIGNATURE),
                METHOD_ADD_SIGNATURE, predicateFromSignature(METHOD_ADD_SIGNATURE),
                METHOD_SUBTRACT_SIGNATURE, predicateFromSignature(METHOD_SUBTRACT_SIGNATURE)
            )
        );
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

    @Test
    @DisplayName("2 | Instance")
    public void testInstance() {
        for (Object[] parameters : INSTANTIATION_PARAMETERS) {
            Object instance = newInstance(getConstructor(CONSTRUCTOR_SIGNATURE), parameters);

            assertSame(parameters[0], getFieldValue(getField(FIELD_X_IDENTIFIER), instance),
                "Field 'x' in class %s does not have the same value as first parameter of constructor".formatted(className));
            assertSame(parameters[1], getFieldValue(getField(FIELD_Y_IDENTIFIER), instance),
                "Field 'y' in class %s does not have the same value as second parameter of constructor".formatted(className));
        }
    }
}

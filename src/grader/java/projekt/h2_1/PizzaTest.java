package projekt.h2_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.*;
import static projekt.utils.TutorAssertions.assertClassHasMethod;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PizzaTest extends TestClass {

    public static final String CLASS_NAME = "projekt.food.Pizza";
    public static final String METHOD_GET_DIAMETER_SIGNATURE = "getDiameter()";


    public PizzaTest() {
        super(
            CLASS_NAME,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_DIAMETER_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, SaucableTest.CLASS_NAME);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_DIAMETER_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}

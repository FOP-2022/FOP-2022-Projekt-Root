package projekt.h2_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.function.DoubleUnaryOperator;

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PizzaConfigTest extends TestClass {

    public static final String METHOD_DIAMETER_SIGNATURE = "diameter(%s)"
        .formatted(ClassName.DOUBLE_UNARY_OPERATOR);

    public static final String METHOD_GET_DIAMETER_MUTATOR_SIGNATURE = "getDiameterMutator()";

    public PizzaConfigTest() {
        super(
            ClassName.PIZZA_CONFIG,
            Collections.emptyMap(),
            predicatesFromSignatures(
                METHOD_DIAMETER_SIGNATURE,
                METHOD_GET_DIAMETER_MUTATOR_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, ClassName.SAUCABLE_CONFIG);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_DIAMETER_SIGNATURE),
            TypeUtils.hasType(void.class)
        );
        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_DIAMETER_MUTATOR_SIGNATURE),
            TypeUtils.hasType(DoubleUnaryOperator.class)
        );
    }

}

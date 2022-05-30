package projekt.h2_3;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.*;

@TestForSubmission("projekt")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PizzaVariantTest extends TestClass {

    public static final String METHOD_GET_BASE_DIAMETER_SIGNATURE = "getBaseDiameter()";

    public PizzaVariantTest() {
        super(
            ClassName.PIZZA_VARIANT,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_BASE_DIAMETER_SIGNATURE)
        );
    }

    @Test
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
    }

    @Test
    public void testDerivation() {
        assertClassImplements(clazz, ClassName.SAUCABLE_VARIANT);
        assertClassNotGeneric(clazz);
    }

    @Test
    public void testMethods() {
        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_BASE_DIAMETER_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}

package projekt.h2_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertClassHasModifiers;
import static projekt.utils.TutorAssertions.assertClassImplements;
import static projekt.utils.TutorAssertions.assertMethod;

@TestForSubmission("projekt")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SaucableConfigTest extends TestClass {

    private static final String UNARY_OPERATOR = "%s<%s>"
        .formatted(ClassName.UNARY_OPERATOR, ClassName.STRING);

    public static final String METHOD_SAUCE_SIGNATURE = "sauce(%s)"
        .formatted(UNARY_OPERATOR);

    public static final String METHOD_GET_SAUCE_MUTATOR_SIGNATURE = "getSauceMutator()";

    public SaucableConfigTest() {
        super(
            ClassName.SAUCABLE_CONFIG,
            Collections.emptyMap(),
            predicatesFromSignatures(
                METHOD_SAUCE_SIGNATURE,
                METHOD_GET_SAUCE_MUTATOR_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, ClassName.FOOD_CONFIG);
    }

    @Test
    @DisplayName("Methods")
    public void testMethods() {
        assertMethod(
            assertClassHasMethod(clazz, METHOD_SAUCE_SIGNATURE),
            TypeUtils.hasType(void.class)
        );
        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_SAUCE_MUTATOR_SIGNATURE),
            TypeUtils.hasType(UNARY_OPERATOR)
        );
    }
}

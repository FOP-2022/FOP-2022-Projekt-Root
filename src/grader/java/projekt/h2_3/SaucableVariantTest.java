package projekt.h2_3;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SaucableVariantTest extends TestClass {

    public static final String METHOD_GET_BASE_SAUCE_SIGNATURE = "getBaseSauce()";

    public SaucableVariantTest() {
        super(
            ClassName.SAUCABLE_VARIANT,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_BASE_SAUCE_SIGNATURE)
        );
    }

    @Test
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
    }

    @Test
    public void testDerivation() {
        assertClassImplements(clazz, ClassName.FOOD_VARIANT);

        assertClassHasTypeParameters(clazz,
            Set.of(ClassName.SAUCABLE),
            Set.of(ClassName.SAUCABLE_CONFIG)
        );
    }

    @Test
    public void testMethods() {
        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_BASE_SAUCE_SIGNATURE),
            TypeUtils.hasType(String.class)
        );
    }
}

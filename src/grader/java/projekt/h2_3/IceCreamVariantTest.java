package projekt.h2_3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertClassHasModifiers;
import static projekt.utils.TutorAssertions.assertClassImplements;
import static projekt.utils.TutorAssertions.assertClassNotGeneric;
import static projekt.utils.TutorAssertions.assertMethod;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class IceCreamVariantTest extends TestClass {

    public static final String METHOD_GET_BASE_FLAVOR_SIGNATURE = "getBaseFlavor()";

    public IceCreamVariantTest() {
        super(
            ClassName.ICE_CREAM_VARIANT,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_BASE_FLAVOR_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, ClassName.FOOD_VARIANT);
        assertClassNotGeneric(clazz);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_BASE_FLAVOR_SIGNATURE),
            TypeUtils.hasType(String.class)
        );
    }
}

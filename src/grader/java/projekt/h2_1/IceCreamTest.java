package projekt.h2_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Modifier;
import java.util.Collections;

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class IceCreamTest extends TestClass {

    public static final String METHOD_GET_FLAVOR_SIGNATURE = "getFlavor()";

    public IceCreamTest() {
        super(
            ClassName.ICE_CREAM,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_FLAVOR_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, SaucableTest.FOOD_CLASS);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_FLAVOR_SIGNATURE),
            TypeUtils.hasType(String.class)
        );
    }

}

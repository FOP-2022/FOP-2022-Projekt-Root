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
public class SaucableTest extends TestClass {

    public static final String METHOD_GET_SAUCE_SIGNATURE = "getSauce()";

    public SaucableTest() {
        super(
            ClassName.SAUCABLE,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_SAUCE_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, ClassName.FOOD);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_SAUCE_SIGNATURE),
            TypeUtils.hasType(String.class)
        );
    }

}

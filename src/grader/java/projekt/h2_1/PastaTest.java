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

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PastaTest extends TestClass {

    public static final String CLASS_NAME = "projekt.food.Pasta";
    public static final String METHOD_GET_THICKNESS_SIGNATURE = "getThickness()";

    public PastaTest() {
        super(
            CLASS_NAME,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_THICKNESS_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, SaucableTest.CLASS_NAME);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_THICKNESS_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }

}

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

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PastaVariantTest extends TestClass {

    public static final String METHOD_GET_BASE_THICKNESS_SIGNATURE = "getBaseThickness()";

    public PastaVariantTest() {
        super(
            ClassName.PASTA_VARIANT,
            Collections.emptyMap(),
            predicatesFromSignatures(METHOD_GET_BASE_THICKNESS_SIGNATURE)
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassHasModifiers(clazz, Modifier.PUBLIC | Modifier.INTERFACE);
        assertClassImplements(clazz, ClassName.SAUCABLE_VARIANT);
        assertClassNotGeneric(clazz);

        assertMethod(
            assertClassHasMethod(clazz, METHOD_GET_BASE_THICKNESS_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}

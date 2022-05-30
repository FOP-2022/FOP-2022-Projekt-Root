package projekt.h2_3;

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
import static projekt.utils.TutorAssertions.assertClassNotGeneric;
import static projekt.utils.TutorAssertions.assertMethod;

@TestForSubmission("projekt")
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
            assertClassHasMethod(clazz, METHOD_GET_BASE_THICKNESS_SIGNATURE),
            TypeUtils.hasType(double.class)
        );
    }
}

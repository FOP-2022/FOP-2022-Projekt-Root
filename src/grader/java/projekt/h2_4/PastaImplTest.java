package projekt.h2_4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class PastaImplTest extends TestClass {

    public static final String FIELD_THICKNESS_IDENTIFIER = "thickness";

    protected PastaImplTest() {
        super(
            ClassName.PASTA_IMPL,
            Map.of(),
            predicateFromIdentifiers(
                FIELD_THICKNESS_IDENTIFIER
            ),
            Map.of()
        );
    }

    @Test
    @DisplayName("Class, fields and methods")
    public void testDefinition() {
        assertClassNotGeneric(clazz);
        assertClassImplements(clazz, ClassName.PASTA);

        Field thickness = assertClassHasField(clazz, FIELD_THICKNESS_IDENTIFIER);
        assertField(thickness, Modifier.PRIVATE | Modifier.FINAL, TypeUtils.hasType(double.class));
    }
}

package projekt.h2_4;

import org.junit.jupiter.api.*;
import projekt.utils.ClassName;
import projekt.utils.TestClass;
import projekt.utils.TypeUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import static projekt.utils.TutorAssertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class IceCreamImplTest extends TestClass {

    public static final String FIELD_FLAVOR_IDENTIFIER = "flavor";

    protected IceCreamImplTest() {
        super(
            ClassName.ICE_CREAM_IMPL,
            Map.of(),
            predicateFromIdentifiers(
                FIELD_FLAVOR_IDENTIFIER
            ),
            Map.of()
        );
    }

    @Test
    @DisplayName("Interface and methods")
    public void testDefinition() {
        assertClassNotGeneric(clazz);
        assertClassImplements(clazz, ClassName.ICE_CREAM);

        Field flavor = assertClassHasField(clazz, FIELD_FLAVOR_IDENTIFIER);
        assertField(flavor, Modifier.PRIVATE | Modifier.FINAL, TypeUtils.hasType(String.class));
    }
}

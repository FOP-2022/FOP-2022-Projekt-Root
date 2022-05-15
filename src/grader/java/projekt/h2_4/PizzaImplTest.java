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
public class PizzaImplTest extends TestClass {

    public static final String FIELD_DIAMETER_IDENTIFIER = "diameter";

    protected PizzaImplTest() {
        super(
            ClassName.PIZZA_IMPL,
            Map.of(),
            predicateFromIdentifiers(
                FIELD_DIAMETER_IDENTIFIER
            ),
            Map.of()
        );
    }

    @Test
    @DisplayName("Class, fields and methods")
    public void testDefinition() {
        assertClassNotGeneric(clazz);
        assertClassImplements(clazz, ClassName.PIZZA);

        Field diameter = assertClassHasField(clazz, FIELD_DIAMETER_IDENTIFIER);
        assertField(diameter, Modifier.PRIVATE | Modifier.FINAL, TypeUtils.hasType(double.class));
    }
}

package projekt.h2_4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class IceCreamImplTest extends AbstractFoodImpTest {

    @BeforeAll
    static void setup() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL)
            .requireImplementing(ClassName.ICE_CREAM);

        spec.requireField("flavor")
            .requireType(String.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}

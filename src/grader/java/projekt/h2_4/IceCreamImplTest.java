package projekt.h2_4;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

@TestForSubmission("projekt")
public class IceCreamImplTest extends AbstractFoodImplTest {

    IceCreamImplTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL)
            .requireImplements(ClassName.ICE_CREAM);

        spec.requireField("flavor")
            .requireType(String.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}

package projekt.h2_4;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.utils.ClassName;

import java.lang.reflect.Modifier;

@TestForSubmission("projekt")
public class PastaImplTest extends AbstractFoodImplTest {

    PastaImplTest() {
        spec.requireClass(ClassName.PASTA_IMPL)
            .requireImplements(ClassName.PASTA);

        spec.requireField("thickness")
            .requireType(double.class)
            .requireModifiers(Modifier.PRIVATE | Modifier.FINAL);
    }
}

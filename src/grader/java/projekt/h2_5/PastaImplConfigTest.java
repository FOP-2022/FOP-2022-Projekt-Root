package projekt.h2_5;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class PastaImplConfigTest extends ClassSpecTestCase {

    protected PastaImplConfigTest() {
        spec.requireClass(ClassName.PIZZA_IMPL_CONFIG)
            .requireImplements(ClassName.PIZZA_CONFIG);
    }
}

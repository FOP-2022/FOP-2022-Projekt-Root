package projekt.h2_5;

import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

@TestForSubmission("projekt")
public class IceCreamImplConfigTest extends ClassSpecTestCase {

    protected IceCreamImplConfigTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL_CONFIG)
            .requireImplements(ClassName.ICE_CREAM_CONFIG);
    }
}

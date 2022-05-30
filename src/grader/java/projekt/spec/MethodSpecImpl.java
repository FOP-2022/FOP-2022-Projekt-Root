package projekt.spec;

import static projekt.utils.TutorAssertions.assertClassHasMethod;
import static projekt.utils.TutorAssertions.assertMethod;

public class MethodSpecImpl extends MemberSpecImpl<MethodSpec> implements MethodSpec {

    public MethodSpecImpl(ClassSpec classSpec, String name) {
        super(classSpec, name);
    }

    @Override
    public SpecTester getTester() {
        return new SpecTester() {
            @Override
            public void runTest() {
                var method = assertClassHasMethod(classSpec.getClassToSpec(), name);
                assertMethod(method, modifiers, typePredicate);
            }

            @Override
            public String toString() {
                return "Test for method %s".formatted(name);
            }
        };
    }

    @Override
    protected MethodSpec self() {
        return this;
    }
}

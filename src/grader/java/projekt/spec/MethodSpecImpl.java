package projekt.spec;

import static projekt.utils.TutorAssertions.*;

public class MethodSpecImpl extends MemberSpecImpl<MethodSpec> implements MethodSpec {

    public MethodSpecImpl(ClassSpec classSpec, String name) {
        super(classSpec, name);
    }

    @Override
    public MethodTester getTester() {
        return new MethodTester() {
            @Override
            public void testMethod() {
                var method = assertClassHasMethod(classSpec.getClassToSpec(), name);
                assertMethod(method, modifiers, typePredicate);
            }

            @Override
            public String toString() {
                return "Test for method %s".formatted(name);
            }
        };
    }
}

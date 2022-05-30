package projekt.spec;

import java.lang.reflect.Field;

import static projekt.utils.TutorAssertions.assertClassHasField;
import static projekt.utils.TutorAssertions.assertField;

public class FieldSpecImpl extends MemberSpecImpl<FieldSpec> implements FieldSpec {

    FieldSpecImpl(ClassSpec classSpec, String name) {
        super(classSpec, name);
    }

    @Override
    public SpecTester getTester() {
        return new SpecTester() {
            @Override
            public void runTest() {
                Field field = assertClassHasField(classSpec.getClassToSpec(), name);
                assertField(field, modifiers, typePredicate);
            }

            @Override
            public String toString() {
                return "Test for field %s".formatted(name);
            }
        };
    }

    @Override
    protected FieldSpec self() {
        return this;
    }
}

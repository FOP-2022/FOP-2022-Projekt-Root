package projekt.spec;

import java.lang.reflect.Field;

import static projekt.utils.TutorAssertions.*;

public class FieldSpecImpl extends MemberSpecImpl<FieldSpec> implements FieldSpec {

    FieldSpecImpl(ClassSpec classSpec, String name) {
        super(classSpec, name);
    }

    @Override
    public FieldTester getTester() {
        return new FieldTester() {
            @Override
            public void testField() {
                Field field = assertClassHasField(classSpec.getClassToSpec(), name);
                assertField(field, modifiers, typePredicate);
            }

            @Override
            public String toString() {
                return "Test for field %s".formatted(name);
            }
        };
    }
}

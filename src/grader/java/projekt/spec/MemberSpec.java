package projekt.spec;

public interface MemberSpec<T extends MemberSpec<T>> extends TestableSpec {

    T requireType(Class<?> expectedType);

    T requireModifiers(int modifiers);
}

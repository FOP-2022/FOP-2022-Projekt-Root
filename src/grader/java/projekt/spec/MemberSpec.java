package projekt.spec;

public interface MemberSpec<T extends MemberSpec<T>> {

    T requireType(Class<?> expectedType);

    T requireModifiers(int modifiers);
}

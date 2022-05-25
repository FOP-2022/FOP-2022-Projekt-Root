package projekt.spec;

public interface MemberSpec<T extends MemberSpec<T>> {
    MemberSpec<T> requireType(Class<?> expectedType);

    MemberSpec<T> requireModifiers(int modifiers);
}

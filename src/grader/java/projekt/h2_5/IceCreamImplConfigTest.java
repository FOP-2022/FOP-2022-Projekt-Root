package projekt.h2_5;

import org.junit.jupiter.api.Test;
import projekt.rwap.ReflectedInstance;
import projekt.spec.ClassSpecTestCase;
import projekt.utils.ClassName;

public class IceCreamImplConfigTest extends ClassSpecTestCase {

    protected IceCreamImplConfigTest() {
        spec.requireClass(ClassName.ICE_CREAM_IMPL_CONFIG)
            .requireImplements(ClassName.ICE_CREAM_CONFIG);
    }

    @Test
    void callSomeMethod() throws ReflectiveOperationException {
        var wrapper = ReflectedInstance
            .getConstructor(spec.getClassToSpec())
            .newInstance();

        var mutatorWrapper = wrapper
            .getMethod("getFlavorMutator")
            .invoke();

        var stringWrapper = mutatorWrapper
            .getMethod("apply", Object.class)
            .invoke("abc");

        System.out.println(stringWrapper.getInstance());
    }
}

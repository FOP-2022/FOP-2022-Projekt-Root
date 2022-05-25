package projekt.spec;

public class EmptyMethodTester implements MethodTester {

    @Override
    public void testMethod() {}

    @Override
    public String toString() {
        return "No method tests specified";
    }
}

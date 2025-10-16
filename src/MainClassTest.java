import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int result = getLocalNumber();

        Assert.assertTrue("The method returned " + result + " not 14 as expected",result == 14);
    }

    @Test
    public void testGetClassNumber() {
        int result = getClassNumber();
        int expected = 45;

        Assert.assertTrue("The method returns less than " + expected,result > expected);
    }

    @Test
    public void testGetClassString() {
        String result = getClassString().toLowerCase();

        Assert.assertTrue("The string does not contain “hello” or “Hello”", result.contains("hello"));
    }

}

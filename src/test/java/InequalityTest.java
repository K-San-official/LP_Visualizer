
import model.Inequality;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InequalityTest
{
    @Test void greater_than()
    {
        Inequality i = Inequality.GREATER_THAN;
        assertEquals(">", i.toString());
    }

    @Test void less_than()
    {
        Inequality i = Inequality.LESS_THAN;
        assertEquals("<", i.toString());
    }

    @Test void equal_to()
    {
        Inequality i = Inequality.EQUAL_TO;
        assertEquals("=", i.toString());
    }
}

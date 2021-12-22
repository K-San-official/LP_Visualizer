import model.LinearProgram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinearProgramTest
{

    @Test void LP_creation()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);
        for(int i = 0; i < coefficients.length; i++)
        {
            for(int j = 0; j < coefficients[i].length; j++)
            {
                assertEquals(coefficients[i][j], lp.getElement(i,j));
            }
        }
    }

}

import model.Inequality;
import model.LinearProgram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OptimalityTest
{
    @Test void optimal_true_pos()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);
        assertTrue(lp.isOptimal());
    }

    @Test void optimal_true_zeros()
    {
        double[][] coefficients = {{0,0,0},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);
        assertTrue(lp.isOptimal());
    }

    @Test void optimal_false_posRHS()
    {
        double[][] coefficients = {{-1,-0.5,10},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);
        assertFalse(lp.isOptimal());
    }

    @Test void optimal_false_negRHS()
    {
        double[][] coefficients = {{-1,-0.5,-10},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);
        assertFalse(lp.isOptimal());
    }
}

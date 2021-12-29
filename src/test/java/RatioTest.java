import model.Inequality;
import model.LinearProgram;
import model.Pivot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RatioTest
{
    @Test void ratio_test_one_col()
    {
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[][] coefficients = {{-1, 2, 3},
                                   { 4, 6, 0},
                                   {10, 8, 0}};
        double[] rhs = {0,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        Pivot exp = new Pivot(1, 0);
        Pivot act = lp.ratioTest();
        assertEquals(exp, act);
    }

    @Test void ratio_test_one_row()
    {
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[][] coefficients = {{-1,-2, 3},
                                   { 4,10, 0},
                                   {-1,-2, 0}};
        double[] rhs = {0,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        Pivot exp = new Pivot(1, 1);
        Pivot act = lp.ratioTest();
        assertEquals(exp, act);
    }

    @Test void ratio_test_two_col_tie()
    {
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[][] coefficients = {{ 1,-2,-2},
                                   { 4, 4, 4},
                                   {-1,-2, 0}};
        double[] rhs = {0,20,20};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        Pivot exp = new Pivot(1, 1);
        Pivot act = lp.ratioTest();
        assertEquals(exp, act);
    }

    @Test void ratio_test_two_row_tie()
    {
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[][] coefficients = {{ 1,-2, 3},
                                   { 4, 2, 0},
                                   {-1, 2, 0}};
        double[] rhs = {0,20,20};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        Pivot exp = new Pivot(1, 1);
        Pivot act = lp.ratioTest();
        assertEquals(exp, act);
    }

    @Test void ratio_test_optimal()
    {
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[][] coefficients = {{-1,-2, 3},
                { 4,10, 0},
                {-1,-2, 0}};
        double[] rhs = {0,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        Pivot act = lp.ratioTest();
        assertNull(act);
    }
}

import model.Inequality;
import model.LinearProgram;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinearProgramTest
{

    @Test void LP_creation_correct()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        for(int i = 0; i < coefficients.length; i++)
        {
            for(int j = 0; j < coefficients[i].length; j++)
            {
                assertEquals(coefficients[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void LP_creation_wrong_sign()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};

        Throwable e = assertThrows(RuntimeException.class, ()-> new LinearProgram(coefficients, sign, rhs));
        assertEquals("Linear Program: Sign, RightHandSide and number of rows must all be equal length", e.getMessage());
    }

    @Test void LP_creation_wrong_RHS()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20};

        Throwable e = assertThrows(RuntimeException.class, ()-> new LinearProgram(coefficients, sign, rhs));
        assertEquals("Linear Program: Sign, RightHandSide and number of rows must all be equal length", e.getMessage());
    }


    @Test void LP_modification()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        for(int i = 0; i < coefficients.length; i++)
        {
            for(int j = 0; j < coefficients[i].length; j++)
            {
                lp.setElement(i,j, 10);
            }
        }

        for(int i = 0; i < coefficients.length; i++)
        {
            for(int j = 0; j < coefficients[i].length; j++)
            {
                assertEquals(10, lp.getElement(i,j));
            }
        }
    }

    @Test void slack_variable()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);
        double[][] exp = {{1,2,3,0},{4,5,6,1},{7,8,9,0}};

        lp.print();
        lp.addSlackVar(1);
        lp.print();

        assertEquals(4, lp.getColumns());
        assertEquals(3, lp.getRows());

        for(int i = 0; i < coefficients.length; i++)
        {
            for(int j = 0; j < coefficients[i].length; j++)
            {
                assertEquals(exp[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void scale_row_int_pos()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        double[][] exp_M = {{2,4,6},{8,10,12},{14,16,18}};
        double[] exp_RHS = {20,40,60};

        lp.scaleRow(0, 2);
        lp.scaleRow(1, 2);
        lp.scaleRow(2, 2);

        for(int i = 0; i < exp_M.length; i++)
        {
            for(int j = 0; j < exp_M[i].length; j++)
            {
                assertEquals(exp_M[i][j], lp.getElement(i,j));
            }
        }

        for(int i = 0; i < exp_RHS.length; i++)
        {
            assertEquals(exp_RHS[i], lp.getRHS(i));
        }
    }

    @Test void scale_row_frac_pos()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        double[][] exp_M = {{0.5,1,1.5},{2,2.5,3},{3.5,4,4.5}};
        double[] exp_RHS = {5,10,15};

        lp.scaleRow(0, 0.5);
        lp.scaleRow(1, 0.5);
        lp.scaleRow(2, 0.5);

        for(int i = 0; i < exp_M.length; i++)
        {
            for(int j = 0; j < exp_M[i].length; j++)
            {
                assertEquals(exp_M[i][j], lp.getElement(i,j));
            }
        }

        for(int i = 0; i < exp_RHS.length; i++)
        {
            assertEquals(exp_RHS[i], lp.getRHS(i));
        }
    }

    @Test void scale_row_int_neg()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        double[][] exp_M = {{-2,-4,-6},{-8,-10,-12},{-14,-16,-18}};
        double[] exp_RHS = {-20,-40,-60};

        lp.scaleRow(0, -2);
        lp.scaleRow(1, -2);
        lp.scaleRow(2, -2);

        for(int i = 0; i < exp_M.length; i++)
        {
            for(int j = 0; j < exp_M[i].length; j++)
            {
                assertEquals(exp_M[i][j], lp.getElement(i,j));
            }
        }

        for(int i = 0; i < exp_RHS.length; i++)
        {
            assertEquals(exp_RHS[i], lp.getRHS(i));
        }
    }

    @Test void scale_row_frac_neg()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        Inequality[] sign = {Inequality.EQUAL_TO, Inequality.LESS_THAN, Inequality.LESS_THAN};
        double[] rhs = {10,20,30};
        LinearProgram lp = new LinearProgram(coefficients, sign, rhs);

        double[][] exp_M = {{-0.5,-1,-1.5},{-2,-2.5,-3},{-3.5,-4,-4.5}};
        double[] exp_RHS = {-5,-10,-15};

        lp.scaleRow(0, -0.5);
        lp.scaleRow(1, -0.5);
        lp.scaleRow(2, -0.5);

        for(int i = 0; i < exp_M.length; i++)
        {
            for(int j = 0; j < exp_M[i].length; j++)
            {
                assertEquals(exp_M[i][j], lp.getElement(i,j));
            }
        }

        for(int i = 0; i < exp_RHS.length; i++)
        {
            assertEquals(exp_RHS[i], lp.getRHS(i));
        }
    }

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

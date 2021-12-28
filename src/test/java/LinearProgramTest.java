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


    @Test void LP_modification()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);

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
        LinearProgram lp = new LinearProgram(coefficients);
        double[][] exp = {{1,2,3,1},{4,5,6,0},{7,8,9,0}};

        lp.addSlackVar(1);

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
        double[][] exp = {{2,4,6},{8,10,12},{14,16,18}};
        LinearProgram lp = new LinearProgram(coefficients);

        lp.scaleRow(0, 2);
        lp.scaleRow(1, 2);
        lp.scaleRow(2, 2);

        for(int i = 0; i < exp.length; i++)
        {
            for(int j = 0; j < exp[i].length; j++)
            {
                assertEquals(exp[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void scale_row_frac_pos()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        double[][] exp = {{0.5,1,1.5},{2,2.5,3},{3.5,4,4.5}};
        LinearProgram lp = new LinearProgram(coefficients);

        lp.scaleRow(0, 0.5);
        lp.scaleRow(1, 0.5);
        lp.scaleRow(2, 0.5);

        for(int i = 0; i < exp.length; i++)
        {
            for(int j = 0; j < exp[i].length; j++)
            {
                assertEquals(exp[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void scale_row_int_neg()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        double[][] exp = {{-2,-4,-6},{-8,-10,-12},{-14,-16,-18}};
        LinearProgram lp = new LinearProgram(coefficients);

        lp.scaleRow(0, -2);
        lp.scaleRow(1, -2);
        lp.scaleRow(2, -2);

        for(int i = 0; i < exp.length; i++)
        {
            for(int j = 0; j < exp[i].length; j++)
            {
                assertEquals(exp[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void scale_row_frac_neg()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        double[][] exp = {{-0.5,-1,-1.5},{-2,-2.5,-3},{-3.5,-4,-4.5}};
        LinearProgram lp = new LinearProgram(coefficients);

        lp.scaleRow(0, -0.5);
        lp.scaleRow(1, -0.5);
        lp.scaleRow(2, -0.5);

        for(int i = 0; i < exp.length; i++)
        {
            for(int j = 0; j < exp[i].length; j++)
            {
                assertEquals(exp[i][j], lp.getElement(i,j));
            }
        }
    }

    @Test void optimal_true_pos()
    {
        double[][] coefficients = {{1,2,3},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);
        assertTrue(lp.isOptimal());
    }

    @Test void optimal_true_zeros()
    {
        double[][] coefficients = {{0,0,0},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);
        assertTrue(lp.isOptimal());
    }

    @Test void optimal_false_posRHS()
    {
        double[][] coefficients = {{-1,-0.5,10},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);
        assertFalse(lp.isOptimal());
    }

    @Test void optimal_false_negRHS()
    {
        double[][] coefficients = {{-1,-0.5,-10},{4,5,6},{7,8,9}};
        LinearProgram lp = new LinearProgram(coefficients);
        assertFalse(lp.isOptimal());
    }
}

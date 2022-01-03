package model;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

import java.util.Stack;

public class LinearProgram
{
    private Matrix M;
    private Inequality[] sign;
    private Vector RHS;
    private Stack<Matrix> M_history;
    private Stack<Vector> RHS_history;

    public LinearProgram(double[][] coefficients, Inequality[] sign, double[] rightHandSide)
    {
        assert coefficients.length <= 4;
        assert coefficients[0].length <= 4;
        if(sign.length != rightHandSide.length || sign.length != coefficients.length || coefficients.length != rightHandSide.length)
            throw new RuntimeException("Linear Program: Sign, RightHandSide and number of rows must all be equal length");

        M = new Basic2DMatrix(coefficients);
        RHS = new BasicVector(rightHandSide);
        M_history = new Stack<>();
        RHS_history = new Stack<>();

        this.sign = sign;
    }

    public void addSlackVar(int slack_row)
    {
        M_history.add(M.copy());
        double[][] new_coef = new double[M.rows()][M.columns()+1];
        double[][] old_coef = M.toDenseMatrix().toArray();

        // Adds all the old coefficients until the RHS of the matrix
        for(int row = 0; row < old_coef.length; row++)
        {
            for(int col = 0; col < old_coef[row].length; col++)
            {
                new_coef[row][col] = old_coef[row][col];
            }
        }

        // Adds the slack variable coefficient
        new_coef[slack_row][M.columns()] = 1;

        M = new Basic2DMatrix(new_coef);
    }

    public void scaleRow(int row, double scalar)
    {
        M_history.add(M.copy());
        for(int col = 0; col < M.columns(); col++)
        {
            double scaled = M.get(row, col) * scalar;
            M.set(row, col, scaled);
        }

        RHS_history.add(RHS.copy());
        RHS.set(row, RHS.get(row) * scalar);
    }

    public boolean isOptimal()
    {
        int row = 0;
        for(int col = 0; col < getColumns()-1; col++)
        {
            if(M.get(row, col) < 0)
                return false;
        }
        return true;
    }

    public Pivot ratioTest()
    {
        double min_ratio = 10E10;
        Pivot min_pivot = null;
        for(int row = 1; row < M.rows(); row++)
        {
            double denominator = RHS.get(row);
            for(int col = 0; col < M.columns(); col++)
            {
                double numerator = M.get(row, col);
                if(numerator > 0 && M.get(0,col) < 0)
                {
                    double ratio = numerator/denominator;
                    if(ratio < min_ratio)
                    {
                        min_ratio = ratio;
                        min_pivot = new Pivot(row, col);
                    }
                }
            }
        }
        return min_pivot;
    }

    public double getElement(int row, int col)
    {
        return M.get(row, col);
    }

    public void setElement(int row, int col, double value)
    {
        M.set(row, col, value);
    }

    public double getRHS(int row)
    {
        return RHS.get(row);
    }

    public int getRows()
    {
        return M.rows();
    }

    public int getColumns()
    {
        return M.columns();
    }

    public void print()
    {
        System.out.println(toString());
    }

    public String toString()
    {

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < M.rows(); r++)
        {
            for(int c = 0; c < M.columns(); c++)
            {
                sb.append(M.get(r,c));
                sb.append(" | ");
            }

            sb.append(sign[r]);
            sb.append(RHS.get(r));
            sb.append("\n");
        }
        return sb.toString();
    }

    private String h_line(int chars)
    {
        StringBuilder sb = new StringBuilder("\n----+");
        sb.append("-----+".repeat(Math.max(0, chars - 2)));
        sb.append("----\n");
        return sb.toString();
    }
}

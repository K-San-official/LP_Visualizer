package model;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

import java.util.Stack;

public class LinearProgram
{
    private Matrix M;
    private Vector RHS;
    private int decisionVars;
    private int constraints;
    private int slackVariables;
    private Stack<Matrix> history;

    public LinearProgram(double[][] coefficients, double[] rightHandSide)
    {
        assert coefficients.length <= 4;
        assert coefficients[0].length <= 4;

        decisionVars = coefficients[0].length;
        constraints = coefficients.length - 1;
        slackVariables = 0;

        M = new Basic2DMatrix(coefficients);
        RHS = new BasicVector(rightHandSide);
        history = new Stack<>();
    }

    public void addSlackVar(int slack_row)
    {
        history.add(M.copy());
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
        history.add(M.copy());
        for(int col = 0; col < M.columns(); col++)
        {
            double scaled = M.get(row, col) * scalar;
            M.set(row, col, scaled);
        }
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

    public double getElement(int row, int col)
    {
        return M.get(row, col);
    }

    public void setElement(int row, int col, double value)
    {
        M.set(row, col, value);
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

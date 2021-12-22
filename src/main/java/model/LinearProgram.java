package model;

import org.la4j.Matrix;
import org.la4j.Vector;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.dense.BasicVector;

import java.util.Stack;

public class LinearProgram
{
    private Matrix M;
    private int decisionVars;
    private int constraints;
    private int slackVariables;
    private Stack<Matrix> history;

    public LinearProgram(double[][] coefficients)
    {
        assert coefficients.length <= 4;
        assert coefficients[0].length <= 4;

        decisionVars = coefficients[0].length;
        constraints = coefficients.length - 1;
        slackVariables = 0;

        M = new Basic2DMatrix(coefficients);
        history = new Stack<>();
    }

    public void addSlackVar(int row)
    {
        history.add(M.copy());
        double[][] new_coef = new double[M.rows()][M.columns()+1];
        double[][] old_coef = M.toDenseMatrix().toArray();

        for(int i = 0; i < old_coef.length; i++)
        {
            for(int j = 0; j < old_coef[i].length; j++)
            {
                new_coef[i][j] = old_coef[i][j];
            }
        }

        new_coef[0][3] = 1;  // TODO fix this so it actually puts the zero in the right place!
        M = new Basic2DMatrix(new_coef);
    }

    public void scaleRow(int row, double value)
    {
        history.add(M.copy());
        // M.eachInRow();
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

    public String toString()
    {

        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < M.rows(); r++)
        {
            for(int c = 0; c < M.columns(); c++)
            {
                sb.append(M.get(r,c));
                if(c != M.columns() -1)
                    sb.append(" | ");
            }
            if(r != M.rows() -1)
                sb.append(h_line(M.columns()));
        }
        return sb.toString();
    }

    private String h_line(int chars)
    {
        StringBuilder sb = new StringBuilder("\n----+");
        for(int i = 0; i < chars-2; i++)
        {
            sb.append("-----+");
        }
        sb.append("----\n");
        return sb.toString();
    }
}

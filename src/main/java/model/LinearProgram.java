package model;

import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import java.util.Stack;

public class LinearProgram
{
    private Matrix A;
    private Matrix M;
    private Stack<Matrix> history;

    public LinearProgram(double[][] coefficients)
    {
        assert coefficients.length <= 4;
        assert coefficients[0].length <= 4;
        A = new Basic2DMatrix(coefficients);
        M = new Basic2DMatrix(coefficients);

        history = new Stack<>();
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
}

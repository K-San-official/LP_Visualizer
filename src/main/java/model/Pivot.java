package model;

public class Pivot
{
    public int row;
    public int col;

    public Pivot(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object O)
    {
        if(O.getClass() != Pivot.class)
            return false;
        Pivot other = (Pivot) O;
        return (this.row == other.row) && (this.col == other.col);
    }
}

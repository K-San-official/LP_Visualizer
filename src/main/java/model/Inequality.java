package model;

public enum Inequality
{
    GREATER_THAN,
    LESS_THAN,
    EQUAL_TO;

    public String toString()
    {
        switch (this)
        {
            case EQUAL_TO -> {return "=  ";}
            case GREATER_THAN -> {return ">= ";}
            case LESS_THAN -> {return "<= ";}
        }
        return "0_0";
    }
}


package com.example.testproblem.entity;

public class Total {
    public double total;

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        return this.total == ((Total) obj).total;
    }
}

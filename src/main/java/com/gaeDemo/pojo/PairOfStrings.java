package com.gaeDemo.pojo;


public class PairOfStrings
{
    public void setKey(String key)
    {
        this.key = key;
    }

    public void setVal(String val)
    {
        this.val = val;
    }

    public String getKey()
    {
        return key;
    }

    public String getVal()
    {
        return val;
    }

    private String key;
    private String val;

    private PairOfStrings(String key, String val)
    {
        this.key = key;
        this.val = val;
    }

    public static  PairOfStrings create(String key, String val)
    {
        return new PairOfStrings(key,val);
    }

    private PairOfStrings() {}


}

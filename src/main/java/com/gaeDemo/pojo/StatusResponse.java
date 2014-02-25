package com.gaeDemo.pojo;/**
 * Author: wge
 * Date: 11/10/2013
 * Time: 19:14
 */


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class StatusResponse
{
    private static final Logger log = Logger.getLogger(StatusResponse.class.getName());
    private int statusCode;
    private final List<PairOfStrings> listError =  new ArrayList<PairOfStrings>();
    private String errorMessage;

    public StatusResponse()
    {
    }

    //get/setters

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    private Object data;

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }


    public int getStatusCode()
    {
        return statusCode;
    }



    public void setErrorList(List allErrors)
    {
        listError.clear();
        listError.addAll(allErrors);
    }

    public List getErrorList()
    {
        return listError;
    }





    public void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }
}

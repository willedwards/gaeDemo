package com.gaeDemo.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.logging.Logger;

public class RfqDisplay
{
    private static final Logger log = Logger.getLogger(RfqDisplay.class.getName());

    @NotEmpty
    @Email
    public String passengerEmail;

}

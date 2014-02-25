package com.gaeDemo.controllers;


import com.gaeDemo.pojo.PairOfStrings;
import com.gaeDemo.pojo.RfqDisplay;
import com.gaeDemo.pojo.StatusResponse;
import com.google.appengine.repackaged.com.google.api.client.http.HttpStatusCodes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class RfqController
{
    private static final Logger log = Logger.getLogger(RfqController.class.getName());


    @RequestMapping(value = "/rfq/submit",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json",
            headers = {"content-type=application/json"})
    @ResponseBody
    public StatusResponse submitRfq(@RequestBody @Valid RfqDisplay initialRfq,BindingResult bindingResult, HttpServletRequest request )
    {

        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatusCode(HttpStatusCodes.STATUS_CODE_OK);

        if(bindingResult.hasErrors())
        {
            log.info("errors found");

            List<PairOfStrings> errors =  Arrays.asList(PairOfStrings.create("passengerEmail", "may not be empty")); //statusResponse.setErrorMessage("validation failed");
            statusResponse.setErrorMessage("validation failed");
            statusResponse.setErrorList(errors);
            return statusResponse;
        }

        log.info("everything good");
        //good path here...
        return  statusResponse;
    }
}

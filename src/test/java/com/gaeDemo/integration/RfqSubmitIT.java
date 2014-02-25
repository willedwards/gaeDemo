package com.gaeDemo.integration;/**
 * Author: wge
 * Date: 10/02/2014
 * Time: 12:54
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaeDemo.pojo.StatusResponse;
import com.jayway.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.expect;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:system-testContext.xml")
public class RfqSubmitIT
{
    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(RfqSubmitIT.class.getName());

    @Autowired
    private Integer jettyPort;


    @Before
    public void setUp() throws IOException {
        RestAssured.port = jettyPort;
    }

    @Test
    public void checkRfqSubmitMissingEmailOnly() throws IOException
    {
        String jsonToFire = getJsonContent("errorNoEmailSingleRfq.json");

        StatusResponse actualResponseObject = expect().statusCode(200)
                 .response()
                 .given().contentType("application/json").body(jsonToFire).when().post("/rfq/submit").as(StatusResponse.class);

        String expectedResponseJson = getJsonContent("errorNoEmailSingleRfqResponse.json");

        ObjectMapper mapper = new ObjectMapper();
        StatusResponse expectedStatusResponseJackson =  mapper.readValue(expectedResponseJson,StatusResponse.class);

        log.info("=====================");
        log.info("actualResponseObject toString = " + actualResponseObject);
        log.info("=====================");
        log.info("expectedResponseJson = " + expectedResponseJson);
        log.info("=====================");
        log.info("expectedStatusResponseJackson = " + expectedStatusResponseJackson);

        Assert.assertEquals(actualResponseObject,expectedStatusResponseJackson);
    }

    private String getJsonContent(String jsonFileName) throws IOException
    {
        String filePath = RfqSubmitIT.class.getClassLoader().getResource(jsonFileName).getFile();
        File file = new File(filePath);
        String content =  FileUtils.readFileToString(file);
        return removeNewLines(content);
    }

    private static String removeNewLines(String originalString) {
        return originalString.replaceAll("\\n","");
    }
}

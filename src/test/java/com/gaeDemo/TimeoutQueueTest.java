package com.gaeDemo;

import com.gaeDemo.pojo.Rfq;
import com.gaeDemo.service.ICoreRfqQueueService;
import com.gaeDemo.service.IRfqListener;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:/test-config.xml")
public class TimeoutQueueTest implements IRfqListener
{
    private static final Logger log = Logger.getLogger(TimeoutQueueTest.class.getName());
    private long triggerTimestampMs;

    private ICoreRfqQueueService timeoutOutRfqQueueService;

    @Before
    public void setup()
    {
        timeoutOutRfqQueueService.setRfqListener(this);
        timeoutOutRfqQueueService.start();
    }

    @Test
    public void placeItemOnAndWaitForPurge() throws InterruptedException
    {
        Rfq rfq1 = new Rfq();

        timeoutOutRfqQueueService.add(rfq1);

        assert(expectedSize(1));

        Thread.sleep(9100);//assume timeout is 9000ms

        long elapsedTimeMs = triggerTimestampMs - rfq1.getCreationTimeMs();

        Assert.assertEquals("message",9000,elapsedTimeMs,100);

    }

    @Test
    public void removeItemWeKnowExists() throws InterruptedException
    {
        Rfq rfq1 = new Rfq();

        timeoutOutRfqQueueService.add(rfq1);
        assert(expectedSize(1));

        Thread.sleep(8900);//assume timeout is 9000ms

        Rfq found = timeoutOutRfqQueueService.find(rfq1.getId());

        Assert.assertEquals(rfq1,found);

    }


    private boolean expectedSize(int size)
    {
       int actualSize = timeoutOutRfqQueueService.size();
       if(actualSize == size)
           return true;

       log.severe("expected " + size + " found " + actualSize);
       return false;
    }

    @Override
    public void onTrigger(Rfq rfq)
    {
        triggerTimestampMs = System.currentTimeMillis();
        log.info("called");
    }
}

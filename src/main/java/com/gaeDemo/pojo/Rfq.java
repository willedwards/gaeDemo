package com.gaeDemo.pojo;

import java.util.UUID;
import java.util.logging.Logger;


public class Rfq
{
    private static final Logger log = Logger.getLogger(Rfq.class.getName());


    private final String id;
    private long creationTimeMs = 0;

    public Rfq()
    {
        this.id = UUID.randomUUID().toString();
        creationTimeMs = System.currentTimeMillis();
    }

    public void resetClock()
    {
        creationTimeMs = 0;
        log.info("reset clock");
    }

    public long getCreationTimeMs()
    {
        return creationTimeMs;
    }

    public String getId()
    {
        return id;
    }

    //generated.
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Rfq))
        {
            return false;
        }

        Rfq rfq = (Rfq) o;

        if (creationTimeMs != rfq.creationTimeMs)
        {
            return false;
        }
        if (!id.equals(rfq.id))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id.hashCode();
        result = 31 * result + (int) (creationTimeMs ^ (creationTimeMs >>> 32));
        return result;
    }
}

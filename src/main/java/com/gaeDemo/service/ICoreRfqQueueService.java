package com.gaeDemo.service;

import com.gaeDemo.pojo.Rfq;

/**
 * Author: wge
 * Date: 27/02/2014
 * Time: 21:20
 */
public interface ICoreRfqQueueService extends Runnable
{
    void setRfqListener(IRfqListener rfqListener);

    void start();

    void shutdown();

    void add(Rfq rfq);

    void manuallyForceRemove(Rfq rfq);

    boolean contains(Rfq rfq);

    void manuallySeekTimeouts();

    void run();

    Rfq find(String id);

    int size();

}

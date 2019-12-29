package com.it18zhang.ssm.test;

import com.it18zhang.ssm.monitor.MonitorService;
import org.junit.Test;

/**
 * @Author ：Tanger.
 * @Date ：Created in 23:06 2019/10/15
 */

public class TestHeartBeatReceiver {
    @Test
    public void test1() throws InterruptedException {
        new MonitorService();
        while (true){
            Thread.sleep(5000);
        }
    }
}

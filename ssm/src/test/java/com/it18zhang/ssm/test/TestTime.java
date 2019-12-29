package com.it18zhang.ssm.test;

import com.it18zhang.ssm.hive.HiveCallLogService;
import com.it18zhang.ssm.util.CallLogUtil;
import org.junit.Test;

/**
 * @Author ：Tanger.
 * @Date ：Created in 11:47 2019/10/5
 */

public class TestTime {
    @Test
    public void test1 (){
        CallLogUtil.getCallLogRanges("20190102", "20190602");
    }

    @Test
    public void testLatestCallLog(){
        HiveCallLogService s = new HiveCallLogService();
        s.findLatestCallLog("15151889601");
    }

}

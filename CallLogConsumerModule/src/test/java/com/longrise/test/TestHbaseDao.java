package com.longrise.test;

import com.longrise.consumer.HbaseDao;
import org.junit.Test;

/**
 * @Author ：Tanger.
 * @Date ：Created in 16:40 2019/10/3
 */


public class TestHbaseDao {
    @Test
    public void test1(){
        HbaseDao dao = new HbaseDao();
        dao.put("15032293356,18620192711,2017/03/13 14:02:01,297");
    }

}

package com.it18zhang.ssm.monitor;

import com.it18zhang.ssm.domain.HeartBeat;
import org.springframework.stereotype.Service;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：Tanger.
 * @Date ：Created in 23:08 2019/10/15
 */
@Service("monitorService")
public class MonitorService {

//    private DatagramSocket sock;
//    private ReceiveThread t;
//
//    public MonitorService(){
//        t = new ReceiveThread();
//        t.start();
//    }
//
//    public List<HeartBeat> getHeartBeats(){
//        return new ArrayList<HeartBeat>(t.map.values());
//    }
}

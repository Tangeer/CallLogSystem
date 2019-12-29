package com.it18zhang.ssm.monitor;

import com.it18zhang.ssm.domain.HeartBeat;
import com.it18zhang.ssm.util.PropertiesUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：Tanger.
 * @Date ：Created in 22:27 2019/10/15
 */

public class ReceiveThread extends Thread{
//    //IP地址和最后一次收到心跳时间
//    public Map<String, HeartBeat> map = new HashMap<String,HeartBeat>();
//    DatagramSocket sock;
//
//    public ReceiveThread(){
//        try {
//            sock = new DatagramSocket(PropertiesUtil.getInt("heartbeat.udp.receive.port"));
//            this.setDaemon(true);
//            System.out.println("心跳接受开始了");
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run(){
//        byte[] buf = new byte[1];
//        DatagramPacket pack = new DatagramPacket(buf,1);
//        while(true){
//            try {
//                sock.receive(pack);
//                int flag = buf[0];
//                InetSocketAddress sockAddr = (InetSocketAddress)pack.getSocketAddress();
//                String ip = sockAddr.getAddress().getHostAddress();
//                map.put(ip, new HeartBeat(ip, flag, System.currentTimeMillis()));
//                System.out.println("收到心跳："+ ip + "," + flag + "," + System.currentTimeMillis());
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

}

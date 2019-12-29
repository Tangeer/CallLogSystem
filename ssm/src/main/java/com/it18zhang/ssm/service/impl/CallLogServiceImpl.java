package com.it18zhang.ssm.service.impl;

import com.it18zhang.ssm.domain.CallLog;
import com.it18zhang.ssm.domain.CallLogRange;
import com.it18zhang.ssm.service.CallLogService;
import com.it18zhang.ssm.service.PersonService;
import com.it18zhang.ssm.util.CallLogUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 呼叫日志
 */
@Service("callLogService")
public class CallLogServiceImpl implements CallLogService{
/*    //注入PersonService
    @Resource(name="personService")
    private PersonService ps ;*/

    //注入PersonService这里从Map缓存中取,速度更快
    @Resource(name="personServiceCache")
    private PersonService ps ;

    private Table table ;
    public CallLogServiceImpl(){
        try {
            Configuration conf = HBaseConfiguration.create();
            Connection conn = ConnectionFactory.createConnection(conf);
            TableName name = TableName.valueOf("ns1:calllogs");
            table = conn.getTable(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *查询所有log
     */
    public List<CallLog> findAll() {
        List<CallLog> list = new ArrayList<CallLog>();
        try {
            Scan scan = new Scan();
            ResultScanner rs = table.getScanner(scan);
            Iterator<Result> it = rs.iterator();
            byte[] f = Bytes.toBytes("f1");
            byte[] caller = Bytes.toBytes("caller");
            byte[] callee = Bytes.toBytes("callee");
            byte[] callTime = Bytes.toBytes("callTime");
            byte[] callDuration = Bytes.toBytes("callDuration");
            CallLog log = null ;
            while(it.hasNext()){
                log = new CallLog();
                Result r = it.next();
                //设置主叫用户名
                String callerStr = Bytes.toString(r.getValue(f, caller));
                log.setCaller(callerStr);
                log.setCallerName(ps.selectNameByPhone(callerStr));
                //设置被叫用户名
                String calleeStr = Bytes.toString(r.getValue(f, callee));
                log.setCallee(calleeStr);
                log.setCalleeName(ps.selectNameByPhone(calleeStr));

                log.setCallTime(Bytes.toString(r.getValue(f, callTime)));
                log.setCallDuration(Bytes.toString(r.getValue(f, callDuration)));
                list.add(log);
            }
            return list ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照范围查询通话时间
     * @param ranges
     * @return
     */
    public List<CallLog> findCallLog(String call, List<CallLogRange> ranges){
        List<CallLog> list = new ArrayList<CallLog>();
        try {
            for (CallLogRange r : ranges){
                Scan scan = new Scan();
                //设置扫描起始行
                scan.setStartRow(Bytes.toBytes(CallLogUtil.getStartRowkey(call, r.getStartPoint(),100)));
                //设置扫描结束行
                scan.setStopRow(Bytes.toBytes(CallLogUtil.getStopRowkey(call, r.getStartPoint(),r.getEndPoint(),100)));

                ResultScanner rs = table.getScanner(scan);
                Iterator<Result> it = rs.iterator();
                byte[] f = Bytes.toBytes("f1");

                byte[] caller = Bytes.toBytes("caller");
                byte[] callee = Bytes.toBytes("callee");
                byte[] callTime = Bytes.toBytes("callTime");
                byte[] callDuration = Bytes.toBytes("callDuration");
                CallLog log = null ;
                while(it.hasNext()){
                    log = new CallLog();
                    Result result = it.next();
                    //rowkey
                    String rowkey = Bytes.toString(result.getRow());
                    String flag = rowkey.split(",")[3];

                    log.setFlag(flag = "0".equals(flag)? "主叫":"被叫");
                    log.setCaller(Bytes.toString(result.getValue(f, caller)));
                    log.setCallee(Bytes.toString(result.getValue(f, callee)));
                    log.setCallTime(Bytes.toString(result.getValue(f, callTime)));
                    log.setCallDuration(Bytes.toString(result.getValue(f, callDuration)));
                    list.add(log);
                }

            }
            return list ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }






}

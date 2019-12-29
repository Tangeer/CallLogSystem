package com.it18zhang.ssm.hive;

import com.it18zhang.ssm.domain.CallLog;
import com.it18zhang.ssm.domain.CallLogStat;
import com.it18zhang.ssm.service.CallLogService;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：Tanger.
 * @Date ：Created in 10:09 2019/10/7
 */

@Service("hiveCallLogService")
public class HiveCallLogService {
    //hiveserver2连接串
    private static String url = "jdbc:hive2://s166:10000/mydb";
    //驱动程序类
    private static String driverClass = "org.apache.hive.jdbc.HiveDriver";

    static {
        try{
            Class.forName(driverClass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 查询最近的通话记录，使用hive进行mr查询
     */
    public CallLog findLatestCallLog(String phoneNum){
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from ext_calllogs_in_hbase where id like '%"+ phoneNum +"%' order by callTime desc limit 1");
            CallLog log = null;
            if (rs.next()){
                log = new CallLog();
                log.setCaller(rs.getString("caller"));
                log.setCallee(rs.getString("callee"));
                log.setCallTime(rs.getString("callTime"));
                log.setCallDuration(rs.getString("callDuration"));
            }
            rs.close();
            return log;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询指定人员指定年份中各个月份的通话次数
     * @param caller
     * @param year
     * @return
     */
    public List<CallLogStat> statCallLogsCount(String caller, String year){
        try {
            List<CallLogStat> list = new ArrayList<CallLogStat>();
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select count(*) ,substr(calltime,1,6) from ext_calllogs_in_hbase where caller = '" + caller +"' and substr(calltime,1,4) == '" + year + "' group by substr(calltime,1,6)");
            while (rs.next()){
                CallLogStat logSt = new CallLogStat();
                logSt.setCount(rs.getInt(1));
                logSt.setYearMonth(rs.getString(2));
                list.add(logSt);
            }
            rs.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

package com.it18zhang.ssm.test;

import com.alibaba.fastjson.JSON;
import com.it18zhang.ssm.domain.Person;
import com.it18zhang.ssm.service.PersonService;
import com.it18zhang.ssm.util.CharUtil;
import javafx.application.Application;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.json.JSONArray;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TestPersonService {
    public static Map<String, String> callers = new HashMap<String, String>();
    public static ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
    public static PersonService ps = (PersonService) ac.getBean("personService");

    static {
        callers.put("15810092493", "史玉龙");
        callers.put("18000696806", "赵贺彪");
        callers.put("15151889601", "张倩 ");
        callers.put("13269361119", "王世昌");
        callers.put("15032293356", "张涛");
        callers.put("17731088562", "张阳");
        callers.put("15338595369", "李进全");
        callers.put("15733218050", "杜泽文");
        callers.put("15614201525", "任宗阳");
        callers.put("15778423030", "梁鹏");
        callers.put("18641241020", "郭美彤");
        callers.put("15732648446", "刘飞飞");
        callers.put("13341109505", "段光星");
        callers.put("13560190665", "唐会华");
        callers.put("18301589432", "杨力谋");
        callers.put("13520404983", "温海英");
        callers.put("18332562075", "朱尚宽");
        callers.put("18620192711", "刘能宗");
    }
    @Test
    public void insertPerson(){
        for (Map.Entry<String,String> e : callers.entrySet()){
            Person p = new Person();
            p.setName(e.getValue());
            p.setPhone(e.getKey());
            ps.insert(p);
        }
    }

    @Test
    public void findAllPerson(){
        List<Person> list = ps.selectAll();
//        for (Person p : list){
//            System.out.println(p.getName() + "===" + p.getPhone());
//        }

        String json = JSON.toJSONString(list.get(0));
        System.out.println(json);
    }
    @Test
    public void TestNameByPhone(){
        System.out.println(ps.selectNameByPhone("15338595369"));
    }


}

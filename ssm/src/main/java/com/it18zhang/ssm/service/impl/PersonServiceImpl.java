package com.it18zhang.ssm.service.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.dao.impl.PersonDaoImpl;
import com.it18zhang.ssm.domain.Item;
import com.it18zhang.ssm.domain.Order;
import com.it18zhang.ssm.domain.Person;
import com.it18zhang.ssm.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService{
    //注入
    @Resource(name = "personDao")
    private BaseDao<Person> personDao ;

    public String selectNameByPhone(String phone){
        return ((PersonDaoImpl)getDao()).selectNameByPhone(phone);
    }




}
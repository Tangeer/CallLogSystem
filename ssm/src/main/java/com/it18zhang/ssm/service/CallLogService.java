package com.it18zhang.ssm.service;

import com.it18zhang.ssm.domain.CallLog;
import com.it18zhang.ssm.domain.CallLogRange;

import java.util.List;

/**
 *
 */
public interface CallLogService {
    public List<CallLog> findAll();

    public List<CallLog> findCallLog(String call, List<CallLogRange> ranges);
}

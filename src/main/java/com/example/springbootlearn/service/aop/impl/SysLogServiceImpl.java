package com.example.springbootlearn.service.aop.impl;

import com.example.springbootlearn.dao.aop.SysLogDao;
import com.example.springbootlearn.entity.sys.SysLogBean;
import com.example.springbootlearn.service.aop.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2024/1/30 16:17:13
 * @Version 1.0
 */

@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogDao sysLogDao;

    public SysLogServiceImpl(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    /**
     * 保存数据
     *
     * @param sysLog
     */
    @Override
    public void save(SysLogBean sysLog) {
        sysLogDao.save(sysLog);
    }
}

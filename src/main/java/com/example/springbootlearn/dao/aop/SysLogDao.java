package com.example.springbootlearn.dao.aop;

import com.example.springbootlearn.entity.sys.SysLogBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2024/1/30 16:47:50
 * @Version 1.0
 */

@Mapper
public interface SysLogDao {

    void save(SysLogBean sysLog);
}

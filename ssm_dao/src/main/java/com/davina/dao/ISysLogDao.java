package com.davina.dao;

import com.davina.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface ISysLogDao {

    @Insert("INSERT INTO sys_log VALUES(SEQ_LOG.nextval,#{visitTime},#{username},#{ip},#{method})")
    void save(SysLog sysLog);
}

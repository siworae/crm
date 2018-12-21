package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.dto.SaleChanceDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleChanceMapper extends BaseDao<SaleChanceDto> {

    Integer updateByParams(@Param("sid") Integer sid, @Param("devResult") Integer devResult);
}
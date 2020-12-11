package com.transaction.core.match.mapper;

import com.transaction.core.match.entites.Entrust;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectKey;

public interface EntrustMapper {
    @Insert({
            "insert into entrust (user_id, entrust_no, ",
            "count, commodity_code, ",
            "bs, price, stage)",
            "values (#{userId,jdbcType=BIGINT}, #{entrustNo,jdbcType=VARCHAR}, ",
            "#{count,jdbcType=INTEGER}, #{commodityCode,jdbcType=VARCHAR}, ",
            "#{bs,jdbcType=BIT}, #{price,jdbcType=DECIMAL}, #{stage,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Entrust record);

    @InsertProvider(type = EntrustSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(Entrust record);
}
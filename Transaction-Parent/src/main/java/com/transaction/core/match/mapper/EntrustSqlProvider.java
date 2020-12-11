package com.transaction.core.match.mapper;

import com.transaction.core.match.entites.Entrust;
import org.apache.ibatis.jdbc.SQL;

public class EntrustSqlProvider {

    public String insertSelective(Entrust record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("entrust");

        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }

        if (record.getEntrustNo() != null) {
            sql.VALUES("entrust_no", "#{entrustNo,jdbcType=VARCHAR}");
        }

        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=INTEGER}");
        }

        if (record.getCommodityCode() != null) {
            sql.VALUES("commodity_code", "#{commodityCode,jdbcType=VARCHAR}");
        }

        if (record.getBs() != null) {
            sql.VALUES("bs", "#{bs,jdbcType=BIT}");
        }

        if (record.getPrice() != null) {
            sql.VALUES("price", "#{price,jdbcType=DECIMAL}");
        }

        if (record.getStage() != null) {
            sql.VALUES("stage", "#{stage,jdbcType=INTEGER}");
        }

        return sql.toString();
    }
}
package com.transaction.core.match;

import com.transaction.core.match.core.CoreTransaction;
import com.transaction.core.match.entites.Entrust;
import com.transaction.core.match.uitils.EntrustUntis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;

@MapperScan("com.transaction.core.match.mapper")
@SpringBootApplication
public class CoreMatchApplication {

    private static final String commodityCode = "001";

    public static void main(String[] args) {
        SpringApplication.run(CoreMatchApplication.class, args);


        ArrayList<Entrust> buyList = new ArrayList<>();
        ArrayList<Entrust> sellList = new ArrayList<>();
        Init(buyList, sellList);

        CoreTransaction.getInstance().testInit(true, commodityCode, buyList);
        CoreTransaction.getInstance().testInit(false, commodityCode, sellList);
        CoreTransaction.getInstance().start();
    }

    private static void Init(ArrayList<Entrust> buyList, ArrayList<Entrust> sellList) {

        Entrust buyEntrust = new Entrust().setBs(true)
                .setCommodityCode(commodityCode)
                .setUserId(2000001L)
                .setCount(100)
                .setPrice(new BigDecimal("10"))
                .setEntrustNo(EntrustUntis.create())
                .setStage(0);


        Entrust sellEntrust = new Entrust().setBs(false)
                .setCommodityCode(commodityCode)
                .setUserId(1000001L)
                .setCount(1000)
                .setEntrustNo(EntrustUntis.create())
                .setPrice(new BigDecimal("10"))
                .setStage(0);

        buyList.add(buyEntrust);
        sellList.add(sellEntrust);
    }
}


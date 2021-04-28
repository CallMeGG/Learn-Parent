package com.transaction.core.match.uitils;

import java.util.UUID;

/**
 * Title: CreadeEntrust
 * Description:
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/18 18:30
 */
public class EntrustUntis {

    /**
     * 获取个长为10的随机数
     *
     * @return
     */
    public static String create() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

}

package com.transaction.core.match.core.task;

import com.transaction.core.match.core.CoreTransaction;

/**
 * Title: demo
 * Description: 这个没用了
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/13 14:12
 */
public class TreadImp implements Runnable {

    private static ThreadLocal local;

    @Override
    public synchronized void run() {

        while (true) {
            //没有买方或者卖方时候等待
            CoreTransaction.getInstance().match();
        }
    }
}

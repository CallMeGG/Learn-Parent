package com.transaction.core.match.core;

import com.transaction.core.match.entites.Entrust;
import com.transaction.core.match.entites.Orders;
import com.transaction.core.match.core.services.TranscationService;
import com.transaction.core.match.uitils.EntrustUntis;
import com.transaction.core.match.uitils.SpringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: Transaction
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author GYP
 * @version 1.0
 * @date 2019/2/15 17:57
 */
public class CoreTransaction {

    private static CoreTransaction transaction;

    private static Thread thread;

    /**
     * 加上volatile来保证编译器逐一进行编译并产生相应的机器码，而不会被编译器优化成一条机器码
     */
    private volatile static boolean hasSame;

    private TranscationService transcationService;

    private ConcurrentHashMap<String, List<Entrust>> buyerMap = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, List<Entrust>> sellerMap = new ConcurrentHashMap<>();

    /**
     * 很多地方用到0
     */
    private static final int ZORE = 0;

    public void testInit(boolean bs, String commodityCode, List<Entrust> list) {
        if (bs) {
            buyerMap.put(commodityCode, list);
        } else {
            sellerMap.put(commodityCode, list);
        }
    }

    public void match() {
        while (true) {
            if (!hasSame()) {
                try {
                    synchronized (thread) {
                        thread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                buyerMap.keySet().forEach(key -> {
                    List<Entrust> sellerList = sellerMap.get(key);
                    List<Entrust> buyerList = buyerMap.get(key);
                    if (buyerList != null) {

                        Iterator<Entrust> iteratorBuy = buyerList.iterator();
                        while (iteratorBuy.hasNext()) {
                            Entrust buyer = iteratorBuy.next();

                            Iterator<Entrust> iteratorSell = sellerList.iterator();
                            while (iteratorSell.hasNext()) {
                                Entrust seller = iteratorSell.next();
                                boolean isBreak = false;
                                int dealCount = buyer.getCount();
                                if (buyer.getCount() <= seller.getCount()) {
                                    int i = seller.getCount() - buyer.getCount();
                                    buyer.setCount(ZORE);
                                    seller.setCount(i);
                                    isBreak = true;
                                } else {
                                    dealCount = buyer.getCount() - seller.getCount();
                                    buyer.setCount(dealCount);
                                    seller.setCount(ZORE);
                                }
                                System.out.println("买家:" + buyer.getUserId() + ",委托单号:" + buyer.getEntrustNo() + ",买了:" + dealCount + ",还剩" + buyer.getCount());
                                System.out.println("卖家:" + seller.getUserId() + ",委托单号:" + seller.getEntrustNo() + ",卖了:" + dealCount + ",还剩" + seller.getCount());
                                System.out.println();

                                Orders orderBuy = new Orders()
                                        .setUserId(buyer.getUserId())
                                        .setAddTime(new Date())
                                        .setBs(true)
                                        .setEntrustNo(buyer.getEntrustNo())
                                        .setPrice(seller.getPrice())
                                        .setType(0)
                                        .setOrderNo(new Date().getTime() + "")
                                        .setCount(dealCount)
                                        .setTatolPrice(seller.getPrice().multiply(new BigDecimal(dealCount)));
                                Orders orderSell = new Orders()
                                        .setUserId(seller.getUserId())
                                        .setAddTime(new Date())
                                        .setBs(false)
                                        .setEntrustNo(seller.getEntrustNo())
                                        .setPrice(seller.getPrice())
                                        .setType(0)
                                        .setOrderNo(new Date().getTime() + "")
                                        .setCount(dealCount)
                                        .setTatolPrice(seller.getPrice().multiply(new BigDecimal(dealCount)));
                                transcationService.addOrder(orderBuy);
                                transcationService.addOrder(orderSell);

                                if (seller.getCount() == 0) {
                                    iteratorSell.remove();
                                    continue;
                                }
                                if (isBreak) {
                                    break;
                                }
                            }
                            if (buyer.getCount() == 0) {
                                iteratorBuy.remove();
                                continue;
                            }
                        }
                    }
                });
                // delete();
            }
        }
    }

    /**
     * 下委托单
     *
     * @param commodityCode
     * @param bs
     * @param count
     * @param price
     * @return
     */
    public Boolean sendEnturst(String commodityCode, boolean bs, int count, BigDecimal price, Long customer) {

        if (bs) {
            add(buyerMap, bs, commodityCode, count, price, customer);
        } else {
            add(sellerMap, bs, commodityCode, count, price, customer);
        }
        synchronized (thread) {
            thread.notify();
        }
        return null;
    }

    /**
     * 添加新的委托单
     *
     * @param map
     * @param bs
     * @param commodityCode
     * @param count
     * @param price
     * @param customer
     */
    private void add(ConcurrentHashMap<String, List<Entrust>> map, boolean bs, String commodityCode, int count, BigDecimal price, Long customer) {

        Entrust entrust = new Entrust()
                .setBs(bs)
                .setCommodityCode(commodityCode)
                .setCount(count)
                .setPrice(price)
                .setUserId(customer)
                .setEntrustNo(EntrustUntis.create())
                .setStage(0);
        map.keySet().forEach(key -> {
            if (key.equals(commodityCode)) {
                map.get(key).add(entrust);
            }
        });

        transcationService.addEntrust(entrust);
    }

    public void start() {
        thread = new Thread(() -> match());
        thread.start();
    }

    /**
     * 是否有相同的商品
     *
     * @return
     */
    private boolean hasSame() {
        hasSame = false;
        if (buyerMap.size() == 0 || sellerMap.size() == 0) {
            return hasSame;
        }
        buyerMap.keySet().forEach(key -> {
            List<Entrust> sellerList = sellerMap.get(key);
            List<Entrust> buyerList = buyerMap.get(key);
            if (!CollectionUtils.isEmpty(sellerList) && !CollectionUtils.isEmpty(buyerList)) {
                hasSame = true;
                return;
            }
        });
        return hasSame;
    }

    private CoreTransaction() {
        if (transcationService == null) {
            transcationService = SpringUtils.getBean(TranscationService.class);
        }
    }

    public static CoreTransaction getInstance() {
        if (transaction == null) {
            transaction = new CoreTransaction();
        }
        return transaction;
    }

}

package com.gyp.jedis.api.channel.jedis;

import com.gyp.jedis.api.system.ApplicationUtil;
import com.gyp.jedis.api.system.config.SystemConfig;
import redis.clients.jedis.Jedis;

/**
 * Title: JedisChannel
 * Company: GYP
 *
 * @author GYP
 * @version 1.0
 * @date 2018/12/17 13:41
 */
public class JedisChannel {

    private static Jedis jedis = null;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";


    /**
     * 获取jedis实例
     *
     * @return
     */
    public static Jedis getSingleton() {
        if (jedis == null) {
            SystemConfig bean = ApplicationUtil.getBean(SystemConfig.class);
            jedis = new Jedis(bean.getRedisIp(), bean.getRedisPort());
        }
        return jedis;
    }


    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁
     * @param requestId  请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
}

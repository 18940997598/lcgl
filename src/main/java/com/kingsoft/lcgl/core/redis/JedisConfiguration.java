package com.kingsoft.lcgl.core.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyongkui on 2015/12/2.
 */
@Configuration
@ConfigurationProperties(prefix="jedis")
public class JedisConfiguration {

    private int maxTotal;
    private int maxIdle;
    private int maxWaitMillis;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private String host;
    private String port;
    private int timeout;
    private String instanceName;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestOnReturn(testOnReturn);
        return jedisPoolConfig;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool(JedisPoolConfig jedisPoolConfig) {
        List<JedisShardInfo> jedisShardInfos = new ArrayList<>();
        String[] hostArr = host.split(",");
        String[] portArr = port.split(",");
        String[] instanceNameArr = instanceName.split(",");
        for(int i=0;i<hostArr.length;i++) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(hostArr[i],Integer.valueOf(portArr[i]),timeout,instanceNameArr[i]);
            jedisShardInfos.add(jedisShardInfo);
        }
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,jedisShardInfos);
        return shardedJedisPool;
    }
}

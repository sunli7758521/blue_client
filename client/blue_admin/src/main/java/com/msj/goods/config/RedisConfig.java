/*
package com.msj.goods.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


*/
/**
 * 类RedisConfig的功能描述:
 * Redis配置
 * @auther hxy
 * @date 2017-11-15 21:49:31
 *//*

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    //private static Logger logger = Logger.getLogger(RedisConfig.class);

    private String hostName;

    private int port;

    private String password;

    private int timeout;*/
/**//*


    @Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public JedisPool getJedisPool(){
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config,hostName,port,timeout,password);
       // logger.info("init JredisPool ...");
        return pool;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
*/

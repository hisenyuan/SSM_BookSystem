package com.hisen.dao;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 使用第三方内存数据库Redis作为二级缓存
 * 模仿Ehcache写的一个RedisCache缓存类
 * Created by hisenyuan on 2017/5/18 at 15:55.
 */
public class RedisCache implements Cache {

  private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
  private static JedisConnectionFactory jedisConnectionFactory;
  private final String id;
  /**
   * 写锁
   */
  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  public RedisCache(final String id) {
    if (id == null) {
      throw new IllegalArgumentException("Cache instances require an ID");
    }
    logger.info("MybatisRedisCache:id=" + id);
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  /**
   * 存放对象
   */
  public void putObject(Object key, Object value) {
    JedisConnection connection = null;
    try {
      connection = jedisConnectionFactory.getConnection();
      RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
      connection.set(serializer.serialize(key), serializer.serialize(value));
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }

  /**
   * 获取对象
   */
  public Object getObject(Object key) {
    Object result = null;
    JedisConnection connection = null;
    try {
      connection = jedisConnectionFactory.getConnection();
      RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
      result = serializer.deserialize(connection.get(serializer.serialize(key)));
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return result;
  }

  /**
   * 删除对象
   */
  public Object removeObject(Object key) {
    JedisConnection connection = null;
    Object result = null;
    try {
      connection = jedisConnectionFactory.getConnection();
      RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
      result = connection.expire(serializer.serialize(key), 0);
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return result;
  }

  /**
   * 获得锁
   */
  public ReadWriteLock getReadWriteLock() {
    return this.readWriteLock;
  }

  /**
   * 获取数据库大小
   */
  public int getSize() {
    int result = 0;
    JedisConnection connection = null;
    try {
      connection = jedisConnectionFactory.getConnection();
      result = Integer.valueOf(connection.dbSize().toString());
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
    return result;
  }

  /**
   * 配置连接工厂
   */
  public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
    RedisCache.jedisConnectionFactory = jedisConnectionFactory;
  }

  /**
   * 清空
   */
  public void clear() {
    JedisConnection connection = null;
    try {
      connection = jedisConnectionFactory.getConnection();
      connection.flushDb();
      connection.flushAll();
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        connection.close();
      }
    }
  }
}

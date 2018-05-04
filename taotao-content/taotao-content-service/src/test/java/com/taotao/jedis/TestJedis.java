package com.taotao.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: minGW
 * @Date: 2018/4/20 10:57
 * @Description:
 */
public class TestJedis {

    @Test
    public void testJedis() throws Exception
    {
        //第一步：创建一个Jedis对象，需要指定服务器的ip及端口
        Jedis jedis = new Jedis("10.65.1.16",6379);

        //第二步：使用Jedis对象操作数据库，每个redis命令对应一个方法
        jedis.set("hello","minGW");
        String ans = jedis.get("hello");

        //第三步：打印结果
        System.out.println(ans);

        //第四步：关闭Jedis
        jedis.close();
    }

    @Test
    public void testJedisPool() throws Exception
    {
        //第一步：创建一个JedisPool对象(单例)，需要指定服务端的ip和端口
        JedisPool jedisPool = new JedisPool("10.65.1.16",6379);

        //第二步：从JedisPool中获取Jedis对象，获取连接对象
        Jedis jedis = jedisPool.getResource();

        //第三步：使用Jedis操作数据库(方法级别使用)
        jedis.set("JedisPool","minGW");
        String ans = jedis.get("JedisPool");
        System.out.println(ans);

        //第四步：操作完之后关闭Jedis连接对象，连接池回收资源
        jedis.close();

        //第五步：系统关闭前关闭JedisPool数据库连接池
        jedisPool.close();

    }
}

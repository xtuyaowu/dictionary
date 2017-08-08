package cc.chenzhihao.dictionary.dao;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Des : <dictionary> : 公司缓存dao
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/5 16:40
 */
@Repository
public class CompanyRedisDao implements BaseDao<String> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String company, String key) {
        redisTemplate.opsForList().rightPush(key, company);
    }

    @Override
    public void save(List<String> t, String key) {
        redisTemplate.opsForList().rightPushAll(key, t);
    }

    @Override
    public void clean(String key) {
        redisTemplate.opsForList().trim(key, 1, size(key));
    }

    @Override
    public Long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public String pop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public List<String> show(String key) {
        return redisTemplate.opsForList().range(key, 0, size(key));
    }
}

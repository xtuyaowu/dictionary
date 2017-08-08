package cc.chenzhihao.dictionary.dao;

import java.util.List;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/5 16:35
 */
public interface BaseDao<T> {

    void save(T t, String key);

    void save(List<T> t, String key);

    void clean(String key);

    Long size(String key);

    T pop(String key);

    List<T> show(String key);

}

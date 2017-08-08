package cc.chenzhihao.dictionary.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * Des : <dictionary-scaffold> :自定义扩展AdaptableJobFactory实现任务工厂,方便任务对象注入spring容器管理的Bean
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/24 20:44
 */
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    /**
     * rewrite AdaptableJobFactory 类任务创建方法, 将任务对象注入到Spring容器中
     *
     * @param bundle
     * @return
     * @throws Exception
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

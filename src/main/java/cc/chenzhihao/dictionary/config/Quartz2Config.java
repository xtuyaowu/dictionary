package cc.chenzhihao.dictionary.config;

import cc.chenzhihao.dictionary.quartz.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Des : <dictionary-scaffold> : Quartz2定时任务配置
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/24 20:49
 */
@Configuration
public class Quartz2Config {

    @Bean
    public JobFactory jobFactory() {
        return new JobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory());
        return schedulerFactoryBean;
    }


}

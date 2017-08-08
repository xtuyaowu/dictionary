package cc.chenzhihao.dictionary.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * Des : <dictionary-scaffold> : mybatis扫描配置
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/4 23:01
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
@MapperScan(basePackages = {"cc.chenzhihao.dictionary.dao.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisScanConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MybatisScanConfiguration.class);

    public MybatisScanConfiguration() {
        logger.info("*************************MybatisScanConfiguration***********************");
    }

}

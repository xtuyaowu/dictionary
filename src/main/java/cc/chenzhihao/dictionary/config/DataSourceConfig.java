package cc.chenzhihao.dictionary.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

/**
 * Des : <dictionary-scaffold> : Druid数据源配置类
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/4 20:49
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.druid.filters}")
    private String filters;
    @Value("${spring.datasource.druid.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.druid.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.druid.maxWait}")
    private Integer maxWait;
    @Value("${spring.datasource.druid.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.druid.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.druid.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.druid.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.druid.PoolPreparedStatements}")
    private Boolean PoolPreparedStatements;
    @Value("${spring.datasource.druid.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;
    @Value("${spring.datasource.druid.removeAbandoned}")
    private Boolean removeAbandoned;
    @Value("${spring.datasource.druid.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;
    @Value("${spring.datasource.druid.logAbandoned}")
    private Boolean logAbandoned;


    @Value("${spring.datasource.druid.url-mapping}")
    private String urlMapping;
    @Value("${spring.datasource.druid.loginUsername}")
    private String loginUsername;
    @Value("${spring.datasource.druid.loginPassword}")
    private String loginPassword;

    /**
     * druid初始化
     *
     * @throws SQLException
     */
    @Primary //默认数据源
    @Bean(name = "dataSource", destroyMethod = "close")
    public DruidDataSource Construction() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverName);
        //配置最大连接
        dataSource.setMaxActive(maxActive);
        //配置初始连接
        dataSource.setInitialSize(initialSize);
        //配置最小连接
        dataSource.setMinIdle(minIdle);
        //连接等待超时时间
        dataSource.setMaxWait(maxWait);
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测是否有效的sql
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        //打开PSCache,并指定每个连接的PSCache大小
        dataSource.setPoolPreparedStatements(PoolPreparedStatements);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        //配置sql监控的filter
        dataSource.setFilters(filters);
        dataSource.setRemoveAbandoned(removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        dataSource.setLogAbandoned(logAbandoned);
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings(urlMapping);
        registrationBean.addInitParameter("loginUsername", loginUsername);
        registrationBean.addInitParameter("loginPassword", loginPassword);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}

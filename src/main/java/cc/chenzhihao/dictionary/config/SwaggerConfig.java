package cc.chenzhihao.dictionary.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Des : <dictionary-scaffold> : SwaggerUi配置
 *
 * @author chenzhihao
 * @version 创建时间: 2017/1/5 00:10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.config.apiInfo.title}")
    private String apiInfoTitle;
    @Value("${swagger.config.apiInfo.description}")
    private String apiInfoDescription;
    @Value("${swagger.config.apiInfo.version}")
    private String apiInfoVersion;
    @Value("${swagger.config.apiInfo.termsOfServiceUrl}")
    private String apiInfoTermsOfServiceUrl;
    @Value("${swagger.config.apiInfo.contact.name}")
    private String apiInfoContactName;
    @Value("${swagger.config.apiInfo.contact.url}")
    private String apiInfoContactUrl;
    @Value("${swagger.config.apiInfo.contact.email}")
    private String apiInfoContactEmail;
    @Value("${swagger.config.apiInfo.license}")
    private String apiInfoLicense;
    @Value("${swagger.config.apiInfo.licenseUrl}")
    private String apiInfoLicenseUrl;
    @Value("${swagger.config.host}")
    private String host;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .select()
                .paths(paths())
                .build()
                .apiInfo(apiInfo());
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> paths() {
        return Predicates.or(Predicates.containsPattern("/api/*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                apiInfoTitle,
                apiInfoDescription,
                apiInfoVersion,
                apiInfoTermsOfServiceUrl,
                new Contact(
                        apiInfoContactName,
                        apiInfoContactUrl,
                        apiInfoContactEmail
                ),
                apiInfoLicense,
                apiInfoLicenseUrl
        );
    }

}

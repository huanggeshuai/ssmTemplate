package com.huang.sys.swagger;

import com.huang.sys.info.SysInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @auther huang
 * @create 2019-03-06 10:55
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Autowired
    private SysInfoBean sysInfoBean;

    @Bean
    public Docket apis(){
        return new Docket(DocumentationType.SWAGGER_2).
                select().             //选中路径和api进行监控
                apis(RequestHandlerSelectors.any()).   //对所有的api进行监控
                paths(PathSelectors.any()).       //对所有的路径进行监控
                build().
                apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        Contact contact=new Contact(sysInfoBean.getName(),sysInfoBean.getUrl(),sysInfoBean.getEmail());
        ApiInfo apiInfo=new ApiInfoBuilder().
                contact(contact).
                title(sysInfoBean.getApiName()).
                description(sysInfoBean.getDescription()).
                version(sysInfoBean.getVesion()).
                build();
        return apiInfo;
    }

}

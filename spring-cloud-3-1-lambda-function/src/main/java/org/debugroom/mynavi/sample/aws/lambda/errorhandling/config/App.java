package org.debugroom.mynavi.sample.aws.lambda.errorhandling.config;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.CloudFormationStackResolver;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.ServiceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ServiceProperties serviceProperties(){
        return new ServiceProperties();
    }

    @Bean
    CloudFormationStackResolver cloudFormationStackResolver(){
        return new CloudFormationStackResolver();
    }

    @Bean
    AWSSimpleSystemsManagement awsSimpleSystemsManagement(){
        return AWSSimpleSystemsManagementClientBuilder.standard().defaultClient();
    }
}

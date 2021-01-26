package org.debugroom.mynavi.sample.aws.lambda.errorhandling.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.CloudFormationStackResolver;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.ServiceProperties;

@EnableSqs
@Configuration
public class SqsConfig {

    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    CloudFormationStackResolver cloudFormationStackResolver;

    @Autowired
    AmazonSQSAsync amazonSQSAsync;

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration(){
        return new AwsClientBuilder.EndpointConfiguration(
                cloudFormationStackResolver.getExportValue(serviceProperties.getCloudFormation().getSqs().getEndpoint()),
                cloudFormationStackResolver.getExportValue(serviceProperties.getCloudFormation().getSqs().getRegion()));
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(){
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

}

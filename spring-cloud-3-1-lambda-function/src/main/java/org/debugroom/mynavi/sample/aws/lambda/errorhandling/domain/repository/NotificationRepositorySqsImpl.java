package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.CloudFormationStackResolver;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.ServiceProperties;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.SystemException;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.Notification;

import java.util.Locale;

@Component
public class NotificationRepositorySqsImpl implements NotificationRepository {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    CloudFormationStackResolver cloudFormationStackResolver;

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void save(Notification notification) {
        String queue = cloudFormationStackResolver.getExportValue(
                serviceProperties.getCloudFormation().getSqs().getQueueName());
        String message = null;
        try{
            message = objectMapper.writeValueAsString(notification);
            queueMessagingTemplate.convertAndSend(queue, message);
        } catch (JsonProcessingException e){
            new SystemException("SE0002", messageSource.getMessage("SE0002",
                    new String[]{serviceProperties.getCloudFormation().getSqs().getEndpoint() + "/" + queue, message},
                    Locale.getDefault()), e);
        }
    }

}

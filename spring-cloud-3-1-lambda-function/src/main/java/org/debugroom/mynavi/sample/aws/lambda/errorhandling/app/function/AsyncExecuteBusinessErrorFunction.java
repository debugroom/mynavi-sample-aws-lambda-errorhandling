package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import java.util.Map;
import java.util.function.Function;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.model.SampleResource;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.BusinessException;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.ErrorAsyncNotification;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository.NotificationRepository;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service.CheckParameterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncExecuteBusinessErrorFunction implements Function<Map<String, Object>, Message<SampleResource>> {

    @Autowired
    CheckParameterService checkParameterService;

    @Autowired
    @Qualifier("notificationRepositorySqsImpl")
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Message<SampleResource> apply(Map<String, Object> stringObjectMap) {

        log.info(this.getClass().getName() + " has started!");
        log.info("[Input]" + stringObjectMap.toString());

        try{
            checkParameterService.execute(null);
        }catch (BusinessException e){
            notificationRepository.save(ErrorAsyncNotification.builder()
                    .userId(userRepository.getId(stringObjectMap))
                    .message(e.getMessage())
                    .build());
        }
        return MessageBuilder.withPayload(
                SampleResource.builder().message("This code never be reached.").build()).build();
    }

}

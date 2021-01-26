package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import lombok.extern.slf4j.Slf4j;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.model.SampleResource;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.SystemException;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.ErrorAsyncNotification;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository.NotificationRepository;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository.UserRepository;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service.CreateSystemErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class AsyncExecuteSystemErrorFunction implements Function<Map<String, Object>, Message<SampleResource>> {

    @Autowired
    CreateSystemErrorService createSystemErrorService;

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
            createSystemErrorService.execute();
        }catch (RuntimeException e){
            notificationRepository.save(ErrorAsyncNotification.builder()
            .userId(userRepository.getId(stringObjectMap))
            .message(e.getMessage())
            .build());
            throw new SystemException("", e);
        }

        return MessageBuilder.withPayload(SampleResource.builder()
                .message("This code would never be reached.").build()).build();
    }

}

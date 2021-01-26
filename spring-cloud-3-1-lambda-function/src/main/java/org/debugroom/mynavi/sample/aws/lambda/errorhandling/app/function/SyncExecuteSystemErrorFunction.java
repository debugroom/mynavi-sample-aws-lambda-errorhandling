package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service.CreateSystemErrorService;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.model.SampleResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncExecuteSystemErrorFunction implements Function<Map<String, Object>, Message<SampleResource>> {

    @Autowired
    CreateSystemErrorService createSystemErrorService;

    @Override
    public Message<SampleResource> apply(Map<String, Object> stringObjectMap) {

        log.info(this.getClass().getName() + " has started!");
        log.info("[Input]" + stringObjectMap.toString());

        createSystemErrorService.execute();

        return MessageBuilder.withPayload(SampleResource.builder()
                .message("This code would never be reached.").build()).build();

    }

}

package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.model.SampleResource;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.BusinessException;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service.CheckParameterService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SyncExecuteBusinessErrorFunction implements Function<Map<String, Object>, Message<SampleResource>> {

    @Autowired
    CheckParameterService checkParameterService;

    @Override
    public Message<SampleResource> apply(Map<String, Object> stringObjectMap) {

        log.info(this.getClass().getName() + " has started!");
        log.info("[Input]" + stringObjectMap.toString());

        Map<String, Object> queryStringParameters = (Map)stringObjectMap.get("queryStringParameters");
        try{
            checkParameterService.execute(queryStringParameters);
        }catch (BusinessException e){
            Map<String, Object> headers = new HashMap<>();
            headers.put("statusCode", 400);
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            return MessageBuilder.createMessage(
                    SampleResource.builder().message("No query string parameters.").build(), messageHeaders);
        }
        return MessageBuilder.withPayload(
                SampleResource.builder().message(queryStringParameters.toString()).build()).build();
    }

}

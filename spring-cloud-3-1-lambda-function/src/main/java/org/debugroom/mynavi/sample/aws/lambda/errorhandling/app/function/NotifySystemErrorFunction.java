package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

import com.amazonaws.util.Base64;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.ServiceProperties;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.MattermostNotification;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class NotifySystemErrorFunction implements Function<Map<String, Object>, Message<String>> {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    @Qualifier("notificationRepositoryMattermostImpl")
    NotificationRepository notificationRepository;

    @Override
    public Message<String> apply(Map<String, Object> stringObjectMap) {

        log.info(this.getClass().getName() + " has started!");
        for(String key : stringObjectMap.keySet()){
            Object value = stringObjectMap.get(key);
            if(Objects.nonNull(value)){
                log.info("[Key]" + key + " [Value]" + value.toString());
            }else {
                log.info("[Key]" + key + " [Value]" + "null");
            }
        }
        Map<String, Object> decodeLogMap = decodeLog((Map)stringObjectMap.get("awslogs"));

        notificationRepository.save(MattermostNotification.builder()
                .text(decodeLogMap.toString())
                .channel(serviceProperties.getMattermost().getChannel())
                .build());

        return MessageBuilder.withPayload("Complete!").build();
    }

    private Map<String, Object> decodeLog(Map<String, Object> inputLogMap){
        byte[] compressedDecodeLogs = Base64.decode(
                ((String)inputLogMap.get("data")).getBytes(StandardCharsets.UTF_8));

        Map<String, Object> decodeLogMap = new HashMap<>();

        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedDecodeLogs);
            GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            decodeLogMap = objectMapper.readValue(stringBuilder.toString(), new TypeReference<Map<String, Object>>() {});
        } catch (IOException e){
            log.info(decodeLogMap.toString());
        }

        return decodeLogMap;

    }

}

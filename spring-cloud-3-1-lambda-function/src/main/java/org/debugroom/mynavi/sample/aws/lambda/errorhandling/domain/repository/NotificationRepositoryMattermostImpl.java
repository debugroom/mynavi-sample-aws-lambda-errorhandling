package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.ServiceProperties;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationRepositoryMattermostImpl implements NotificationRepository {

    @Autowired
    ServiceProperties serviceProperties;

    @Autowired
    AWSSimpleSystemsManagement awsSimpleSystemsManagement;

    @Override
    public void save(Notification message) {
        WebClient webClient = WebClient.builder()
                .baseUrl(getParameterFromParameterStore(
                        serviceProperties.getSystemsManagerParameterStore()
                                .getMattermost()
                                .getInCommingWebhook(), false))
                .build();
        webClient.post()
                .bodyValue(message)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    private String getParameterFromParameterStore(String paramName, boolean isEncripted){
        GetParameterRequest request = new GetParameterRequest();
        request.setName(paramName);
        request.setWithDecryption(isEncripted);
        GetParameterResult getParameterResult = awsSimpleSystemsManagement.getParameter(request);
        return getParameterResult.getParameter().getValue();
    }
}

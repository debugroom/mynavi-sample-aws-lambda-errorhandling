package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {

    CloudFormation cloudFormation = new CloudFormation();
    SystemsManagerParameterStore systemsManagerParameterStore = new SystemsManagerParameterStore();
    Mattermost mattermost = new Mattermost();

    @Data
    public class CloudFormation{
        Sqs sqs = new Sqs();
    }

    @Data
    public class SystemsManagerParameterStore{
        Mattermost mattermost = new Mattermost();
    }

    @Data
    public class Sqs{
        String endpoint;
        String region;
        String queueName;
    }

    @Data
    public class Mattermost{
        String inCommingWebhook;
        String channel;
    }

}

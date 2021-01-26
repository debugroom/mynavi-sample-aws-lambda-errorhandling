package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MattermostNotification implements Notification {

    private String text;
    private String channel;

}

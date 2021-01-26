package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.model.Notification;

public interface NotificationRepository {

    public void save(Notification message);

}

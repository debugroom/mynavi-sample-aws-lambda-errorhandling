package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service;

import java.util.Map;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.BusinessException;

public interface CheckParameterService {

    public void execute(Map<String, Object> requestParameters) throws BusinessException;

}

package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.BusinessException;

@Service
public class CheckParameterServiceImpl implements CheckParameterService {

    @Autowired
    MessageSource messageSource;

    @Override
    public void execute(Map<String, Object> input) throws BusinessException {
        if(Objects.isNull(input) || Objects.equals(input.size(), 0))  {
            throw new BusinessException("BE00001",
                messageSource.getMessage("BE0001", null, Locale.getDefault()));
        }
    }

}

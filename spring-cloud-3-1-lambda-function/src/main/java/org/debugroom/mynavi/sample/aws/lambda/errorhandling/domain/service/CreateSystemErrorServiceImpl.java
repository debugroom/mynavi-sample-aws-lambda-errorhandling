package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import org.debugroom.mynavi.sample.aws.lambda.errorhandling.common.exception.SystemException;

@Service
public class CreateSystemErrorServiceImpl implements CreateSystemErrorService {

    @Autowired
    MessageSource messageSource;

    @Override
    public void execute(){
        throw new SystemException("SE00001",
                messageSource.getMessage("SE0001", null, Locale.getDefault()));
    }

}

#!/usr/bin/env bash

#stack_name="mynavi-sample-lambda-errorhandling-cloudwatch"
stack_name="mynavi-sample-lambda-errorhandling-s3"
#stack_name="mynavi-sample-lambda-errorhandling-apigateway"
#stack_name="mynavi-sample-lambda-errorhandling-lambda"

aws cloudformation delete-stack --stack-name ${stack_name}
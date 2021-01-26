#!/usr/bin/env bash

stack_name="mynavi-sample-lambda-errorhandling-cloudwatch"
#stack_name="mynavi-sample-lambda-errorhandling-s3"
#stack_name="mynavi-sample-lambda-errorhandling-apigateway"
#stack_name="mynavi-sample-lambda-errorhandling-lambda"
#stack_name="mynavi-sample-lambda-errorhandling-sqs"
template_path="cloudformation/4-cloudwatch-subscription-cfn.yml"
#template_path="cloudformation/3-s3-for-lambda-trigger-cfn.yml"
#template_path="cloudformation/3-api-gateway-cfn.yml"
#template_path="cloudformation/2-lambda-cfn.yml"
#template_path="cloudformation/1-sqs-cfn.yml"
parameters="EnvType=Dev"
#aws cloudformation create-stack --stack-name ${stack_name} --template-body file://${template_path} --capabilities CAPABILITY_IAM
# It is better cloudformation deploy option because command can execute even if stack existing(no need to delete existing stack).

if [ "$parameters" == "" ]; then
    aws cloudformation deploy --stack-name ${stack_name} --template-file ${template_path} --capabilities CAPABILITY_IAM
else
    aws cloudformation deploy --stack-name ${stack_name} --template-file ${template_path} --parameter-overrides ${parameters} --capabilities CAPABILITY_NAMED_IAM
fi
#!/usr/bin/env bash

export JAVA_HOME=/usr/lib/jvm/java-11-amazon-corretto.x86_64
bucket_name="debugroom-mynavi-sample-lambda-errorhandling-for-deploy"
stack_name="mynavi-sample-deploy-s3-for-lambda-errorhandling"
template_path="cloudformation/1-s3-for-lambda-deploy-cfn.yml"
s3_objectkey="spring-cloud-3-1-lambda-function-0.0.1-SNAPSHOT-aws.jar"

if [ "" == "`aws s3 ls | grep $bucket_name`" ]; then
    aws cloudformation deploy --stack-name ${stack_name} --template-file ${template_path} --capabilities CAPABILITY_IAM
fi

cd spring-cloud-3-1-lambda-function
./mvnw clean package
aws s3 cp target/${s3_objectkey} s3://${bucket_name}/
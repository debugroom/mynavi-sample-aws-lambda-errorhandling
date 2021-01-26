package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.config.TestConfig;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

class SyncExecuteBusinessErrorFunctionTest {

    ObjectMapper mapper = new ObjectMapper();

    String apiGatewayEvent1 = "{\n" +
            "    \"resource\": \"/sampleresource\",\n" +
            "    \"path\": \"/sampleresrouce\",\n" +
            "    \"httpMethod\": \"POST\",\n" +
            "    \"headers\": {\n" +
            "        \"accept\": \"*/*\",\n" +
            "        \"content-type\": \"application/json\",\n" +
            "        \"Host\": \"test.execute-api.ap-northeast-1.amazonaws.com\",\n" +
            "        \"User-Agent\": \"curl/7.54.0\",\n" +
            "        \"X-Amzn-Trace-Id\": \"Root=1-5ece339e-e0595766066d703ec70f1522\",\n" +
            "        \"X-Forwarded-For\": \"10.99.8.3\",\n" +
            "        \"X-Forwarded-Port\": \"443\",\n" +
            "        \"X-Forwarded-Proto\": \"https\"\n" +
            "    },\n" +
            "    \"multiValueHeaders\": {\n" +
            "        \"accept\": [\n" +
            "            \"*/*\"\n" +
            "        ],\n" +
            "        \"content-type\": [\n" +
            "            \"application/json\"\n" +
            "        ],\n" +
            "        \"Host\": [\n" +
            "            \"test.execute-api.ap-northeast-1.amazonaws.com\"\n" +
            "        ],\n" +
            "        \"User-Agent\": [\n" +
            "            \"curl/7.54.0\"\n" +
            "        ],\n" +
            "        \"X-Amzn-Trace-Id\": [\n" +
            "            \"Root=1-5ece339e-e0595766066d703ec70f1522\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-For\": [\n" +
            "            \"10.99.8.3\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Port\": [\n" +
            "            \"443\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Proto\": [\n" +
            "            \"https\"\n" +
            "        ]\n" +
            "    },\n" +
            "    \"queryStringParameters\": {\n" +
            "        \"param1\": \"text1\",\n" +
            "        \"param2\": \"text2\" \n" +
            "    },\n" +
            "    \"multiValueQueryStringParameters\": null,\n" +
            "    \"pathParameters\": null,\n" +
            "    \"stageVariables\": null,\n" +
            "    \"requestContext\": {\n" +
            "        \"resourceId\": \"test\",\n" +
            "        \"resourcePath\": \"/sampleresource\",\n" +
            "        \"httpMethod\": \"POST\",\n" +
            "        \"extendedRequestId\": \"NL0A1EokCGYFZOA=\",\n" +
            "        \"requestTime\": \"24/Jan/2021:09:32:14 +0000\",\n" +
            "        \"path\": \"/test/sampleresrouce\",\n" +
            "        \"accountId\": \"XXXXXXXXXXX\",\n" +
            "        \"protocol\": \"HTTP/1.1\",\n" +
            "        \"stage\": \"test\",\n" +
            "        \"domainPrefix\": \"test\",\n" +
            "        \"requestTimeEpoch\": 1590571934872,\n" +
            "        \"requestId\": \"xxxxxxxx-f92a-43c3-9360-868ba4053a00\",\n" +
            "        \"identity\": {\n" +
            "            \"cognitoIdentityPoolId\": null,\n" +
            "            \"accountId\": null,\n" +
            "            \"cognitoIdentityId\": null,\n" +
            "            \"caller\": null,\n" +
            "            \"sourceIp\": \"10.99.8.3\",\n" +
            "            \"principalOrgId\": null,\n" +
            "            \"accessKey\": null,\n" +
            "            \"cognitoAuthenticationType\": null,\n" +
            "            \"cognitoAuthenticationProvider\": null,\n" +
            "            \"userArn\": null,\n" +
            "            \"userAgent\": \"curl/7.54.0\",\n" +
            "            \"user\": null\n" +
            "        },\n" +
            "        \"domainName\": \"test.execute-api.ap-northeast-1.amazonaws.com\",\n" +
            "        \"apiId\": \"test\"\n" +
            "    },\n" +
            "    \"body\":\"It's test data\",\n" +
            "    \"isBase64Encoded\": false\n" +
            "}";

    String apiGatewayEvent2 = "{\n" +
            "    \"resource\": \"/sampleresource\",\n" +
            "    \"path\": \"/sampleresrouce\",\n" +
            "    \"httpMethod\": \"POST\",\n" +
            "    \"headers\": {\n" +
            "        \"accept\": \"*/*\",\n" +
            "        \"content-type\": \"application/json\",\n" +
            "        \"Host\": \"test.execute-api.ap-northeast-1.amazonaws.com\",\n" +
            "        \"User-Agent\": \"curl/7.54.0\",\n" +
            "        \"X-Amzn-Trace-Id\": \"Root=1-5ece339e-e0595766066d703ec70f1522\",\n" +
            "        \"X-Forwarded-For\": \"10.99.8.3\",\n" +
            "        \"X-Forwarded-Port\": \"443\",\n" +
            "        \"X-Forwarded-Proto\": \"https\"\n" +
            "    },\n" +
            "    \"multiValueHeaders\": {\n" +
            "        \"accept\": [\n" +
            "            \"*/*\"\n" +
            "        ],\n" +
            "        \"content-type\": [\n" +
            "            \"application/json\"\n" +
            "        ],\n" +
            "        \"Host\": [\n" +
            "            \"test.execute-api.ap-northeast-1.amazonaws.com\"\n" +
            "        ],\n" +
            "        \"User-Agent\": [\n" +
            "            \"curl/7.54.0\"\n" +
            "        ],\n" +
            "        \"X-Amzn-Trace-Id\": [\n" +
            "            \"Root=1-5ece339e-e0595766066d703ec70f1522\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-For\": [\n" +
            "            \"10.99.8.3\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Port\": [\n" +
            "            \"443\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Proto\": [\n" +
            "            \"https\"\n" +
            "        ]\n" +
            "    },\n" +
            "    \"queryStringParameters\": null,\n" +
            "    \"multiValueQueryStringParameters\": null,\n" +
            "    \"pathParameters\": null,\n" +
            "    \"stageVariables\": null,\n" +
            "    \"requestContext\": {\n" +
            "        \"resourceId\": \"test\",\n" +
            "        \"resourcePath\": \"/sampleresource\",\n" +
            "        \"httpMethod\": \"POST\",\n" +
            "        \"extendedRequestId\": \"NL0A1EokCGYFZOA=\",\n" +
            "        \"requestTime\": \"24/Jan/2021:09:32:14 +0000\",\n" +
            "        \"path\": \"/test/sampleresrouce\",\n" +
            "        \"accountId\": \"XXXXXXXXXXX\",\n" +
            "        \"protocol\": \"HTTP/1.1\",\n" +
            "        \"stage\": \"test\",\n" +
            "        \"domainPrefix\": \"test\",\n" +
            "        \"requestTimeEpoch\": 1590571934872,\n" +
            "        \"requestId\": \"xxxxxxxx-f92a-43c3-9360-868ba4053a00\",\n" +
            "        \"identity\": {\n" +
            "            \"cognitoIdentityPoolId\": null,\n" +
            "            \"accountId\": null,\n" +
            "            \"cognitoIdentityId\": null,\n" +
            "            \"caller\": null,\n" +
            "            \"sourceIp\": \"10.99.8.3\",\n" +
            "            \"principalOrgId\": null,\n" +
            "            \"accessKey\": null,\n" +
            "            \"cognitoAuthenticationType\": null,\n" +
            "            \"cognitoAuthenticationProvider\": null,\n" +
            "            \"userArn\": null,\n" +
            "            \"userAgent\": \"curl/7.54.0\",\n" +
            "            \"user\": null\n" +
            "        },\n" +
            "        \"domainName\": \"test.execute-api.ap-northeast-1.amazonaws.com\",\n" +
            "        \"apiId\": \"test\"\n" +
            "    },\n" +
            "    \"body\":\"It's test data\",\n" +
            "    \"isBase64Encoded\": false\n" +
            "}";

    @Test
    @Order(1)
    void testApiGatewayStringEventBodyNormalCase() throws Exception {
        System.setProperty("MAIN_CLASS", TestConfig.class.getName());
        System.setProperty("spring.cloud.function.definition", "syncExecuteBusinessErrorFunction");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream(this.apiGatewayEvent1.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(output.toByteArray(), Map.class);
    }

    @Test
    @Order(2)
    void testApiGatewayStringEventBodyAbnormalCase() throws Exception {
        System.setProperty("MAIN_CLASS", TestConfig.class.getName());
        System.setProperty("spring.cloud.function.definition", "syncExecuteBusinessErrorFunction");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream(this.apiGatewayEvent2.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(output.toByteArray(), Map.class);

    }

}

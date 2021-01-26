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

public class AsyncExecuteBusinessErrorFunctionTest {

    ObjectMapper mapper = new ObjectMapper();

    String s3Event = "{\n" +
            "   \"Records\":[\n" +
            "      {\n" +
            "         \"eventVersion\":\"2.1\",\n" +
            "         \"eventSource\":\"aws:s3\",\n" +
            "         \"awsRegion\":\"us-east-2\",\n" +
            "         \"eventTime\":\"2020-07-15T21:29:41.365Z\",\n" +
            "         \"eventName\":\"ObjectCreated:Put\",\n" +
            "         \"userIdentity\":{\n" +
            "            \"principalId\":\"AWS:AIxxx\"\n" +
            "         },\n" +
            "         \"requestParameters\":{\n" +
            "            \"sourceIPAddress\":\"xxxx\"\n" +
            "         },\n" +
            "         \"responseElements\":{\n" +
            "            \"x-amz-request-id\":\"xxxx\",\n" +
            "            \"x-amz-id-2\":\"xxx/=\"\n" +
            "         },\n" +
            "         \"s3\":{\n" +
            "            \"s3SchemaVersion\":\"1.0\",\n" +
            "            \"configurationId\":\"New Data Delivery\",\n" +
            "            \"bucket\":{\n" +
            "               \"name\":\"debugroom-mynavi-sample-lambda-errorhandling-for-system-error\",\n" +
            "               \"ownerIdentity\":{\n" +
            "                  \"principalId\":\"xxx\"\n" +
            "               },\n" +
            "               \"arn\":\"arn:aws:s3:::bucket\"\n" +
            "            },\n" +
            "            \"object\":{\n" +
            "               \"key\":\"TokenRequest_Format\",\n" +
            "               \"size\":32711,\n" +
            "               \"eTag\":\"aaaa\",\n" +
            "               \"sequencer\":\"aaaa\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}";

//    @Test
    @Order(1)
    void testApiGatewayStringEventBodyNormalCase() throws Exception {
        System.setProperty("MAIN_CLASS", TestConfig.class.getName());
        System.setProperty("spring.cloud.function.definition", "asyncExecuteBusinessErrorFunction");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream(this.s3Event.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
    }

}

package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.debugroom.mynavi.sample.aws.lambda.errorhandling.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

public class NotifySystemErrorFunctionTest {

    ObjectMapper mapper = new ObjectMapper();

    String apiGatewayEvent = "{\n" +
            "    \"awslogs\": {\n" +
            "        \"data\": \"H4sIAAAAAAAAAO1V32/bNhD+VwShDx4Q06JISqLfvMYpArQrUHnLQxMMjHSWVUukRlF2gyD/e09S3B9pu2brkL0YhgHq7vjdd7wjv1u/hrZVBaxuGvDn/ulitfjz1TJNFy+W/olv9hosmkUchVxKyvCH5soUL6zpGvTM1L6dVaq+ztWsVXVTwRQt09EyzSwoB9P2pnVQT8FaY6frTmeuNHrESR2G1AgUBiGdBXQWBrO3z14uVst0dbXOVcRVvJYBz7kMlEpisU6kFBFwGfE1QrTddZvZsukRz8rKgW39+Vv/nkpWmS7fK5dtxuQbpfOq1MWB3/Pef9H7X5qiTT/DmtJfZfrHRXh++vr09Zl/NZBd7kC7Hv/WL3PkzIQMZURjGQoeJzThMRdBxEOJJsbigEmWMC6okEEQh0EcJRHlPAyQtyvx4B2y9Oc0opQyGSdShMnJoSEIn8IOrHeusSitKm/ZV+BtVOuZLOss5MRLwe7KDDzQeWNK7by5dxvczT1jC5LDdVdYY2oyHgbBvpCxbpKZujaawPsMhmpJOnRoefi+1D+LMPf+LftLfemU+2EJualVqUk7YpB0CLhHPMc1UoOsc7DaWLNvH7CbfB3+Tu3UPBS/PDK9ahpymGTyfBjz+xx9nWcHD4ZVN5Pv+4esLPw/stIvam0bi/dibVUNe2O3ZLg5n3JlBvv43pFMOYUXgaRlT+0A+AaKsnX25tnBcK53BkNxdWGRDFhSomX7ccPk2/tHYoKzp2SWm8VwXH9HKWL0KSmpHxLiyZN27xGE4n/SNJWrxvW4ONqfZ92ibXij4Q381eEDOXnoHZJ9Kr7/JNeqhdm7fItDNj42xMK6gsyR37CKHbwCtzH5IsvwZTV2uO3jOAaTMcAbI/4b0MnveqtROL3UdDaDR4KeQgUFAuviJ4D7VaV08RF1xPr+fu3fnTzUMi7wTyMWhQHqWpRIHgsWSM4YTYJEJkIkgQhjGsUx41R8W8tQsZk8atlRy45adtSyo5YdtexJtOzq7gNd4WVH0A4AAA==\" \n" +
            "    } \n" +
            "}";

    @SuppressWarnings("rawtypes")
//    @Test
    public void testApiGatewayStringEventBody() throws Exception {
        System.setProperty("MAIN_CLASS", TestConfig.class.getName());
        System.setProperty("spring.cloud.function.definition", "notifySystemErrorFunction");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream(this.apiGatewayEvent.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
    }

}

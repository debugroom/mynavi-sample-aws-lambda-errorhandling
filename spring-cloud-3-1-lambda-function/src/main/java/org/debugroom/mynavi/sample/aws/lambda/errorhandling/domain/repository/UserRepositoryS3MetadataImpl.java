package org.debugroom.mynavi.sample.aws.lambda.errorhandling.domain.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserRepositoryS3MetadataImpl implements UserRepository {

    @Autowired
    AmazonS3 amazonS3;

    @Override
    public String getId(Map<String, Object> stringObjectMap) {

        List<Map<String, Object>> records = (List<Map<String, Object>>)stringObjectMap.get("Records");
        Map<String, Object> s3InfoMap = (Map<String, Object>)records.get(0).get("s3");
        Map<String, Object> bucketMap = (Map<String, Object>)s3InfoMap.get("bucket");
        Map<String, Object> objectMap = (Map<String, Object>)s3InfoMap.get("object");
        GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest(
                (String)bucketMap.get("name"), (String)objectMap.get("key"));

        ObjectMetadata objectMetadata = amazonS3.getObjectMetadata(getObjectMetadataRequest);

        return objectMetadata.getUserMetadata().get("userId");

    }

}

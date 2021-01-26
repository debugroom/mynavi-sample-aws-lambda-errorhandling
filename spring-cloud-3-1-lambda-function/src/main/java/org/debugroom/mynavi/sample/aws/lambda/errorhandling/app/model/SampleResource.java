package org.debugroom.mynavi.sample.aws.lambda.errorhandling.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SampleResource {

    private String message;

}

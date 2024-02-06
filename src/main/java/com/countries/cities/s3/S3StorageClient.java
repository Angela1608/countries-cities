package com.countries.cities.s3;

import com.countries.cities.exception.StorageException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@RequiredArgsConstructor
public class S3StorageClient implements StorageClient {

    private static final String IDENTIFIER_PREFIX = "logos";

    private final S3Client s3Client;
    private final String bucket;
    private final String host;

    @Override
    public String putContent(String fileName, byte[] data) {
        String identifier = IDENTIFIER_PREFIX + "/" + fileName;
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(identifier)
                .build();
        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(data));
            return buildLogoLink(fileName);
        } catch (Exception e) {
            log.error("Error putting content to S3. Bucket: {}, Key: {}, Error Message: {}",
                    bucket, identifier, e.getMessage());
            throw new StorageException("Failed to upload content to S3", e);
        }
    }

    private String buildLogoLink(String fileName) {
        return UriComponentsBuilder
                .fromHttpUrl(host)
                .path(IDENTIFIER_PREFIX)
                .path("/")
                .path(UriUtils.encode(fileName, StandardCharsets.UTF_8))
                .build()
                .toString();
    }

}

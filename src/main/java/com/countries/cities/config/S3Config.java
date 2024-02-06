package com.countries.cities.config;

import com.countries.cities.s3.S3StorageClient;
import com.countries.cities.s3.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    private String region;
    private String bucket;
    private String accessKey;
    private String secretKey;
    private String host;

    public S3Config(@Value("${aws.s3.region}") String region,
                    @Value("${aws.s3.bucket}") String bucket,
                    @Value("${aws.s3.credentials.access-key}") String accessKey,
                    @Value("${aws.s3.credentials.secret-key}") String secretKey,
                    @Value("${aws.s3.host}") String host) {
        this.region = region;
        this.bucket = bucket;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.host = host;
    }

    @Bean
    public StorageClient localStorageClient() {
        S3Client s3Client = S3Client.builder()
                .credentialsProvider(createStaticCredentialsProvider(accessKey, secretKey))
                .region(Region.of(region))
                .build();
        return new S3StorageClient(s3Client, bucket, host);
    }

    private AwsCredentialsProvider createStaticCredentialsProvider(String accessKey,
                                                                   String secretKey) {
        AwsBasicCredentials awsCredsProvider = AwsBasicCredentials.create(accessKey, secretKey);
        return StaticCredentialsProvider.create(awsCredsProvider);
    }

}

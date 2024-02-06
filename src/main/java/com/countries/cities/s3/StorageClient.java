package com.countries.cities.s3;

public interface StorageClient {

    String putContent(String fileName, byte[] data);

}

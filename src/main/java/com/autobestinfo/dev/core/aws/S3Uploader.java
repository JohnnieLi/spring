package com.autobestinfo.dev.core.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

//https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-project-gradle.html
//https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-objects.html
@Service
public class S3Uploader {

     static Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }


    List<Bucket> listBuckets(){
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        return s3.listBuckets();
    }


    Bucket createBucket(String bucket_name){
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket_name)) {
            System.out.format("Bucket %s already exists.\n", bucket_name);
            b = getBucket(bucket_name);
        } else {
            try {
                b = s3.createBucket(bucket_name);
            } catch (AmazonS3Exception e) {
                System.err.println(e.getErrorMessage());
            }
        }
        return b;
    }


    void uploadBucketObject(String bucket_name, String path, String filename, String file_path){
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            String key_name =  path + filename;
            s3.putObject(bucket_name, key_name, new File(file_path));
        } catch (AmazonServiceException e) {
           throw e;
        }
    }

    List<S3ObjectSummary> listBucketObjects(String bucket_name){
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        ListObjectsV2Result result = s3.listObjectsV2(bucket_name);
        return result.getObjectSummaries();
    }


    void deleteBucketObject(String bucket_name, String object_key){
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.deleteObject(bucket_name, object_key);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

}

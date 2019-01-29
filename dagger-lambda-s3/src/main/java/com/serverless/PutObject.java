
package com.serverless;


import java.io.File;
import java.nio.file.Paths;

import javax.inject.Inject;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Upload a file to an Amazon S3 bucket.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class PutObject{

	@Inject ListObjects listKeys;




    //public static void main(String[] args){
	public void putObjectToS3(){

    	String clientRegion = "ap-northeast-1";

        String bucketName = "dagger-test-bucket";
        String file_path = "images/sakura.jpg";
        String key_name = Paths.get(file_path).getFileName().toString();

        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucketName);
        //final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider())
                .withRegion(clientRegion)
                .build();



        try {

        	s3Client.putObject(bucketName, key_name, new File(file_path));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }

}
package com.ag.energyconsumption.repository.dynamodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration 
public class DynamoDBConfiguration {
	
	 
    @Value("${aws.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${aws.endpoint.local}")
    private String amazonDynamoDBEndpointLocal;
    
    @Value("${aws.user.accesskeyid}")
    private String amazonAWSAccessKey;
 
    @Value("${aws.user.secretkey}")
    private String amazonAWSSecretKey;
    
    @Value("${aws.region}")
    private String amazonAwsRegion;
 
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        AmazonDynamoDB amazonDynamoDB 
//          = new AmazonDynamoDBClient(amazonAWSCredentials());
//         
//        if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
//            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
//        }
//         
//        return amazonDynamoDB;
//    }
 
    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
          amazonAWSAccessKey, amazonAWSSecretKey);
    }

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}
	
    @Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {

		
		return AmazonDynamoDBClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(amazonAwsRegion).build();
	}
	
	@Bean
	public AmazonDynamoDBClient amazonDynamoDBClientLocal() {
		
		AmazonDynamoDBClient amazonDynamoDBClient = new AmazonDynamoDBClient(amazonAWSCredentials());

		amazonDynamoDBClient.setEndpoint(amazonDynamoDBEndpointLocal);
		
		return amazonDynamoDBClient;
		
	}
}

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.javastubs.userServiceGrpc;
import com.example.javastubs.User.APIResponse;
import com.example.javastubs.User.LoginRequest;
import com.example.javastubs.userServiceGrpc.userServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcClient {

	public static void main(String[] args) throws InterruptedException {
		
		SpringApplication.run(GrpcClient.class, args);
		
		System.out.println("Grpc Client!!!");
		
		int i=1;
		
		ManagedChannel channel = null;
		
		while(i <= 10)
		{
			
		channel = ManagedChannelBuilder
				.forAddress("172.30.233.176", 8085).usePlaintext().build();
		
		userServiceBlockingStub userStub = userServiceGrpc.newBlockingStub(channel);
		
		LoginRequest loginRequest = LoginRequest.newBuilder()
				.setUsername("New World").setPassword("New World").build();
		
		APIResponse response = userStub.login(loginRequest);
		
		System.out.println(response.getResponsemessage());
		
		i++;
		
		Thread.sleep(10000);
		
		}
		
		channel.shutdown();
		System.out.println("Task completed!!!");
	}

}

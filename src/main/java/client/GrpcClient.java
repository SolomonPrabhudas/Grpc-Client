package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.javastubs.User.APIResponse;
import com.sample.javastubs.User.LoginRequest;
import com.sample.javastubs.userServiceGrpc;
import com.sample.javastubs.userServiceGrpc.userServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcClient {

	public static void main(String[] args) {
		
		SpringApplication.run(GrpcClient.class, args);
		
		System.out.println("Grpc Client!!!");
		
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 8085).usePlaintext().build();
		
		userServiceBlockingStub userStub = userServiceGrpc.newBlockingStub(channel);
		
		LoginRequest loginRequest = LoginRequest.newBuilder()
				.setUsername("New World").setPassword("New World").build();
		
		APIResponse response = userStub.login(loginRequest);
		
		System.out.println(response.getResponsemessage());
		
		channel.shutdown();
	}

}

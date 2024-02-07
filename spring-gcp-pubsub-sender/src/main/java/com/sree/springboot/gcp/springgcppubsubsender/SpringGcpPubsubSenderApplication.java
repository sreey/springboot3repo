package com.sree.springboot.gcp.springgcppubsubsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;

@SpringBootApplication
@RestController
public class SpringGcpPubsubSenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGcpPubsubSenderApplication.class, args);
	}

	@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
	public interface PubsubOutboundGateway {
		void sendToPubsub(String text);
	}

	@Bean
	@ServiceActivator(inputChannel = "pubsubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
		return new PubSubMessageHandler(pubsubTemplate, "exampleTopic");
	}

	@Autowired
	private PubsubOutboundGateway messagingGateway;

	@PostMapping("/postMessage")
	public ResponseEntity<String> postMessage(@RequestParam("message") String message) {
		this.messagingGateway.sendToPubsub(message);
		return ResponseEntity.ok()
        .body("posted message successfully to topic");
	}

	@GetMapping("/getMessage")
	public String getMessage(){
		return "hello";
	}

}

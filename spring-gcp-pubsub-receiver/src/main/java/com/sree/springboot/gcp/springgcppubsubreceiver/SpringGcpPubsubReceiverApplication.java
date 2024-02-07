package com.sree.springboot.gcp.springgcppubsubreceiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;

@SpringBootApplication
public class SpringGcpPubsubReceiverApplication {


	private static final Log LOGGER = LogFactory.getLog(SpringGcpPubsubReceiverApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringGcpPubsubReceiverApplication.class, args);
	}

	@Bean
  public MessageChannel pubsubInputChannel() {
    return new DirectChannel();
  }

  @Bean
  public PubSubInboundChannelAdapter messageChannelAdapter(
      @Qualifier("pubsubInputChannel") MessageChannel inputChannel,
      PubSubTemplate pubSubTemplate) {
    PubSubInboundChannelAdapter adapter =
        new PubSubInboundChannelAdapter(pubSubTemplate, "exampleSubscription");
    adapter.setOutputChannel(inputChannel);

    return adapter;
  }

  @ServiceActivator(inputChannel = "pubsubInputChannel")
  public void messageReceiver(String payload) {
    LOGGER.info("Message arrived! Payload: " + payload);
  }
}

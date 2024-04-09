package sk.projects.kafka.kafkaspringbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

public class AccountDetailsConsumer {

    private final Logger logger = LoggerFactory.getLogger(AccountDetailsConsumer.class);

    @KafkaListener(id = "account_consumer", topics = "account_details", groupId = "retail_sedt", autoStartup = "false")
    public void listen(String value,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        logger.info(String.format("Consumed event from topic %s: key = %-10s value = %s", topic, key, value));
    }
}

package sk.projects.kafka.kafkaspringbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;


@SpringBootApplication
public class KafkaSpringBootDemoApplication {

    private final AccountDetailsProducer producer;
    @Autowired
    KafkaSpringBootDemoApplication(AccountDetailsProducer producer) {
        this.producer = producer;
    }

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry = new KafkaListenerEndpointRegistry();


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KafkaSpringBootDemoApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            for (String arg : args) {
                switch (arg) {
                    case "--producer" -> {
                        this.producer.sendMessage("awalther", "t-shirts");
                        this.producer.sendMessage("awalther-1", "pants");
                    }
                    case "--consumer" -> {
                        MessageListenerContainer listenerContainer = this.kafkaListenerEndpointRegistry.getListenerContainer("topic6Consumer");
                            listenerContainer.start();
                    }
                    default -> {
                    }
                }
            }
        };
    }
}
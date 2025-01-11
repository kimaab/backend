package me.hello.backend.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ProduceController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/publish")
    public String publish() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        this.kafkaTemplate.send("wool_kafka_topic", generatedString);

        this.kafkaTemplate.send("wool_kafka_topic2", generatedString);

        this.kafkaTemplate.send("wool_kafka_topic3", generatedString);

        return "success";
    }
}
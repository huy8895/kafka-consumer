package com.example.kafkaconsumer;

import com.example.kafkaconsumer.confiig.KafkaConfig;
import com.example.kafkaconsumer.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
public class KafkaConsumerApplication {
    private final List<String> listMsg = new ArrayList<>();
    private final List<MyUser> listMyUser = new ArrayList<>();

    @KafkaListener(groupId = KafkaConfig.GROUP_ID_1, topics = KafkaConfig.TOPIC_1, containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromKafka(String data){
        log.info("consume message: {} \n from topic: {}", data, KafkaConfig.TOPIC_1);
        listMsg.add(data);
        return listMsg;
    }

    @KafkaListener(groupId = KafkaConfig.GROUP_ID_1, topics = KafkaConfig.TOPIC_1, containerFactory = "kafkaListenerContainerFactory")
    public List<MyUser> getJsonMsgFromKafka(String user){
        log.info("consume json message: {} \n from topic: {}", user, KafkaConfig.TOPIC_1);
//        listUser.add(user);
        return listMyUser;
    }

    @GetMapping("/consumeStringMsg")
    public List<String> consumeMsg(){
        return listMsg;
    }

    @GetMapping("/consumeJsonMsg")
    public List<MyUser> consumeJsonMsg(){
        return listMyUser;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class, args);
    }

}

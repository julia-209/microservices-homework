package org.example.contactproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableTransactionManagement
@EnableKafka
public class ContactProjectApplication {
        public static void main(String[] args) {
            SpringApplication.run(ContactProjectApplication.class, args);
        }
}


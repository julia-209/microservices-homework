package org.example.contactproject.config;

import org.example.contactproject.ChatRequest;
import org.example.contactproject.service.ChatService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ChatConfig {

    private final ChatService chatService;
    private final StreamBridge streamBridge;

    @Bean
    public Consumer<ChatRequest> consumer() {
        return request -> {
            String message = request.getMessage();
            System.out.println("Получено сообщение: " + message);

            String response =
                    chatService.processMessage(message);

            System.out.println("Ответ: " + response);

            streamBridge.send(
                    "chat-producer-topic",
                    response
            );
        };
    }
}
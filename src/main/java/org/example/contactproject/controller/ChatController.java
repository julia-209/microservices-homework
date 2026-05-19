package org.example.contactproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.contactproject.ChatRequest;
import org.example.contactproject.service.ChatServiceImpl;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final StreamBridge streamBridge;
    private final ChatServiceImpl chatService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> sendMessage(
            @RequestBody ChatRequest request
    ) {

        streamBridge.send(
                "chat-consumer-topic",
                request
        );

        return ResponseEntity.ok("Сообщение отправлено");
    }
    @GetMapping("/process")
    public ResponseEntity<String> process(
            @RequestParam String message
    ) {

        return ResponseEntity.ok(
                chatService.processMessage(message)
        );
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok(
                chatService.hello()
        );
    }

    @GetMapping("/status")
    public ResponseEntity<String> status() {

        return ResponseEntity.ok(
                chatService.status()
        );
    }
}

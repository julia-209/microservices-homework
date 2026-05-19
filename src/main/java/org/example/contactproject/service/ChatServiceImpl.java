package org.example.contactproject.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public String processMessage(String message) {

        if (message == null) {
            return "Пустое сообщение";
        }

        String normalized = message.toLowerCase().trim();

        String answer;

        switch (normalized) {

            case "который час?":
                answer = LocalTime.now().withNano(0).toString();
                break;

            case "привет":
                answer = "Привет!";
                break;

            case "как дела?":
                answer = "Хорошо";
                break;

            default:
                answer = "Я не понимаю вопрос";
        }

        return answer;
    }
    @Override
    public String hello() {
        return "Привет!";
    }

    @Override
    public String status() {
        return "Сервис работает";
    }
}
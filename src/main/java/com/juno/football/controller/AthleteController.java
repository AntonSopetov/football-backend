package com.juno.football.controller;

import com.juno.football.model.LeadRequest;
import com.juno.football.service.TelegramPushService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final TelegramPushService pushService;

    public AthleteController(TelegramPushService pushService) {
        this.pushService = pushService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerFromSite(@RequestBody LeadRequest request) {

        // 1. Валидация возраста по ТЗ (от 5 до 12 лет)
        if (request.getChildAge() < 5 || request.getChildAge() > 12) {
            return ResponseEntity
                    .badRequest() // Возвращает статус 400 Bad Request
                    .body("ERROR: Заявка отклонена. Возраст ребенка должен быть от 5 до 12 лет.");
        }

        // 2. Если валидация прошла успешно, шлем пуш в Телеграм
        pushService.sendRegistrationPush(request);

        return ResponseEntity.ok("SUCCESS: Заявка принята бэкендом, пуш отправлен в группу!");
    }
}

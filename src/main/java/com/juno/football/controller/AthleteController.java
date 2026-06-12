package com.juno.football.controller;

import com.juno.football.model.LeadRequest;
import com.juno.football.service.TelegramPushService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    private final TelegramPushService pushService;

    public AthleteController(TelegramPushService pushService) {
        this.pushService = pushService;
    }

    /**
     * Сюда сайт будет слать POST-запрос при заполнении формы
     * URL для запроса: http://localhost:8080/api/athletes/register
     */
    @PostMapping("/register")
    public String registerFromSite(@RequestBody LeadRequest request) {
        // Вызываем отправку пуша в Телеграм чат
        pushService.sendRegistrationPush(request);

        return "SUCCESS: Заявка принята бэкендом, пуш отправлен в группу!";
    }
}

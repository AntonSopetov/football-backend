package com.juno.football.controller;

import com.juno.football.model.MatchEvent;
import com.juno.football.model.Player;
import com.juno.football.model.RegistrationStatus;
import com.juno.football.service.MatchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Создание матча с проверкой погоды в Батуми (Сценарий №1)
     * Ром, тут ты шлёшь POST-запрос на http://localhost:8080/api/matches/create
     */
    @PostMapping("/create")
    public String createMatch(@RequestBody MatchEvent matchEvent) {
        return matchService.createMatchWithWeatherCheck(matchEvent);
    }

    /**
     * Принудительное создание матча (Ром, тут если ты нажал кнопку "Всё равно создать")
     */
    @PostMapping("/create/force")
    public String forceCreateMatch(@RequestBody MatchEvent matchEvent) {
        matchService.forceCreateMatch(matchEvent);
        return "SUCCESS: Матч принудительно создан вопреки плохой погоде!";
    }

    /**
     * Запись игрока на матч (Сценарий №2)
     * Ром, тут ты шлёшь POST-запрос на http://localhost:8080/api/matches/{id}/register
     */
    @PostMapping("/{matchId}/register")
    public String registerPlayer(@PathVariable Long matchId, @RequestBody Player player) {
        RegistrationStatus status = matchService.registerPlayer(matchId, player);

        if (status == RegistrationStatus.ACTIVE) {
            return "Игрок успешно добавлен в основной состав!";
        } else {
            return "Свободных мест нет. Игрок добавлен в список ожидания (Waitlist)!";
        }
    }
}

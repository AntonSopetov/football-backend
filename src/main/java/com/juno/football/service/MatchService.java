package com.juno.football.service;

import com.juno.football.model.MatchEvent;
import com.juno.football.model.Player;
import com.juno.football.model.RegistrationStatus;
import com.juno.football.model.WeatherInfo;
import com.juno.football.repository.MatchRepository;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final WeatherService weatherService; // Подключаем наш новый погодный сервис

    // Спринг автоматически внедрит оба сервиса сюда
    public MatchService(MatchRepository matchRepository, WeatherService weatherService) {
        this.matchRepository = matchRepository;
        this.weatherService = weatherService;
    }

    /**
     * Метод создания матча организатором с проверкой погоды (Сценарий №1)
     * Возвращает текст-предупреждение или подтверждение создания.
     */
    public String createMatchWithWeatherCheck(MatchEvent match) {
        // 1. Спрашиваем у сервиса, какая сейчас погода в Батуми
        WeatherInfo currentWeather = weatherService.getWeatherForBatumi();

        // 2. Проверяем риски по ТЗ (влажность > 85% или дождь > 2.0 мм/ч)
        if (weatherService.hasSevereWeatherRisks(currentWeather)) {
            // Возвращаем Soft Block интерфейс (сообщение для Роминого бота)
            return "SOFT_BLOCK: Внимание! В Батуми высокая влажность (" + currentWeather.getHumidity()
                    + "%) или сильный дождь (" + currentWeather.getRainIntensity()
                    + " мм/ч). Вы уверены, что хотите опубликовать матч?";
        }

        // 3. Если всё отлично - сохраняем матч в память
        matchRepository.save(match);
        return "SUCCESS: Матч успешно создан и опубликован в каналах Sport Park Batumi!";
    }

    /**
     * Метод принудительного создания (если организатор нажал кнопку "Всё равно создать")
     */
    public void forceCreateMatch(MatchEvent match) {
        matchRepository.save(match);
    }

    /**
     * Логика кнопки "+ Apply" (остается без изменений)
     */
    public RegistrationStatus registerPlayer(Long matchId, Player player) {
        MatchEvent match = matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Матч с ID " + matchId + " не найден"));

        if (match.getActiveRoster().contains(player)) return RegistrationStatus.ACTIVE;
        if (match.getWaitlist().contains(player)) return RegistrationStatus.WAITLIST;

        if (match.getActiveRoster().size() < match.getMaxCapacity()) {
            match.getActiveRoster().add(player);
            matchRepository.save(match);
            return RegistrationStatus.ACTIVE;
        } else {
            match.getWaitlist().add(player);
            matchRepository.save(match);
            return RegistrationStatus.WAITLIST;
        }
    }
}

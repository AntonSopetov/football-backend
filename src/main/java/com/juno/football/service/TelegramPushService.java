package com.juno.football.service;

import com.juno.football.model.LeadRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramPushService {

    private final String botToken = "8596634492:AAFSAH1hRT5bkOP1jJUmKkPACAkVU4TnTGw";
    private final String chatId = "1560165453";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendRegistrationPush(LeadRequest request) {
        String text = "🔔 *Новая заявка с сайта Juno!*\n\n" +
                "👤 *Ребенок:* " + request.getChildName() + "\n" +
                "📅 *Возраст:* " + request.getChildAge() + " лет\n" +
                "📞 *Телефон:* " + request.getParentPhone();

        String url = "https://telegram.org" + botToken + "/sendMessage?chat_id=" + chatId + "&text=" + text + "&parse_mode=Markdown";

        try {
            restTemplate.getForObject(url, String.class);
            System.out.println("Пуш успешно улетел в Телеграм!");
        } catch (Exception e) {
            System.err.println("Ошибка отправки пуша в ТГ: " + e.getMessage());
        }
    }
}

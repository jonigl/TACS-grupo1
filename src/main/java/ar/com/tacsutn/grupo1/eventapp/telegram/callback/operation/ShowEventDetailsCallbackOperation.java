package ar.com.tacsutn.grupo1.eventapp.telegram.callback.operation;

import ar.com.tacsutn.grupo1.eventapp.client.EventbriteClient;
import ar.com.tacsutn.grupo1.eventapp.models.Event;
import ar.com.tacsutn.grupo1.eventapp.telegram.TelegramBot;
import ar.com.tacsutn.grupo1.eventapp.telegram.callback.CallbackData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

@Component
public class ShowEventDetailsCallbackOperation extends AbstractCallbackOperation {

    private final EventbriteClient eventbriteClient;

    @Autowired
    protected ShowEventDetailsCallbackOperation(EventbriteClient eventbriteClient) {
        this.eventbriteClient = eventbriteClient;
    }

    @Override
    public void handle(TelegramBot bot, CallbackQuery callbackQuery, CallbackData callbackData) {
        String eventId = callbackData.getEventId();

        eventbriteClient.getEvent(eventId).ifPresent(event -> {
            SendMessage request = getRequest(callbackQuery, event);
            makeRequest(bot, request);
        });
    }

    private SendMessage getRequest(CallbackQuery callbackQuery, Event event) {
        String messageText = new StringBuilder()
                .append("Evento: ")
                .append("[").append(event.getName()).append("]")
                .append("(").append(event.getUrl()).append(").")
                .toString();

        return new SendMessage()
                .setChatId((long) callbackQuery.getFrom().getId())
                .enableMarkdown(true)
                .enableWebPagePreview()
                .setText(messageText)
                .setReplyMarkup(getKeyboardMarkup(event));
    }

    private InlineKeyboardMarkup getKeyboardMarkup(Event event) {
        InlineKeyboardButton button = new InlineKeyboardButton()
                .setUrl(event.getUrl())
                .setText("Ver en Eventbrite");

        List<InlineKeyboardButton> row = Collections.singletonList(button);
        List<List<InlineKeyboardButton>> keyboard = Collections.singletonList(row);

        return new InlineKeyboardMarkup()
                .setKeyboard(keyboard);
    }
}

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {

        ApiContextInitializer.init(); // иницилизация апи

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {

            telegramBotsApi.registerBot(new Bot()); //регистрация бота


        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }


    public void sendMsg(Message message, String text) {

        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);


        try {

            setButtons((sendMessage));
            sendMessage(sendMessage);


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


    public void onUpdateReceived(Update update) {  //прием сообщений

        Parsing parsing = new Parsing();





        Model model = new Model();

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "чем могу помочь ?");

                    break;
                case "/start":
                    sendMsg(message, "ДОРБРО ПОЖАЛОВАТЬ, ПОГОДА В КАКОМ ГОРОДЕ ВАС ИНТЕРЕСУЕТ?");
                    break;
                case "/weak":
                    sendMsg(message, parsing.pars().toString());
                    break;


                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "Такой город не найден!!!");
                    }
            }
        }

    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setSelective(true);

        replyKeyboardMarkup.setResizeKeyboard(true);

        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        //keyboardFirstRow.add(new KeyboardButton("ВоВа"));
        keyboardFirstRow.add(new KeyboardButton("Vinnytsia"));
        keyboardFirstRow.add(new KeyboardButton("Kyiv"));
        keyboardFirstRow.add(new KeyboardButton("Zaporizhzhia"));
        keyboardFirstRow.add(new KeyboardButton("/weak"));

        keyboardRows.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    public String getBotUsername() { // возращает имя бота
        return "WeatherBot";
    }

    public String getBotToken() { //токин
        return "651724817:AAEwJbsXmfWCfw4GRfS0PV2_CsDnp14DNq0";
    }
}

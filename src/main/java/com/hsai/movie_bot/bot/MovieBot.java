package com.hsai.movie_bot.bot;

import com.hsai.movie_bot.exception.ServiceException;
import com.hsai.movie_bot.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;

@Component
public class MovieBot extends TelegramLongPollingBot {
    private String currentUser;
    private static final Logger LOG = LoggerFactory.getLogger(MovieBot.class);
    private static final String START = "/start";

    private static final String SIGN_IN = "/sign_in";
    private static final String SIGN_UP = "/sign_up";
    private static final String HELP = "/help";

    @Autowired
    private MovieService movieService;

    public MovieBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        var message = update.getMessage().getText();
        var chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case SIGN_IN -> signInCommand(chatId);
            case SIGN_UP -> signUpCommand(chatId);
            case HELP -> helpCommand(chatId);
            default -> unknownCommand(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return "hsai_2023_movie_bot";
    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Добро пожаловать в бот, %s!
                                
                Здесь Вы сможете узнать как сильно я ненавижу этот универ.
                                
                Для этого вы можете воспользоваться командами:
                /sign_up - зарегестрироваться
                /sign_in - войти
                /
                                
                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void signUpCommand(Long chatId) {
        var login = "";
        String formattedText = "Введите логин: ";
        sendMessage(chatId, formattedText);

        login = movieService.addLogin();
        if (login.equals("")) {
            formattedText = "Данный логин ужэ существует или использует запрещённые символы. Попробуйте ещё раз.";
            sendMessage(chatId, formattedText);
            return;
        }
        formattedText = "Введите пароль: ";

        sendMessage(chatId, formattedText);

        boolean password = movieService.addPassword(login);
        if(!password){
            formattedText = "Пароль использует запрещённые символы. Попробуйте ещё раз.";
            sendMessage(chatId, formattedText);
            return;
        }
        formattedText = "Вы успешно зарегестрировались!\nПосле регистрации необходимо выполнить вход (\\sign_in).";
        sendMessage(chatId, formattedText);
    }

    private void signInCommand(Long chatId) {
        var login = "";
        String formattedText = "Введите логин: ";
        sendMessage(chatId, formattedText);
        login = movieService.searchForLogin();
        if(login.equals("")){
            formattedText = "Пользователя с таким логином не существует.";
            sendMessage(chatId, formattedText);
            return;
        }
        formattedText = "Введите пароль: ";
        sendMessage(chatId, formattedText);
        boolean password = movieService.searchForPassword(login);
        if(!password){
            formattedText = "Неверный пароль. Попробуйте ещё раз.";
            sendMessage(chatId, formattedText);
            return;
        }
        currentUser = login;
        formattedText = "Вход выполнен!";
        sendMessage(chatId, formattedText);
    }

    private void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту
                                
                Можете воспользоваться следующими командами:
                """;
        sendMessage(chatId, text);
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Ошибка отправки сообщения", e);
        }
    }
}
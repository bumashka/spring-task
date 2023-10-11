package com.hsai.movie_bot.service;

import com.hsai.movie_bot.exception.ServiceException;

public interface MovieService {

    String addLogin();

    boolean addPassword(String login);

    String searchForLogin();

    boolean searchForPassword(String login);

}

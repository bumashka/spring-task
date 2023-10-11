package com.hsai.movie_bot.service;

public interface MovieService {

    String getRecommendedFilm(String genre) throws Exception;

    void getUserId(String userName) throws Exception;

}

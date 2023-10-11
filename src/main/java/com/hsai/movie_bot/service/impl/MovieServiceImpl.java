package com.hsai.movie_bot.service.impl;

import com.hsai.movie_bot.client.SQLClient;
import com.hsai.movie_bot.service.MovieService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.random.RandomGenerator;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private SQLClient client;

    @Cacheable(value = "user", unless = "#result == null or #result.isEmpty()")
    @Override
    public void getUserId(String userName) throws Exception {
        var userOptional = client.getUser(userName);

        String field = userOptional.orElseThrow(
                () -> new Exception("Couldn't get user name.")
        );
        JSONObject user = new JSONObject(field);
        try {
            user.get("nickname");
        } catch (JSONException e) {
            throw new Exception("No user in the database!");
        }
    }

    @Override
    public String getRecommendedFilm(String genre) throws Exception {
        long genreId;
        String genreName = genre;
        if (!genre.isEmpty()) {
            var genreOptional = client.findGenre(genre);
            String field = genreOptional.orElseThrow(
                    () -> new Exception("Client is not answering.")
            );
            JSONObject JSONgenre = new JSONObject(field);
            try {
                genreId = Long.parseLong(JSONgenre.get("id").toString());
            } catch (JSONException e) {
                throw new Exception("No such genre in the database!");
            }
        } else {
            var genreOptional = client.findAllGenres();
            String field = genreOptional.orElseThrow(
                    () -> new Exception("Client is not answering.")
            );
            JSONArray JSONArrayGenre = new JSONArray(field);
            JSONObject randomGenre = JSONArrayGenre.getJSONObject(
                    RandomGenerator.getDefault().nextInt(JSONArrayGenre.length() - 1));
            genreId = Long.parseLong(randomGenre.get("id").toString());
            genreName = randomGenre.get("name").toString();
        }
        JSONArray JSONArrayMovie = new JSONArray(client.findMovieBasedOnGenre(String.valueOf(genreId)).orElseThrow(
                () -> new Exception("Client is not answering.")
        ));
        JSONObject randomMovie = JSONArrayMovie.getJSONObject(
                RandomGenerator.getDefault().nextInt(JSONArrayMovie.length() - 1));
        return genreName.concat("~").concat(randomMovie.get("name").toString());
    }
}

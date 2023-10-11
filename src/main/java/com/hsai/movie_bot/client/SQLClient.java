package com.hsai.movie_bot.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class SQLClient {
    @Autowired
    private OkHttpClient client;

    @Value("${server.url}")
    private String SQLUrl;

    public Optional<String> request(String url) throws Exception {
        var request = new Request.Builder()
                .url(url)
                .build();
        try (var response = client.newCall(request).execute()) {
            var body = response.body();
            return body == null ? Optional.empty() : Optional.of(body.string());
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public Optional<String> getUser(String userName) throws Exception {
        return request(SQLUrl.concat("user/nickname/").concat(userName));
    }

    public Optional<String> findGenre(String genre) throws Exception {
        return request(SQLUrl.concat("genre/name/").concat(genre));
    }

    public Optional<String> findAllGenres() throws Exception {
        return request(SQLUrl.concat("genre/all"));
    }

    public Optional<String> findMovieBasedOnGenre(String genreId) throws Exception {
        return request(SQLUrl.concat("movie/genre/").concat(genreId));
    }
}
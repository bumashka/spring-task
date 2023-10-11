package edu.hsai.movierecommendation.entity.user.abstraction.service;

import edu.hsai.movierecommendation.entity.user.repository.User;

public interface UserService {
    UserDto getById(Long id);
    record UserDto(Long id, String nickname) {
        public static UserDto fromDbEntity(User user) {
            return new UserDto(
                    user.getId(),
                    user.getNickname()
            );
        }
    }
}

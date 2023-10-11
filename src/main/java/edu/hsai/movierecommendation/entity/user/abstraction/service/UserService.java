package edu.hsai.movierecommendation.entity.user.abstraction.service;

import edu.hsai.movierecommendation.entity.user.repository.User;

public interface UserService {
    UserDto getById(Long id);
    Long addUser(AddUserDto addUserDto);

    record UserDto(Long id, String nickname) {
        public static UserDto fromDbEntity(User user) {
            return new UserDto(
                    user.getId(),
                    user.getNickname()
            );
        }
    }

    record AddUserDto(String nickname) {
        public static User toDbEntity(AddUserDto addUserDto) {
            return new User(addUserDto.nickname());
        }
    }
}

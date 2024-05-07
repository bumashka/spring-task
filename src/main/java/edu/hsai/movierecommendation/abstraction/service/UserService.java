package edu.hsai.movierecommendation.abstraction.service;

import edu.hsai.movierecommendation.repository.User;

public interface UserService {
    UserDto getById(Long id);
    Long addUser(AddUserDto addUserDto);
    UserDto getByNickname(String nickname);
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

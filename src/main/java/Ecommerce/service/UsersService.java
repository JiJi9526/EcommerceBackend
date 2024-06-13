package Ecommerce.service;

import Ecommerce.dto.UsersInfo;
import Ecommerce.entity.Users;

import java.util.UUID;

public interface UsersService {
    Users createUser(Users user);
    Users updateUser(UsersInfo usersInfo);
    void deleteUser(UUID userId);
}

package com.bootcamp.stockportfolio.service;

import com.bootcamp.stockportfolio.entity.User;
import java.util.UUID;

public interface UserService {
    UUID addUser(User user);
}

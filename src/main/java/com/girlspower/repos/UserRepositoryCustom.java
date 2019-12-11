package com.girlspower.repos;

import com.girlspower.domain.User;

public interface UserRepositoryCustom {
    User findByAuthentication();
}
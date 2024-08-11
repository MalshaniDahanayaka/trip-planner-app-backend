package com.uok.tripplanner.authservice.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),
    NORMAL_USER_READ("normal_user:read"),
    NORMAL_USER_UPDATE("normal_user:update"),
    NORMAL_USER_CREATE("normal_user:create"),
    NORMAL_USER_DELETE("normal_user:delete"),
    PREMIUM_USER_READ("premium_user:read"),
    PREMIUM_USER_UPDATE("premium_user:update"),
    PREMIUM_USER_CREATE("premium_user:create"),
    PREMIUM_USER_DELETE("premium_user:delete");


    @Getter
    private final String permission;
}

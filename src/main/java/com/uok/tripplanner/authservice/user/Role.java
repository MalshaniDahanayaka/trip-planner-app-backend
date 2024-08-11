package com.uok.tripplanner.authservice.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.uok.tripplanner.authservice.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    NORMAL_USER(
            Set.of(
                    NORMAL_USER_READ,
                    NORMAL_USER_UPDATE,
                    NORMAL_USER_DELETE,
                    NORMAL_USER_CREATE
            )
    ),
    PREMIUM_USER(
            Set.of(
                    NORMAL_USER_READ,
                    NORMAL_USER_UPDATE,
                    NORMAL_USER_DELETE,
                    NORMAL_USER_CREATE,
                    PREMIUM_USER_READ,
                    PREMIUM_USER_UPDATE,
                    PREMIUM_USER_DELETE,
                    PREMIUM_USER_CREATE
            )
    ),

    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

package com.uok.tripplanner.authservice.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PermissionTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Permission#valueOf(String)}
     *   <li>{@link Permission#getPermission()}
     * </ul>
     */
    @Test
    void testValueOf() {
        assertEquals("admin:read", Permission.valueOf("ADMIN_READ").getPermission());
    }
}


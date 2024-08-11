package com.uok.tripplanner.authservice.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.uok.tripplanner.authservice.user.User;
import org.junit.jupiter.api.Test;

public class TokenTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Token#Token()}
     *   <li>{@link Token#setExpired(boolean)}
     *   <li>{@link Token#setId(Integer)}
     *   <li>{@link Token#setRevoked(boolean)}
     *   <li>{@link Token#setToken(String)}
     *   <li>{@link Token#setTokenType(TokenType)}
     *   <li>{@link Token#setUser(User)}
     *   <li>{@link Token#toString()}
     *   <li>{@link Token#getId()}
     *   <li>{@link Token#getToken()}
     *   <li>{@link Token#getTokenType()}
     *   <li>{@link Token#getUser()}
     *   <li>{@link Token#isExpired()}
     *   <li>{@link Token#isRevoked()}
     * </ul>
     */
    @Test
    public void testConstructor() {
        Token actualToken = new Token();
        actualToken.setExpired(true);
        actualToken.setId(1);
        actualToken.setRevoked(true);
        actualToken.setToken("ABC123");
        actualToken.setTokenType(TokenType.BEARER);
        User user = new User();
        actualToken.setUser(user);
        String actualToStringResult = actualToken.toString();
        assertEquals(1, actualToken.getId().intValue());
        assertEquals("ABC123", actualToken.getToken());
        assertEquals(TokenType.BEARER, actualToken.getTokenType());
        assertSame(user, actualToken.getUser());
        assertTrue(actualToken.isExpired());
        assertTrue(actualToken.isRevoked());
        assertEquals(
                "Token(id=1, token=ABC123, tokenType=BEARER, revoked=true, expired=true, user=User(id=null, firstname=null,"
                        + " lastname=null, email=null, password=null, role=null, tokens=null))",
                actualToStringResult);
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals() {
        assertNotEquals(new Token(), null);
        assertNotEquals(new Token(), "Different type to Token");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Token#equals(Object)}
     *   <li>{@link Token#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals2() {
        Token token = new Token();
        assertEquals(token, token);
        int expectedHashCodeResult = token.hashCode();
        assertEquals(expectedHashCodeResult, token.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Token#equals(Object)}
     *   <li>{@link Token#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals3() {
        Token token = new Token();
        Token token2 = new Token();
        assertEquals(token, token2);
        int expectedHashCodeResult = token.hashCode();
        assertEquals(expectedHashCodeResult, token2.hashCode());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals4() {
        Token token = new Token(1, "ABC123", TokenType.BEARER, true, true, new User());
        assertNotEquals(token, new Token());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals5() {
        Token token = new Token();
        token.setId(1);
        assertNotEquals(token, new Token());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals6() {
        Token token = new Token();
        token.setToken("ABC123");
        assertNotEquals(token, new Token());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals7() {
        Token token = new Token();
        token.setExpired(true);
        assertNotEquals(token, new Token());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals8() {
        Token token = new Token();
        token.setUser(new User());
        assertNotEquals(token, new Token());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals9() {
        Token token = new Token(1, "ABC123", TokenType.BEARER, true, true, mock(User.class));
        assertNotEquals(token, new Token());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Token#equals(Object)}
     *   <li>{@link Token#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals10() {
        Token token = new Token(1, "ABC123", TokenType.BEARER, true, true, new User());
        Token token2 = new Token(1, "ABC123", TokenType.BEARER, true, true, new User());

        assertEquals(token, token2);
        int expectedHashCodeResult = token.hashCode();
        assertEquals(expectedHashCodeResult, token2.hashCode());
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals11() {
        Token token = new Token();

        Token token2 = new Token();
        token2.setId(1);
        assertNotEquals(token, token2);
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals12() {
        Token token = new Token();

        Token token2 = new Token();
        token2.setToken("ABC123");
        assertNotEquals(token, token2);
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals13() {
        Token token = new Token();

        Token token2 = new Token();
        token2.setUser(new User());
        assertNotEquals(token, token2);
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals14() {
        Token token = new Token(1, "ABC123", null, true, true, new User());
        assertNotEquals(token, new Token(1, "ABC123", TokenType.BEARER, true, true, new User()));
    }

    /**
     * Method under test: {@link Token#equals(Object)}
     */
    @Test
    public void testEquals15() {
        Token token = new Token(1, "ABC123", TokenType.BEARER, true, true, new User());
        assertNotEquals(token, new Token(1, "ABC123", null, true, true, new User()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Token#equals(Object)}
     *   <li>{@link Token#hashCode()}
     * </ul>
     */
    @Test
    public void testEquals16() {
        Token token = new Token(1, "ABC123", null, true, true, new User());
        Token token2 = new Token(1, "ABC123", null, true, true, new User());

        assertEquals(token, token2);
        int expectedHashCodeResult = token.hashCode();
        assertEquals(expectedHashCodeResult, token2.hashCode());
    }
}


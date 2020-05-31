package model;

/**
 * Created by samosbor on 5/16/18.
 */

public class AuthorizationToken {
    /**
     * An authorization token
     */
    String token;

    /**
     * The constructor for an authorization token
     * @param token
     */
    public AuthorizationToken(String token) {
        this.token = token;
    }

    /**
     * Gets the authorization token
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authorization token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Checks to see if an authorization token is equal to another
     * @param o
     * @return true if the authorization tokens are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizationToken)) return false;

        AuthorizationToken that = (AuthorizationToken) o;

        return getToken().equals(that.getToken());
    }

    /**
     * Gets the hashcode value of the authorization token
     * @return the hashcode value of the authorization token
     */
    @Override
    public int hashCode() {
        return getToken().hashCode();
    }

    /**
     * Gets the string representation of the authorization token object
     * @return the string that represents the authorization token
     */
    @Override
    public String toString() {
        return "AuthorizationToken{" +
                "token='" + token + '\'' +
                '}';
    }
}

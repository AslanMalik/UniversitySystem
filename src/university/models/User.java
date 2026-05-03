package university.models;

import university.enums.Language;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract base class for all users in the university system.
 * Every user has a unique identifier, credentials, and a language preference.
 */
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String login;
    private String password;
    private Language language;

    /**
     * Constructs a User with the given credentials and language.
     * A random UUID is automatically generated for {@code id}.
     *
     * @param login    unique login name
     * @param password plain-text password (store/hash as appropriate)
     * @param language preferred interface language
     */
    public User(String login, String password, Language language) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.language = language;
    }

    /**
     * Displays the role-specific menu for this user.
     * Each subclass must provide its own menu implementation.
     */
    public abstract void displayMenu();

    /**
     * Checks whether the supplied string matches this user's password.
     *
     * @param input the password to verify
     * @return {@code true} if {@code input} equals the stored password
     */
    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    /**
     * Returns the unique identifier of this user.
     *
     * @return the UUID
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this user.
     *
     * @param id the new UUID
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the login name.
     *
     * @return login string
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login name.
     *
     * @param login new login string
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the password.
     *
     * @return password string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password new password string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the preferred language.
     *
     * @return {@link Language} enum value
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the preferred language.
     *
     * @param language new language preference
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    // -------------------------------------------------------------------------
    // Object overrides
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", language=" + language +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

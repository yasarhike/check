package org.insta.authentication.service;

import org.insta.authentication.model.User;

/**
 * <p>
 * Represents the UserService interface for managing user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface UserAccountService {

    /**
     * <p>
     * Validates the user object details and creates an account if the details are correct.
     * </p>
     *
     * @param user Refers to the {@link User} object representing the user whose account is to be created.
     * @return True if the user details are correct and the account is successfully created, otherwise false.
     */
    boolean createProfile(final User user);

    /**
     * <p>
     * Validates the user input details and allows the user to login if the details are correct.
     * </p>
     *
     * @param mobileOrEmail Refers to the mobile number, email of the user.
     * @param id            Refers to the ID of the user.
     * @param password      Refers to the password of the user.
     * @param type          Refers to the type of key (e.g., "mobile", "mail", etc.).
     * @return {@link User} Object representing the authenticated user if the details are correct, otherwise null.
     */
    User getProfile(final String mobileOrEmail, final int id, final String password, final String type);
}

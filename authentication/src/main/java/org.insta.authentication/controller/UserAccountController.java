package org.insta.authentication.controller;

import org.insta.authentication.dao.UserAccountDAOImpl;
import org.insta.authentication.view.Key;
import org.insta.authentication.model.User;

/**
 * <p>
 * Managing user accounts provides methods to create, retrieve, update, and delete user account
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class UserAccountController {

    private static UserAccountController profileController;
    private final UserAccountDAOImpl userAccountDAOImpl;

    /**
     * <p>
     * Restrict object creation outside the class
     * </p>
     */
    private UserAccountController() {
        userAccountDAOImpl = UserAccountDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountController class.
     * </p>
     *
     * @return Singleton instance of UseAccountController class.
     */
    public static UserAccountController getInstance() {
        return profileController == null ? profileController = new UserAccountController() : profileController;
    }

    /**
     * <p>
     * Invokes the service createProfile method to create a new user profile.
     * </p>
     *
     * @param user Refers the {@link User} object representing the user profile to be created.
     * @return True if the user profile is successfully created, otherwise false.
     */
    public boolean createProfile(final User user) {
        return userAccountDAOImpl.createProfile(user);
    }

    /**
     * <p>
     * Retrieves the user profile based on the provided credentials.
     * </p>
     *
     * @param emailOrMobileOrName Email, mobile number, or name associated with the user profile.
     * @param password            Password associated with the user profile.
     * @param key                 Key refers to type.
     * @return {@link User} profile if found, otherwise null.
     */
    public User getProfile(final String emailOrMobileOrName, final String password, final Key key) {
        return userAccountDAOImpl.getProfile(emailOrMobileOrName, password, key);
    }

    /**
     * <p>
     * Updates the user profile with the provided information.
     * </p>
     *
     * @param user {@link User} object containing updated profile information.
     * @return True if the profile is successfully updated, otherwise false.
     */
    public boolean updateProfile(final User user) {
        return userAccountDAOImpl.updateProfile(user);
    }

    /**
     * <p>
     * Deletes the user profile associated with the given ID.
     * </p>
     *
     * @param id Unique identifier of the user profile to be deleted.
     * @return True If the profile is successfully deleted, otherwise false.
     */
    public boolean deleteProfile(final int id) {
        return userAccountDAOImpl.deleteProfile(id);
    }
}

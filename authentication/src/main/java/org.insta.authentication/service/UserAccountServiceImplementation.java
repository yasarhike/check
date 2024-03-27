package org.insta.authentication.service;

import org.insta.authentication.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implementation of the UserService interface for user account service.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class UserAccountServiceImplementation implements UserAccountService {

    private static UserAccountServiceImplementation userAccountServiceImplementation;
    private static int userId = 1;
    public final Map<String, User> usersByMailEmail;
    public final Map<Integer, User> usersById;

    /**
     * <p>
     * Private constructor to restrict object creation outside the class
     * </p>
     */
    private UserAccountServiceImplementation() {
        usersByMailEmail = new HashMap<>();
        usersById = new HashMap<>();
    }

    /**
     * <p>
     * Creates a singleton instance of UserAccountServiceImplementation class if it is not already created.
     * </p>
     *
     * @return the singleton instance of UserAccountServiceImplementation class.
     */
    public static UserAccountServiceImplementation getInstance() {
        return userAccountServiceImplementation == null ? userAccountServiceImplementation = new UserAccountServiceImplementation() : userAccountServiceImplementation;
    }

    /**
     * <p>
     * Validates the user object details and creates an account if the details are correct.
     * </p>
     *
     * @param user Refer the {@link User}
     * @return True if the user is successfully signed up, otherwise false.
     */
    public boolean createProfile(final User user) {
        return user.getEmail() == null ? createProfileByMobile(user) : createProfileByEmail(user);
    }

    /**
     * <p>
     * Validates the user input details and return the user object if correct.
     * </p>
     *
     * @param mobileOrEmail Refers mobile number or email of the user.
     * @param password      Refers the password of the user.
     * @return {@link User} Returns the User object if successful, otherwise null.
     */
    public User getProfile(final String mobileOrEmail, final int id, final String password, final String type) {
        return type.equals("mailOrMobile") ? getProfileByMailEmail(mobileOrEmail, password) : getProfileById(id, password);
    }

    /**
     * <p>
     * Validates the user input details and return the user object if correct.
     * </p>
     *
     * @param mobileOrEmail Refers mobile number or email of the user.
     * @param password      Refers the password of the user.
     * @return {@link User} Returns the User object if successful, otherwise null.
     */
    private User getProfileByMailEmail(final String mobileOrEmail, final String password) {
        return usersByMailEmail.getOrDefault(mobileOrEmail, null);
    }

    /**
     * <p>
     * Validates the user input details and return the user object if correct.
     * </p>
     *
     * @param id       Refers the ID of the user.
     * @param password Refers the password of the user.
     * @return {@link User} Returns the User object if successful, otherwise null.
     */
    private User getProfileById(final int id, final String password) {
        return usersById.getOrDefault(id, null);
    }

    /**
     * <p>
     * Validates the user input details and allow to create an account by mobile number.
     * </p>
     *
     * @param user Refers to the {@link User} object representing the user account.
     * @return True if the account is successfully created, otherwise false.
     */
    private boolean createProfileByMobile(final User user) {
        if (!usersByMailEmail.containsKey(user.getMobileNumber())) {
            usersByMailEmail.put(user.getMobileNumber(), user);
            usersById.put(userId, usersByMailEmail.get(user.getMobileNumber()));
            user.setUserId(userId++);
            return true;
        }
        return false;
    }

    /**
     * <p>
     * Validates the user input details and allow to create an account by mail.
     * </p>
     *
     * @param user Refers to the {@link User} object representing the user account.
     * @return True if the account is successfully created, otherwise false.
     */
    private boolean createProfileByEmail(final User user) {
        if (!usersByMailEmail.containsKey(user.getEmail()) && !usersById.containsKey(user.getUserId())) {
            usersByMailEmail.put(user.getEmail(), user);
            usersById.put(userId, usersByMailEmail.get(user.getEmail()));
            user.setUserId(userId++);
            return true;
        }
        return false;
    }

    /**
     * <p>
     * Checks if a user with the given ID is registered.
     * </p>
     *
     * @param user ID of the user to check.
     * @return True if the user with the given ID is registered, otherwise false.
     */
    public boolean isUserExists(final int user) {
        return usersById.containsKey(user);
    }

    /**
     * <p>
     * Updates the specified type of information for the given user.
     * </p>
     *
     * @param type     Refers to the type of information to be updated (e.g., "name", "email", "password", "mobile").
     * @param newValue Refers to the new value to be set for the specified type of information.
     * @param user     Refers to the {@link User} whose information is to be updated.
     */
    public void updateProfile(final String type, final String newValue, final User user) {
        switch (type) {
            case "name" : updateName(newValue, user);
            case "email" : updateMail(newValue, user);
            case "password" : updatePassword(newValue, user);
            case "mobile" : updateMobile(newValue, user);
            default : {
            }
        }
    }

    /**
     * <p>
     * Updates the name of the user.
     * </p>
     *
     * @param newName The new name to be set for the user.
     * @param user    Refers to the {@link User} whose name is to be updated.
     */
    private void updateName(final String newName, final User user) {
        if (usersById.containsKey(user.getUserId())) {
            usersById.get(user.getUserId()).setName(newName);
            user.setName(newName);
        }
    }

    /**
     * <p>
     * Updates the email of the user.
     * </p>
     *
     * @param newValue The new email to be set for the user.
     * @param user     Refers to the {@link User} whose email is to be updated.
     */
    private void updateMail(final String newValue, final User user) {
        if (usersByMailEmail.containsKey(user.getEmail())) {
            usersByMailEmail.get(user.getEmail()).setEmail(newValue);
            usersByMailEmail.put(newValue, usersByMailEmail.get(user.getEmail()));
            usersByMailEmail.remove(user.getEmail());
            user.setEmail(newValue);
        }
    }

    /**
     * <p>
     * Updates the mobile of the user.
     * </p>
     *
     * @param newValue The new mobile to be set for the user.
     * @param user     Refers to the {@link User} whose mobile is to be updated.
     */
    private void updateMobile(final String newValue, final User user) {
        if (usersByMailEmail.containsKey(user.getMobileNumber())) {
            usersByMailEmail.get(user.getMobileNumber()).setMobileNumber(newValue);
            usersByMailEmail.put(newValue, usersByMailEmail.get(user.getMobileNumber()));
            usersByMailEmail.remove(user.getMobileNumber());
            user.setMobileNumber(newValue);
        }
    }

    /**
     * <p>
     * Updates the password of the user.
     * </p>
     *
     * @param newValue The new password to be set for the user.
     * @param user     Refers to the {@link User} whose password is to be updated.
     */
    private void updatePassword(final String newValue, final User user) {
        usersByMailEmail.get(user.getPassword()).setPassword(newValue);
        user.setPassword(newValue);
    }
}


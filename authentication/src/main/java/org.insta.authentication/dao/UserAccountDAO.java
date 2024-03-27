package org.insta.authentication.dao;

import org.insta.authentication.model.User;
import org.insta.authentication.view.Key;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface UserAccountDAO {

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data
     * @return True if the user profile is successfully , otherwise false.
     */
    boolean createProfile(final User user);

    /**
     * <p>
     * Gets a user profile.
     * </p>
     *
     * @param keyValue contains the value of the key(mobile, email, username).
     * @param key      Refers the key {@link Key} type.
     * @param password Refers the password of the user.
     * @return True if the user profile is successfully , otherwise false.
     */
    User getProfile(final String keyValue, final String password, final Key key);

    /**
     * <p>
     * Update a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data.
     * @return True if the user profile is successfully , otherwise false.
     */
    boolean updateProfile(final User user);

    /**
     * <p>
     * Deletes a user profile.
     * </p>
     *
     * @param id contains the id of the user profile.
     * @return True if the user profile is successfully , otherwise false.
     */
    boolean deleteProfile(final int id);
}

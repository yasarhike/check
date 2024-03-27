package org.insta.authentication.dao;

import org.insta.authentication.dao.queryhandler.QueryHandler;
import org.insta.authentication.model.User;
import org.insta.authentication.view.Key;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.databaseconnection.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class UserAccountDAOImpl implements UserAccountDAO {

    private static UserAccountDAOImpl userAccountDAOImpl;
    private final Connection connection;
    private final QueryHandler queryHandler;
    private final Logger logger = LogManager.getLogger(UserAccountDAOImpl.class);

    /**
     * <p>
     * Prevent instantiation from outside the class.
     * </p>
     */
    private UserAccountDAOImpl() {
        queryHandler = QueryHandler.getInstance();
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountDAOImpl.
     * </p>
     *
     * @return Singleton instance of UserAccountDAOImpl.
     */
    public static UserAccountDAOImpl getInstance() {
        return userAccountDAOImpl == null ? userAccountDAOImpl = new UserAccountDAOImpl() : userAccountDAOImpl;
    }

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data
     * @return True if the user profile is successfully , otherwise false.
     */
    public boolean createProfile(final User user) {

        try {
            connection.setAutoCommit(false);

            final PreparedStatement preparedStatement = connection.prepareStatement
                    (queryHandler.getCreateAccountQuery(), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAddress().getDoorNumber());
            preparedStatement.setString(6, user.getAddress().getState());

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return queryHandler.setUserId(preparedStatement, user);
            }
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                logger.debug("Rollback failed");
            }
            logger.debug("Account creation failed");
        }

        return false;
    }

    /**
     * <p>
     * Update a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data.
     * @return True if the user profile is successfully , otherwise false.
     */
    public boolean updateProfile(final User user) {
        final List<Object> list = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement
                (queryHandler.generateQuery(user, list))) {
            int index = 0;

            connection.setAutoCommit(false);

            for (final Object element : list) {
                preparedStatement.setObject(++index, element);
            }

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (final SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                logger.debug("Rollback failed");
            }

            logger.debug("Operation failed");
        }
        return false;
    }

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
    public User getProfile(final String keyValue, final String password, final Key key) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(queryHandler.getQuery(key))) {

            connection.setAutoCommit(true);
            preparedStatement.setString(1, keyValue);
            preparedStatement.setString(2, password);

            final ResultSet resultSet = preparedStatement.executeQuery();

            return queryHandler.setUser(resultSet);

        } catch (SQLException exception) {
            logger.warn("Operation failed");
        }
        return null;
    }

    /**
     * <p>
     * Deletes a user profile.
     * </p>
     *
     * @param id contains the id of the user profile.
     * @return True if the user profile is successfully , otherwise false.
     */
    public boolean deleteProfile(final int id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(queryHandler.getDeleteAccountQuery())) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                logger.debug("Rollback failed");
            }
            logger.debug("Operation failed");
        }
        return false;
    }
}

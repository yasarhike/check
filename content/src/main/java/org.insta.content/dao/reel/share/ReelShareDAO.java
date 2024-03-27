package org.insta.content.dao.reel.share;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user reel share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class ReelShareDAO {

    private static ReelShareDAO reelShareDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelShareDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelShareDAO class.
     * </p>
     *
     * @return The singleton instance of ReelShareDAO class.
     */
    public static ReelShareDAO getInstance() {
        return reelShareDAO == null ? reelShareDAO = new ReelShareDAO() : reelShareDAO;
    }

    /**
     * <p>
     * Add the people who shared the reel.
     * </p>
     *
     * @return True if it is added successfully, otherwise false.
     */
    public boolean reelShare(final int userId, final int reelId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO reel_share (reel_id, shared_by)"
                        , "VALUES (?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
            System.out.println("Operation failed");
        }

        return false;
    }

    /**
     * <p>
     * Remove the people who unshared the reel.
     * </p>
     *
     * @return True if it is unshared successfully, otherwise false.
     */
    public boolean removeShare(final int userId, final int postId, final int shareId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from reel_share "
                        , "where reel_share = ? and liked_by = ? and id = ?"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, shareId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}

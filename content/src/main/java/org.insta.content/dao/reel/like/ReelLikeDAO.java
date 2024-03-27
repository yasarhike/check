package org.insta.content.dao.reel.like;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user reel like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class ReelLikeDAO {

    private static ReelLikeDAO reelLikeDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelLikeDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelLikeDAO class.
     * </p>
     *
     * @return The singleton instance of ReelLikeDAO class.
     */
    public static ReelLikeDAO getInstance() {
        return reelLikeDAO == null ? reelLikeDAO = new ReelLikeDAO() : reelLikeDAO;
    }

    /**
     * <p>
     * Add a like for the particular reel.
     * </p>
     *
     * @param reelId Refers the reelId for the reel.
     * @param userId Refers the userId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean reelLike(final int userId, final int reelId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO reel_like (reel_id, liked_by)"
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
     * Unlike a particular reel
     * </p>
     *
     * @param reelId Refers the reelId for the reel.
     * @param userId Refers the userId for the user.
     * @return True if the unlike is done successfully, otherwise false.
     */
    public boolean reelUnlike(final int userId, final int reelId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from reel_like "
                        , "where reel_id = ? and liked_by = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}

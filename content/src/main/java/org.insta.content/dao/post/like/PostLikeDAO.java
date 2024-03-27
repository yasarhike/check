package org.insta.content.dao.post.like;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostLikeDAO {

    private static PostLikeDAO postLikeDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostLikeDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeDAO class.
     * </p>
     *
     * @return The singleton instance of POstLikeDAO class.
     */
    public static PostLikeDAO getInstance() {
        return postLikeDAO == null ? postLikeDAO = new PostLikeDAO() : postLikeDAO;
    }

    /**
     * <p>
     * Add a like for the particular post
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean postLike(final int userId, final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO post_like (post_id, liked_by)", "VALUES (?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
            System.out.println("Operation failed");
        }

        return false;
    }

    /**
     * <p>
     * Unlike a particular post
     * </p>
     *
     * @param postId Refers the postId for the post.
     * @param userId Refers the userId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean postUnlike(final int userId, final int postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from post_like ", "where post_id = ? and liked_by = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException ignored) {
        }

        return false;
    }
}

package org.insta.content.dao.reel.comment;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user reel comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class ReelCommentDAO {

    private static ReelCommentDAO reelCommentDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelCommentDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelCommentDAO class.
     * </p>
     *
     * @return The singleton instance of ReelCommentDAO class.
     */
    public static ReelCommentDAO getInstance() {
        return reelCommentDAO == null ? reelCommentDAO = new ReelCommentDAO() : reelCommentDAO;
    }

    /**
     * <p>
     * Add a comment for the particular reel.
     * </p>
     *
     * @param reelId  Refers the reelId for the reel.
     * @param userId  Refers the userId for the user.
     * @param caption Refers the caption of the reel.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean addComment(final int userId, final int reelId, final String caption) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO reel_comment (reel_id, commented_by, content )", "VALUES (?, ?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, caption);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
            System.out.println("Operation failed");
        }

        return false;
    }

    /**
     * <p>
     * Delete a comment for the particular reel.
     * </p>
     *
     * @param reelId    Refers the reelId for the reel.
     * @param userId    Refers the userId for the user.
     * @param commentId Refers the commentId of the comment.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean deleteComment(final int userId, final int reelId, final int commentId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from reel_comment ", "where reel_id = ? and commented_by = ? and id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, reelId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, commentId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}

package org.insta.content.dao.post.comment;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user post comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostCommentDAO {

    private static PostCommentDAO postCommentDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentDAO class.
     * </p>
     *
     * @return The singleton instance of PstCommentDAO class.
     */
    public static PostCommentDAO getInstance() {
        return postCommentDAO == null ? postCommentDAO = new PostCommentDAO() : postCommentDAO;
    }

    /**
     * <p>
     * Post a comment for the post
     * </p>
     *
     * @param userId  Refers the id of the user.
     * @param postId  Refers the id of the post.
     * @param caption Refers the caption for the post.
     * @return True if the comment is added successfully, otherwise false.
     */
    public boolean postComment(final int userId, final int postId, final String caption) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO post_comment (post_id, commented_by, content )"
                        , "VALUES (?, ?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
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
     * Delete a comment for the post
     * </p>
     *
     * @param userId    Refers the id of the user.
     * @param postId    Refers the id of the post.
     * @param commentId Refers the commentId for the post.
     * @return True if the comment is deleted successfully, otherwise false.
     */
    public boolean deleteComment(final int userId, final int postId, final int commentId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "Delete from post_comment "
                        , "where post_id = ? and commented_by = ? and id = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, commentId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}

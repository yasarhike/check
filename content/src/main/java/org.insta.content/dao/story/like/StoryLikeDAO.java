package org.insta.content.dao.story.like;

import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Managing user story like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class StoryLikeDAO {

    private static StoryLikeDAO storyLikeDAO;
    private final Connection connection;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryLikeDAO() {
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeDAO class.
     * </p>
     *
     * @return The singleton instance of StoryLikeDAO class.
     */
    public static StoryLikeDAO getInstance() {
        return storyLikeDAO == null ? storyLikeDAO = new StoryLikeDAO() : storyLikeDAO;
    }

    /**
     * <p>
     * Add a like for the particular story.
     * </p>
     *
     * @param userId  Refers the userId for the user.
     * @param storyId Refers the storyId of the story.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean storyLike(final int userId, final int storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "INSERT INTO story_like (story_id, liked_by)", "VALUES (?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
            System.out.println("Operation failed");
        }

        return false;
    }

    /**
     * <p>
     * Remove a like for the particular post
     * </p>
     *
     * @param userId  Refers the userId for the user.
     * @param storyId Refers the storyId of the story.
     * @return True if the UnLike is done successfully, otherwise false.
     */
    public boolean storyUnlike(final int userId, final int storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(String.join(" ", "Delete from story_like ", "where story_id = ? and liked_by = ? "))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, storyId);
            preparedStatement.setInt(2, userId);

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException ignored) {
        }

        return false;
    }
}

package org.insta.content.dao.story;

import org.insta.content.model.story.Story;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class StoryServiceDAOImpl implements StoryServiceDAO {

    private static StoryServiceDAOImpl storyServiceDAOImpl;
    private final Connection connection;
    private final Logger logger = LogManager.getLogger(StoryServiceDAOImpl.class);;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryServiceDAOImpl() {
        connection = DatabaseConnection.get();
    }

    public static StoryServiceDAOImpl getInstance() {
        return storyServiceDAOImpl == null ? storyServiceDAOImpl = new StoryServiceDAOImpl() : storyServiceDAOImpl;
    }

    /**
     * <p>
     * Add a story of the user.
     * </p>
     *
     * @param story  {@link Story} Refers the story for the user.
     * @param userId Refers the userId of the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean addStory(final Story story, final Integer userId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(String.join(" ", "INSERT INTO STORY  (user_id, caption ",
                        ", is_private, music, type) VALUES (?, ?, ?, ?, ?)"))) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1, story.getUserId());
            preparedStatement.setString(2, story.getText());
            preparedStatement.setBoolean(3, story.isPrivate());
            preparedStatement.setString(4, story.getMusic());
            preparedStatement.setInt(5, story.getMedia().getId());

            return preparedStatement.executeUpdate() > 0;

        } catch (final SQLException exception) {
            logger.debug("Operation failed");
        }
        return false;
    }

    /**
     * <p>
     * Display a story of the user.
     * </p>
     *
     * @param userId Refers the userId of the user.
     * @return List if story is fetched successfully, otherwise false.
     */
    public List<Story> displayStory(final int userId) {
        List<Story> reels = null;

        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(getStoryDisplayQuery())) {
            reels = new ArrayList<>();

            preparedStatement.setInt(1, userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            return setStory(resultSet, reels);

        } catch (final SQLException exception) {
            logger.debug("Operation failed ");
        }
        return reels;
    }

    /**
     * <p>
     * Sets the fetched details in the list
     * </p>
     *
     * @param resultSet {@link ResultSet} Refers the resultSet which is fetched.
     * @return List if the fetched details is added successfully, otherwise null.
     */
    private List<Story> setStory(final ResultSet resultSet, final List<Story> reels) {
        try {
            while (resultSet.next()) {
                Story story = new Story();

                story.setStoryId(resultSet.getInt(1));
                story.setUserId(resultSet.getInt(2));
                story.setUserName(resultSet.getString(3));
                story.setText(resultSet.getString(4));
                story.setPrivate(resultSet.getBoolean(5));
                story.setMusic(resultSet.getString(6));
                story.setTimestamp(resultSet.getTimestamp(7));
                story.setTotalLikes(resultSet.getInt(8));
                story.setTotalShares(resultSet.getInt(9));
                reels.add(story);
            }
            return reels;
        } catch (final SQLException exception) {
            logger.debug("Operation failed");
        }
        return reels;
    }

    /**
     * <p>
     * Gets the story display query.
     * </p>
     *
     * @return story display query.
     */
    private String getStoryDisplayQuery() {
        return String.join(" ", "select story.id, story.user_id, account.name "
                , " ,  story.caption, story.is_private, story.music, story.created_at "
                , " , count(story_like.story_id), "
                , " count(story_share.story_id) from story"
                , " left join account on account.id = story.user_id "
                , " left join story_like on story.id = story_like.story_id "
                , " left join story_share on story.id = story_share.story_id "
                , " where story.user_id = ? "
                , " group by story.id, account.id "
                , " order by story.id;") ;
    }
}

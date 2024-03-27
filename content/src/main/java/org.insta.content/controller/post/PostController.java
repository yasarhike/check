package org.insta.content.controller.post;

import org.insta.authentication.model.User;
import org.insta.content.dao.post.PostServiceDAOImpl;
import org.insta.content.model.post.Post;

import java.util.List;


/**
 * <p>
 * Implementation of the PostController class for managing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostController {

    private static PostController postController;
    private final PostServiceDAOImpl postServiceDAOImpl;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostController() {
        postServiceDAOImpl = PostServiceDAOImpl.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostController class.
     * </p>
     *
     * @return The singleton instance of PostController class.
     */
    public static PostController getInstance() {
        return postController == null ? postController = new PostController() : postController;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post Refer to the {@link Post} of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    public boolean addPost(final Post post) {
        return postServiceDAOImpl.addPost(post);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to postId of the post.
     * @param userId The userId of the user removing the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    public boolean removePost(final Integer postId, final Integer userId) {
        return postServiceDAOImpl.removePost(postId, userId);
    }

    /**
     * <p>
     * Updates the post with the specified ID.
     * </P>
     *
     * @param post Refer to {@link Post} of the user.
     * @return True if the post is updated successfully, otherwise false.
     */
    public boolean updatePost(final Post post) {
        return postServiceDAOImpl.updatePost(post);
    }

    /**
     * <p>
     * Displays the post with the specified ID.
     * </P>
     *
     * @param userId Refer to user id of the user.
     * @return List if the post is fetched successfully, otherwise null.
     */
    public List<Post> display(final User user) {
        return postServiceDAOImpl.displayPost(user);
    }
}
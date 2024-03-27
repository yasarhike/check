package org.insta.content.service.post;


import org.insta.content.model.post.Post;
import org.insta.content.service.home.content.ContentServiceImpl;

import java.util.Map;

/**
 * <p>
 * Implementation class for managing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostServiceImpl {

    private static PostServiceImpl postServiceImplementation;
    private final ContentServiceImpl<Post> postService;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostServiceImpl() {
        postService = new ContentServiceImpl<>();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the singleton instance of PostServiceImplementation class.
     * </p>
     *
     * @return The singleton instance of PostServiceImplementation class.
     */
    public static PostServiceImpl getInstance() {
        return postServiceImplementation == null ? postServiceImplementation = new PostServiceImpl()
                : postServiceImplementation;
    }

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post   Refer to the post to the user.
     * @param userId Refer the userId of the user adding the post.
     * @return True if the post is added successfully, otherwise false.
     */
    public boolean addPost(final Post post, final Integer userId) {
        post.setPostId(ContentServiceImpl.getContentCount());

        return postService.add(post, userId);
    }

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to id of the post.
     * @param userId The userId of the user removing the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    public boolean removePost(final int postId, final Integer userId) {
        return postService.remove(postId, userId);
    }

    /**
     * <p>
     * Retrieves all post.
     * </p>
     *
     * @return Map contains the user story.
     */
    public Map<Integer, Map<Integer, Post>> getPost() {
        return postService.getContent();
    }
}
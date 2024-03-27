package org.insta.content.service.post;

import org.insta.content.model.post.Post;

/**
 * <p>
 * Managing post service operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostService {

    /**
     * <p>
     * Adds a post for the specified user.
     * </p>
     *
     * @param post   Refer to the post to the user.
     * @param userId Refer the userId of the user adding the post.
     * @return True if the post is added successfully, otherwise false.
     */
    boolean addPost(final Post post, final Integer userId);

    /**
     * <p>
     * Removes a post with the specified ID for the specified user.
     * </P>
     *
     * @param postId Refer to id of the post.
     * @param userId The userId of the user removing the post.
     * @return True if the post is removed successfully, otherwise false.
     */
    boolean removePost(final int postId, final Integer userId);
}

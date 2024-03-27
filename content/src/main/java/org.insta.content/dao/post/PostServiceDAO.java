package org.insta.content.dao.post;

import org.insta.content.model.post.Post;

/**
 * <p>
 * Managing user post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface PostServiceDAO {

    /**
     * <p>
     * Post a video or image for the user account
     * </p>
     *
     * @param post {@link Post} Refers the post for the user.
     * @return True if the post is added successfully, otherwise false.
     */
    boolean addPost(final Post post);

    /**
     * <p>
     * Delete a post for the user account
     * </p>
     *
     * @param postId Refers the postId for the user.
     * @param userId Refers the userId of the user.
     * @return True if the post is added successfully, otherwise false.
     */
    boolean removePost(final int postId, final Integer userId);
}

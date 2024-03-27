package org.insta.content.controller.post.comment;

import org.insta.content.dao.post.comment.PostCommentDAO;

/**
 * <p>
 * Implemented class for managing post comment operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostCommentController {

    private static PostCommentController postCommentController;
    private final PostCommentDAO postCommentDAO;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentController() {
        postCommentDAO = PostCommentDAO.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentController class.
     * </p>
     *
     * @return The singleton instance of PostCommentController class.
     */
    public static PostCommentController getInstance() {
        return postCommentController == null ? postCommentController = new PostCommentController() : postCommentController;
    }

    /**
     * <p>
     * Adds a comment for the specified post.
     * </p>
     *
     * @param userId  Refer to the userId of the user.
     * @param postId  Refer the postId of the post.
     * @param caption Refer the caption of the post.
     * @return True if the comment is added successfully, otherwise false.
     */
    public boolean addComment(final int userId, final int postId, final String caption) {
        return postCommentDAO.postComment(userId, postId, caption);
    }

    /**
     * <p>
     * Removes a comment for the specified post.
     * </p>
     *
     * @param userId    Refer to the userId of the user.
     * @param postId    Refer the postId of the post.
     * @param commentId Refer the commentId of the post.
     * @return True if the comment is removed successfully, otherwise false.
     */
    public boolean removeComment(final int commentId, final int userId, final int postId) {
        return postCommentDAO.deleteComment(commentId, userId, postId);
    }
}

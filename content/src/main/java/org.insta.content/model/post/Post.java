package org.insta.content.model.post;

import org.insta.content.model.home.Media;

import java.sql.Timestamp;

/**
 * <p>
 * Contain post details of the user.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Post {

    private String caption;
    private Media type;
    private boolean isPrivate;
    private int postId;
    private int userId;
    private String userName;
    private int likeCount;
    private int commentCount;
    private int shareCount;

    private Timestamp timestamp;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public Media getType() {
        return type;
    }

    public void setType(final Media type) {
        this.type = type;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(final int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(final int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(final int likeCount) {
        this.likeCount = likeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(final int shareCount) {
        this.shareCount = shareCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String toString() {
        return (String.join(" "
                , "\nPost id : ", Integer.toString(this.getPostId())
                , "\nCaption : ", this.getCaption()
                , "\nMedia : ", this.getType().toString()
                , "\nTimestamp : ", this.getTimestamp().toString()
                , "\nTotal likes : ", Integer.toString(this.getLikeCount())
                , "\nTotal comments : ", Integer.toString(this.getCommentCount())
                , "\nTotal shares : ", Integer.toString(this.getShareCount()), "\n"));
    }
}

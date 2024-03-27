package org.insta.content.model.reel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Contain the reel details of the user.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Reel {

    private String caption;
    private Boolean isPrivate;
    private int reelId;
    private int userId;
    private String duration;
    private Timestamp timestamp;
    private String userName;
    private int total_likes;
    private int total_comment;
    private int total_shares;
    private List<String> hashTags;

    public Boolean isPrivate() {
        return isPrivate;
    }

    public int getReelId() {
        return reelId;
    }

    public void setReelId(final int reelId) {
        this.reelId = reelId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(final String caption) {
        this.caption = caption;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(final Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public int getTotal_comment() {
        return total_comment;
    }

    public void setTotal_comment(final int total_comment) {
        this.total_comment = total_comment;
    }

    public int getTotal_likes() {
        return total_likes;
    }

    public void setTotal_likes(final int total_likes) {
        this.total_likes = total_likes;
    }

    public int getTotal_shares() {
        return total_shares;
    }

    public void setTotal_shares(final int total_shares) {
        this.total_shares = total_shares;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(final String hashTags) {
        String[] tagsArray = hashTags.split("#");
        this.hashTags = new ArrayList<>();

        for (final String tag : tagsArray) {
            final String trimmedTag = tag.trim();
            if (!trimmedTag.isEmpty()) {
                this.hashTags.add(trimmedTag);
            }
        }
    }

    public String toString() {

        return (String.join(" "
                , "\nReel id", Integer.toString(this.getReelId())
                , "\nAuthor : ", this.getUserName()
                , "\nCaption : ", this.getCaption()
                , "\nDuration : ", this.getDuration()
                , "\nTimeStamp : ", this.getTimestamp().toString()
                , "\nTotalLikes : ", Integer.toString(this.getTotal_likes())
                , "\nTotalComments : ", Integer.toString(this.getTotal_comment())
                , "\nTotalShares : ", Integer.toString(this.getTotal_shares())
                , "\n")
        );
    }
}

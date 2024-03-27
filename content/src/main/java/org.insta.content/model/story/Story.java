package org.insta.content.model.story;

import org.insta.content.model.home.Media;

import java.sql.Timestamp;

/**
 * <p>
 * Contains story details of the user.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class Story {

    private boolean isPrivate;
    private int storyId;
    private Media media;
    private String music;
    private String text;
    private int userId;
    private String userName;
    private int totalLikes;
    private int totalShares;
    private Timestamp timestamp;

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(final String music) {
        this.music = music;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(final int storyId) {
        this.storyId = storyId;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(final int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(final int totalShares) {
        this.totalShares = totalShares;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return (String.join(" "
                , "\nStory_Id : ", Integer.toString(this.getStoryId())
                , "\nAuthor : ", this.getUserName()
                , "\nCaption : ", this.getText()
                , "\nCreated at : ", this.getTimestamp().toString()
                , "\nMusic : ", this.getMusic()
                , "\nTotal likes : ", Integer.toString(this.getTotalLikes())
                , "\nTotal shares :", Integer.toString(this.getTotalShares())
                , "\n"));
    }
}

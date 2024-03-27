package org.insta.content.model.home;

/**
 * <p>
 * Contains the constants for media
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public enum Media {
    IMAGE(1), VIDEO(2);

    private final int id;

    Media(int id) {
        this.id = id;
    }

    public static Media getMedia(final int id) {
        switch (id) {
            case 1: return IMAGE;
            case 2: return VIDEO;
            default: return null;
        }
    }

    public int getId() {
        return id;
    }
}


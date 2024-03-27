package org.insta.content.controller.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;

public class ReelShareController {

    private static ReelShareController reelShareController;
    private final ReelShareDAO reelShareDAO;

    private ReelShareController() {
        reelShareDAO = ReelShareDAO.getInstance();
    }

    public static ReelShareController getInstance() {
        return reelShareController == null ? reelShareController = new ReelShareController() : reelShareController;
    }

    public boolean reelShare(final int userId, final int reelId) {
        return reelShareDAO.reelShare(userId, reelId);
    }

    public boolean removeShare(final int userId, final int postId, final int shareId) {
        return reelShareDAO.removeShare(userId, postId, shareId);
    }
}

package org.insta.main;

import org.insta.authentication.view.AuthenticationView;
import org.insta.content.view.home.HomeView;

/**
 * <p>
 * Initiates the application.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.82 6 Feb 2024
 */
public class Main {
    private static final AuthenticationView authenticationView = AuthenticationView.getInstance();

    /**
     * <p>
     * Invoking the authentication page and start the application.
     * </p>
     */
    public static void main(String[] args) {
        authenticationView.setHomePage(HomeView.getInstance());
        authenticationView.authentication();
    }
}
package sample;

/**
 * Session handles what user is currently logged in
 */
public class Session {

    public static int userID;

    /**
     * Returns a {@code int} userID of the user that is currently logged in
     * @return {@code int} userID of currently logged in user
     */
    public static int getUserID() {
        return userID;
    }

    /**
     * Sets an {@code int} userID of the user that logged in
     * @param userID {@code int} corresponding to id of logged in user in database
     */
    public static void setUserID(int userID) {
        Session.userID = userID;
    }
}

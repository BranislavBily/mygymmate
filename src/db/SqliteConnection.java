package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * and will be punished
 * This code is proprietary and confidential of the person stated bellow
 * Created by branislavbily on 14.3.2019
 * If you are confused, feel free to ask me <branislav.bily@gmail.com>
 */
public class SqliteConnection {

    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:mygymmateDatabase.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

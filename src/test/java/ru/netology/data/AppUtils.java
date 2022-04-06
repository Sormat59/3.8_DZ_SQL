package ru.netology.data;

import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;


public class AppUtils {
    public AppUtils() {

    }

    @SneakyThrows
    public static String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        QueryRunner runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "user", "pass")
        ) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return code;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void clearTables() {
        var deleteCode = "DELETE FROM auth_codes";
        var deleteTransactions = "DELETE FROM card_transactions";
        var deleteCard = "DELETE FROM cards";
        var deleteUser = "DELETE FROM users";
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, deleteCode);
            runner.update(conn, deleteTransactions);
            runner.update(conn, deleteCard);
            runner.update(conn, deleteUser);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}

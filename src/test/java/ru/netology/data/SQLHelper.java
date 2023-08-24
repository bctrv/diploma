package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getDebitStatus() {
        var SQLStatus = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var status = runner.query(connection, SQLStatus, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static String getTransactionID() {
        var SQLId = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows
    public static String getPaymentID() {
        var SQLId = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var SQLStatus = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var status = runner.query(connection, SQLStatus, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static String getCreditInfo() {
        var SQLId = "SELECT bank_id FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows
    public static String getCreditID() {
        var SQLId = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1";
        var connection = getConn();
        var id = runner.query(connection, SQLId, new ScalarHandler<String>());
        return id;
    }

    @SneakyThrows
    public static void cleanDB() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }

}
package SQL;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLMethods {

    String url = "jdbc:mysql://127.0.0.1:3308/diplom";
    String user = "dmitry";
    String password = "21uzd";

    @SneakyThrows
    public String getStatusFromPaymentEntity() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        String status = null;
        try (
                Connection connection = DriverManager.getConnection(
                        url, user, password
                )
        ) {
            status = runner.query(connection, dataSQL, new ScalarHandler<>());
        }
        return status;
    }


    @SneakyThrows
    public long getNumberOfRawsFromPaymentEntity() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "SELECT COUNT(transaction_id) FROM payment_entity";
        long numberOfRaws = 0;
        try (
                Connection connection = DriverManager.getConnection(
                        url, user, password
                )
        ) {
            numberOfRaws = runner.query(connection, dataSQL, new ScalarHandler<>());
        }
        return numberOfRaws;
    }


}

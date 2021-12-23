package SQL;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLTests {

    @Test
    @SneakyThrows
    public void SQLmethod() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS";
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/diplom-project", "dmitry", "21uzd"
                )
        ) {
          runner.query(connection, dataSQL, new ScalarHandler<>());
        }

    }
}

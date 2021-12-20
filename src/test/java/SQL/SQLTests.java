package SQL;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLTests {

    @SneakyThrows
    public void SQLmethod() {
        QueryRunner runner = new QueryRunner();
        String dataSQL = "";
        String code = null;
        try (
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/diplom-project", "dmitry", "21uzd"
                )
        ) {
            code = runner.query(connection, dataSQL, new ScalarHandler<>());
        }

    }
}

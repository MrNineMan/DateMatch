package org.draft.dev;

import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class BirthCheckTest {

    @Container
    public static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0");
    private ResultSet rs;


    @BeforeEach
    public void setUp() {
        try {
            Connection conn = DriverManager.getConnection(sqlContainer.getJdbcUrl(), sqlContainer.getUsername(), sqlContainer.getPassword());
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            state.execute("CREATE TABLE Persons (\n" +  //You can return a
                    "    ID INT NOT NULL DEFAULT 0, \n" +
                    "    BirthDate varchar(255),\n" +
                    "    FirstName varchar(255),\n" +
                    "    LastName varchar(255), \n" +
                    "    PRIMARY KEY(ID));");

            state.execute("INSERT INTO Persons (ID, Birthdate, FirstName, LastName) \n" +
                    "values (1,'safdasf', 'John', 'Smith'), \n" +
                    "(2,'afdadfsf','Jane', 'Doe'), \n" +
                    "(3,'addfddads', 'James', 'Dereck'); \n");

             rs = state.executeQuery("SELECT * FROM Persons");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void removeRecordsTest() {
            BirthCheck to = new BirthCheck();
            assertThrows(SQLDataException.class, () -> to.removeRecords(rs));
    }
}
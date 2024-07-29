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
        //create the persons table in the database
        Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        state.execute("CREATE TABLE Persons (\n" +  //You can return a
                      "    ID INT NOT NULL DEFAULT 0, \n" +
                      "    BirthDate DATE,\n" +
                      "    FirstName varchar(255),\n" +
                      "    LastName varchar(255), \n" +
                      "    PRIMARY KEY(ID));");
		    //feed data into the table
        state.execute("INSERT INTO Persons (ID, Birthdate, FirstName, LastName) \n" +
                      "values (1,'1999-10-01', 'John', 'Smith'), \n" +
                      "(2,'1974-03-10','Jane', 'Doe'), \n" +
                      "(3,'1988-06-06', 'James', 'Dereck'); \n");
		    //populate the result set
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

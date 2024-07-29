package org.draft.dev;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BirthCheck {


    void removeRecords(ResultSet rs) throws SQLException {
        while (rs.next()) {
            java.sql.Date birthDate = rs.getDate(2);             // Retrieve the first column value (only)
            if (1990 > birthDate.getYear()) {
                rs.deleteRow();
            }

        }
    }

}



package org.draft.dev;

//Add the necessary SQL imports 
import java.sql.ResultSet;
import java.sql.SQLException;


public class BirthCheck {
	//Recieves a ResultSet   
    void removeRecords(ResultSet rs) {
        try {
            //Loops through the result set
            while (rs.next()) {
                // Retrieves the birtdate from the second column
                java.sql.Date birthDate = rs.getDate(2);              
                //Checks if the birthyear is less than 1990 
                if (1990 > birthDate.getYear()) {
                    //if the above condition is true, delete the record
                    rs.deleteRow();
                }

            }
		
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

package aplicacao;


import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Programa {

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Lavinia Venturim");
            st.setString(2,"lavinia.venturim@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("21/06/2008").getTime()));
            st.setDouble(4, 3000);
            st.setInt(5, 2);

            int linhaAltereda = st.executeUpdate();

            if (linhaAltereda > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Pronto! ID = " + id);
                }
            }
            else {
                System.out.println("Nenhuma Linha Alterada!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}

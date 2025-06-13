package aplicacao;


import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Programa {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?"
            );

            st.setDouble(1, 200);;

            int linhasAfetadas = st.executeUpdate();

            System.out.println("Pronto! Linhas Afetadas: " + linhasAfetadas);
        }
        catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }
}
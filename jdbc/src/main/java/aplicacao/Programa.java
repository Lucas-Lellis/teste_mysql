package aplicacao;


import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int linha1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("Fake error");
//            }

            int linha2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("linha 1 " + linha1);
            System.out.println("linha 2 " + linha2);

        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transação nao ocorreu! Por causa de: " + e.getMessage());
            }
            catch (SQLException e1) {
                throw new DbException("Error ao tentar retornar transação! Por causa de: " + e1.getMessage());
            }
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }
}
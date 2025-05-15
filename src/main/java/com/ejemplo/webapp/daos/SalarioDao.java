package com.ejemplo.webapp.daos;

import com.ejemplo.webapp.beans.Salario;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class SalarioDao {
    private static final String URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USER = "root";
    private static final String PASS = "root";

    public ArrayList<Salario> listarSalariosMaximosPorTitulo() {
        ArrayList<Salario> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT t.title, MAX(s.salary) as max_salary " +
                            "FROM salaries s " +
                            "JOIN titles t ON s.emp_no = t.emp_no " +
                            "GROUP BY t.title");

            while (rs.next()) {
                Salario salario = new Salario();
                salario.setTitle(rs.getString("title"));
                salario.setSalary(rs.getInt("max_salary"));
                lista.add(salario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
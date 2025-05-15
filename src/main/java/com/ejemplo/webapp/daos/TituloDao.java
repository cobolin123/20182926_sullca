package com.ejemplo.webapp.daos;

import com.ejemplo.webapp.beans.Titulo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class TituloDao {
    private static final String URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USER = "root";
    private static final String PASS = "root";

    public ArrayList<Titulo> listarTitulos() {
        ArrayList<Titulo> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT t.* FROM titles t " +
                            "INNER JOIN (SELECT emp_no, MAX(from_date) as max_date FROM titles GROUP BY emp_no) m " +
                            "ON t.emp_no = m.emp_no AND t.from_date = m.max_date LIMIT 100");

            while (rs.next()) {
                Titulo titulo = new Titulo();
                titulo.setEmpNo(rs.getInt("emp_no"));
                titulo.setTitle(rs.getString("title"));
                titulo.setFromDate(rs.getDate("from_date"));
                titulo.setToDate(rs.getDate("to_date"));
                lista.add(titulo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Titulo obtenerTitulo(int empNo) {
        Titulo titulo = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM titles WHERE emp_no = ? ORDER BY from_date DESC LIMIT 1");
            pstmt.setInt(1, empNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                titulo = new Titulo();
                titulo.setEmpNo(rs.getInt("emp_no"));
                titulo.setTitle(rs.getString("title"));
                titulo.setFromDate(rs.getDate("from_date"));
                titulo.setToDate(rs.getDate("to_date"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return titulo;
    }

    public void crearTitulo(Titulo titulo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO titles (emp_no, title, from_date, to_date) " +
                            "VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, titulo.getEmpNo());
            pstmt.setString(2, titulo.getTitle());
            pstmt.setDate(3, new java.sql.Date(titulo.getFromDate().getTime()));
            pstmt.setDate(4, new java.sql.Date(titulo.getToDate().getTime()));

            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTitulo(Titulo titulo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE titles SET title = ?, to_date = ? " +
                            "WHERE emp_no = ? AND from_date = ?");

            pstmt.setString(1, titulo.getTitle());
            pstmt.setDate(2, new java.sql.Date(titulo.getToDate().getTime()));
            pstmt.setInt(3, titulo.getEmpNo());
            pstmt.setDate(4, new java.sql.Date(titulo.getFromDate().getTime()));

            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarTitulo(int empNo, Date fromDate) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM titles WHERE emp_no = ? AND from_date = ?");

            pstmt.setInt(1, empNo);
            pstmt.setDate(2, new java.sql.Date(fromDate.getTime()));
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
package com.ejemplo.webapp.daos;

import com.ejemplo.webapp.beans.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class EmpleadoDao {
    private static final String URL = "jdbc:mysql://localhost:3306/employees";
    private static final String USER = "root";
    private static final String PASS = "root";

    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees LIMIT 100");

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
                lista.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Empleado> buscarEmpleados(String nombre) {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ? LIMIT 100");
            pstmt.setString(1, "%" + nombre + "%");
            pstmt.setString(2, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
                lista.add(emp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Empleado obtenerEmpleado(int empNo) {
        Empleado emp = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM employees WHERE emp_no = ?");
            pstmt.setInt(1, empNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                emp = new Empleado();
                emp.setEmpNo(rs.getInt("emp_no"));
                emp.setBirthDate(rs.getDate("birth_date"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setGender(rs.getString("gender"));
                emp.setHireDate(rs.getDate("hire_date"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public int obtenerUltimoId() {
        int ultimoId = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(emp_no) FROM employees");

            if (rs.next()) {
                ultimoId = rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return ultimoId;
    }

    public void crearEmpleado(Empleado emp) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, emp.getEmpNo());
            pstmt.setDate(2, new java.sql.Date(emp.getBirthDate().getTime()));
            pstmt.setString(3, emp.getFirstName());
            pstmt.setString(4, emp.getLastName());
            pstmt.setString(5, emp.getGender());
            pstmt.setDate(6, new java.sql.Date(emp.getHireDate().getTime()));

            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEmpleado(Empleado emp) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE employees SET birth_date = ?, first_name = ?, last_name = ?, " +
                            "gender = ?, hire_date = ? WHERE emp_no = ?");

            pstmt.setDate(1, new java.sql.Date(emp.getBirthDate().getTime()));
            pstmt.setString(2, emp.getFirstName());
            pstmt.setString(3, emp.getLastName());
            pstmt.setString(4, emp.getGender());
            pstmt.setDate(5, new java.sql.Date(emp.getHireDate().getTime()));
            pstmt.setInt(6, emp.getEmpNo());

            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarEmpleado(int empNo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM employees WHERE emp_no = ?");

            pstmt.setInt(1, empNo);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
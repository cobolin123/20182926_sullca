package com.ejemplo.webapp.servlets;

import com.ejemplo.webapp.beans.Empleado;
import com.ejemplo.webapp.daos.EmpleadoDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "EmpleadoServlet", value = "/EmpleadoServlet")
public class EmpleadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        EmpleadoDao empleadoDao = new EmpleadoDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                String nombre = request.getParameter("nombre");
                if (nombre != null && !nombre.isEmpty()) {
                    request.setAttribute("empleados", empleadoDao.buscarEmpleados(nombre));
                } else {
                    request.setAttribute("empleados", empleadoDao.listarEmpleados());
                }
                view = request.getRequestDispatcher("/empleados/lista.jsp");
                view.forward(request, response);
                break;

            case "formNuevo":
                request.setAttribute("ultimoId", empleadoDao.obtenerUltimoId() + 1);
                view = request.getRequestDispatcher("/empleados/nuevo.jsp");
                view.forward(request, response);
                break;

            case "editar":
                int empNo = Integer.parseInt(request.getParameter("id"));
                Empleado emp = empleadoDao.obtenerEmpleado(empNo);
                if (emp == null) {
                    response.sendRedirect(request.getContextPath() + "/EmpleadoServlet");
                } else {
                    request.setAttribute("empleado", emp);
                    view = request.getRequestDispatcher("/empleados/editar.jsp");
                    view.forward(request, response);
                }
                break;

            case "borrar":
                empNo = Integer.parseInt(request.getParameter("id"));
                empleadoDao.borrarEmpleado(empNo);
                response.sendRedirect(request.getContextPath() + "/EmpleadoServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        EmpleadoDao empleadoDao = new EmpleadoDao();

        switch (action) {
            case "crear":
                Empleado nuevoEmp = new Empleado();
                nuevoEmp.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
                nuevoEmp.setBirthDate(java.sql.Date.valueOf(request.getParameter("birthDate")));
                nuevoEmp.setFirstName(request.getParameter("firstName"));
                nuevoEmp.setLastName(request.getParameter("lastName"));
                nuevoEmp.setGender(request.getParameter("gender"));
                nuevoEmp.setHireDate(java.sql.Date.valueOf(request.getParameter("hireDate")));

                empleadoDao.crearEmpleado(nuevoEmp);
                response.sendRedirect(request.getContextPath() + "/EmpleadoServlet");
                break;

            case "actualizar":
                Empleado empActualizado = new Empleado();
                empActualizado.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
                empActualizado.setBirthDate(java.sql.Date.valueOf(request.getParameter("birthDate")));
                empActualizado.setFirstName(request.getParameter("firstName"));
                empActualizado.setLastName(request.getParameter("lastName"));
                empActualizado.setGender(request.getParameter("gender"));
                empActualizado.setHireDate(java.sql.Date.valueOf(request.getParameter("hireDate")));

                empleadoDao.actualizarEmpleado(empActualizado);
                response.sendRedirect(request.getContextPath() + "/EmpleadoServlet");
                break;
        }
    }
}
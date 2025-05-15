package com.ejemplo.webapp.servlets;

import com.ejemplo.webapp.daos.SalarioDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SalarioServlet", value = "/SalarioServlet")
public class SalarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SalarioDao salarioDao = new SalarioDao();
        request.setAttribute("salarios", salarioDao.listarSalariosMaximosPorTitulo());

        RequestDispatcher view = request.getRequestDispatcher("/salarios/lista.jsp");
        view.forward(request, response);
    }
}
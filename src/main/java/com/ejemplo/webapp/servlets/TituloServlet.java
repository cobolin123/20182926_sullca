package com.ejemplo.webapp.servlets;

import com.ejemplo.webapp.beans.Titulo;
import com.ejemplo.webapp.daos.TituloDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "TituloServlet", value = "/TituloServlet")
public class TituloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        TituloDao tituloDao = new TituloDao();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                request.setAttribute("titulos", tituloDao.listarTitulos());
                view = request.getRequestDispatcher("/titulos/lista.jsp");
                view.forward(request, response);
                break;

            case "formNuevo":
                view = request.getRequestDispatcher("/titulos/nuevo.jsp");
                view.forward(request, response);
                break;

            case "editar":
                int empNo = Integer.parseInt(request.getParameter("id"));
                Titulo titulo = tituloDao.obtenerTitulo(empNo);
                if (titulo == null) {
                    response.sendRedirect(request.getContextPath() + "/TituloServlet");
                } else {
                    request.setAttribute("titulo", titulo);
                    view = request.getRequestDispatcher("/titulos/editar.jsp");
                    view.forward(request, response);
                }
                break;

            case "borrar":
                empNo = Integer.parseInt(request.getParameter("id"));
                Date fromDate = java.sql.Date.valueOf(request.getParameter("fromDate"));
                tituloDao.borrarTitulo(empNo, fromDate);
                response.sendRedirect(request.getContextPath() + "/TituloServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        TituloDao tituloDao = new TituloDao();

        switch (action) {
            case "crear":
                Titulo nuevoTitulo = new Titulo();
                nuevoTitulo.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
                nuevoTitulo.setTitle(request.getParameter("title"));
                nuevoTitulo.setFromDate(java.sql.Date.valueOf(request.getParameter("fromDate")));
                nuevoTitulo.setToDate(java.sql.Date.valueOf(request.getParameter("toDate")));

                tituloDao.crearTitulo(nuevoTitulo);
                response.sendRedirect(request.getContextPath() + "/TituloServlet");
                break;

            case "actualizar":
                Titulo tituloActualizado = new Titulo();
                tituloActualizado.setEmpNo(Integer.parseInt(request.getParameter("empNo")));
                tituloActualizado.setTitle(request.getParameter("title"));
                tituloActualizado.setFromDate(java.sql.Date.valueOf(request.getParameter("fromDate")));
                tituloActualizado.setToDate(java.sql.Date.valueOf(request.getParameter("toDate")));

                tituloDao.actualizarTitulo(tituloActualizado);
                response.sendRedirect(request.getContextPath() + "/TituloServlet");
                break;
        }
    }
}
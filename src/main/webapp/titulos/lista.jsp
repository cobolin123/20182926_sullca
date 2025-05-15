<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ejemplo.webapp.beans.Titulo" %>
<jsp:useBean type="java.util.ArrayList<com.ejemplo.webapp.beans.Titulo>" scope="request" id="titulos" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Títulos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Administración Employees</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link ${activePage == 'empleados' ? 'active' : ''}"
             href="${pageContext.request.contextPath}/EmpleadoServlet">Empleados</a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${activePage == 'titulos' ? 'active' : ''}"
             href="${pageContext.request.contextPath}/TituloServlet">Títulos</a>
        </li>
        <li class="nav-item">
          <a class="nav-link ${activePage == 'salarios' ? 'active' : ''}"
             href="${pageContext.request.contextPath}/SalarioServlet">Salarios</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-4">
  <h1 class="mb-3">Títulos</h1>

  <a href="${pageContext.request.contextPath}/TituloServlet?action=formNuevo" class="btn btn-success mb-3">Crear Título</a>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Emp No</th>
      <th>Title</th>
      <th>From Date</th>
      <th>To Date</th>
      <th></th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < titulos.size(); i++) {
      Titulo titulo = titulos.get(i); %>
    <tr>
      <td><%= titulo.getEmpNo() %></td>
      <td><%= titulo.getTitle() %></td>
      <td><%= titulo.getFromDate() %></td>
      <td><%= titulo.getToDate() %></td>
      <td>
        <a href="${pageContext.request.contextPath}/TituloServlet?action=editar&id=<%= titulo.getEmpNo() %>" class="btn btn-sm btn-warning">Editar</a>
      </td>
      <td>
        <a href="${pageContext.request.contextPath}/TituloServlet?action=borrar&id=<%= titulo.getEmpNo() %>&fromDate=<%= titulo.getFromDate() %>" class="btn btn-sm btn-danger">Borrar</a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
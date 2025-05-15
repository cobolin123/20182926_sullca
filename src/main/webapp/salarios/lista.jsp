<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ejemplo.webapp.beans.Salario" %>
<jsp:useBean type="java.util.ArrayList<com.ejemplo.webapp.beans.Salario>" scope="request" id="salarios" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Salarios</title>
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
  <h1 class="mb-3">Salarios</h1>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Title</th>
      <th>Salary</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < salarios.size(); i++) {
      Salario salario = salarios.get(i); %>
    <tr>
      <td><%= salario.getTitle() %></td>
      <td><%= salario.getSalary() %></td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
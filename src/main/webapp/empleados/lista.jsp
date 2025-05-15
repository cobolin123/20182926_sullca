<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ejemplo.webapp.beans.Empleado" %>
<jsp:useBean type="java.util.ArrayList<com.ejemplo.webapp.beans.Empleado>" scope="request" id="empleados" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Empleados</title>
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
  <h1 class="mb-3">Empleados</h1>

  <div class="card mb-4">
    <div class="card-header">
      <h2 class="mb-0">Buscar empleado</h2>
    </div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/EmpleadoServlet" method="get">
        <div class="row g-3">
          <div class="col-md-8">
            <input type="text" class="form-control" name="nombre" placeholder="Nombre o apellido">
          </div>
          <div class="col-md-4">
            <button type="submit" class="btn btn-primary">Buscar</button>
            <a href="${pageContext.request.contextPath}/EmpleadoServlet" class="btn btn-secondary">Limpiar</a>
          </div>
        </div>
      </form>
    </div>
  </div>

  <a href="${pageContext.request.contextPath}/EmpleadoServlet?action=formNuevo" class="btn btn-success mb-3">Crear Empleado</a>

  <table class="table table-striped">
    <thead>
    <tr>
      <th>Emp No</th>
      <th>Birth Date</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Gender</th>
      <th>Hire Date</th>
      <th></th>
      <th></th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < empleados.size(); i++) {
      Empleado emp = empleados.get(i); %>
    <tr>
      <td><%= emp.getEmpNo() %></td>
      <td><%= emp.getBirthDate() %></td>
      <td><%= emp.getFirstName() %></td>
      <td><%= emp.getLastName() %></td>
      <td><%= emp.getGender() %></td>
      <td><%= emp.getHireDate() %></td>
      <td>
        <a href="${pageContext.request.contextPath}/EmpleadoServlet?action=editar&id=<%= emp.getEmpNo() %>" class="btn btn-sm btn-warning">Editar</a>
      </td>
      <td>
        <a href="${pageContext.request.contextPath}/EmpleadoServlet?action=borrar&id=<%= emp.getEmpNo() %>" class="btn btn-sm btn-danger">Borrar</a>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.webapp.beans.Titulo" %>
<jsp:useBean type="com.ejemplo.webapp.beans.Titulo" scope="request" id="titulo" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Título</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h1 class="mb-3">Editar título</h1>

  <form action="${pageContext.request.contextPath}/TituloServlet" method="post">
    <input type="hidden" name="action" value="actualizar">
    <input type="hidden" name="fromDate" value="<%= titulo.getFromDate() %>">

    <div class="mb-3">
      <label for="empNo" class="form-label">Emp No:</label>
      <input type="number" class="form-control" id="empNo" name="empNo" value="<%= titulo.getEmpNo() %>" readonly>
    </div>

    <div class="mb-3">
      <label for="title" class="form-label">Title:</label>
      <input type="text" class="form-control" id="title" name="title" value="<%= titulo.getTitle() %>" required>
    </div>

    <div class="mb-3">
      <label for="fromDate" class="form-label">From Date:</label>
      <input type="date" class="form-control" id="fromDate"
             value="<%= new java.sql.Date(titulo.getFromDate().getTime()) %>" readonly>
    </div>

    <div class="mb-3">
      <label for="toDate" class="form-label">To Date:</label>
      <input type="date" class="form-control" id="toDate" name="toDate"
             value="<%= new java.sql.Date(titulo.getToDate().getTime()) %>" required>
    </div>

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/TituloServlet" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
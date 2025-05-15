<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ejemplo.webapp.beans.Empleado" %>
<jsp:useBean type="com.ejemplo.webapp.beans.Empleado" scope="request" id="empleado" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Empleado</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h1 class="mb-3">Editar empleado</h1>

  <form action="${pageContext.request.contextPath}/EmpleadoServlet" method="post">
    <input type="hidden" name="action" value="actualizar">

    <input type="hidden" id="empNo" name="empNo" value="<%= empleado.getEmpNo() %>">

    <div class="mb-3">
      <label for="birthDate" class="form-label">Birth Date:</label>
      <input type="date" class="form-control" id="birthDate" name="birthDate"
             value="<%= new java.sql.Date(empleado.getBirthDate().getTime()) %>" required>
    </div>

    <div class="mb-3">
      <label for="firstName" class="form-label">First Name:</label>
      <input type="text" class="form-control" id="firstName" name="firstName" value="<%= empleado.getFirstName() %>" required>
    </div>

    <div class="mb-3">
      <label for="lastName" class="form-label">Last Name:</label>
      <input type="text" class="form-control" id="lastName" name="lastName" value="<%= empleado.getLastName() %>" required>
    </div>

    <div class="mb-3">
      <label for="gender" class="form-label">Gender:</label>
      <select class="form-select" id="gender" name="gender" required>
        <option value="M" <%= empleado.getGender().equals("M") ? "selected" : "" %>>Male</option>
        <option value="F" <%= empleado.getGender().equals("F") ? "selected" : "" %>>Female</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="hireDate" class="form-label">Hire Date:</label>
      <input type="date" class="form-control" id="hireDate" name="hireDate"
             value="<%= new java.sql.Date(empleado.getHireDate().getTime()) %>" required>
    </div>

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/EmpleadoServlet" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
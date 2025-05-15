<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Crear Título</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
  <h1 class="mb-3">Crear un nuevo título</h1>

  <form action="${pageContext.request.contextPath}/TituloServlet" method="post">
    <input type="hidden" name="action" value="crear">

    <div class="mb-3">
      <label for="empNo" class="form-label">Emp No:</label>
      <input type="number" class="form-control" id="empNo" name="empNo" required>
    </div>

    <div class="mb-3">
      <label for="title" class="form-label">Title:</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>

    <div class="mb-3">
      <label for="fromDate" class="form-label">From Date:</label>
      <input type="date" class="form-control" id="fromDate" name="fromDate" required>
    </div>

    <div class="mb-3">
      <label for="toDate" class="form-label">To Date:</label>
      <input type="date" class="form-control" id="toDate" name="toDate" required>
    </div>

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/TituloServlet" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
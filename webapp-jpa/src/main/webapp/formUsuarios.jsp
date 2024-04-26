<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="layout/header.jsp" />

<h3>${title}</h3>

<form action="${pageContext.request.contextPath}/usuarios/form"" method="post">
    <div class="row mb-2">
        <label for="username" class="col-form-label col-sm-2">Username</label>
        <div class="col-sm-4">
            <input type="text" name="username" id="username" value="${usuario.username}" class="form-control">
        </div>
        <c:if test="${errores != null && errores.containsKey('username')}">
            <div style="color:red;">${errores.username}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="password" class="col-form-label col-sm-2">Password</label>
        <div class="col-sm-4">
            <input type="password" name="password" id="password" value="" class="form-control">
        </div>
        <c:if test="${errores != null && not empty errores.password}">
            <div style="color:red;">${errores.password}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="password2" class="col-form-label col-sm-2">Confirma password</label>
        <div class="col-sm-4">
            <input type="password" name="password2" id="password2" value="" class="form-control">
        </div>
        <c:if test="${errores != null && not empty errores.password2}">
             <div style="color:red;">${errores.password2}</div>
        </c:if>
    </div>

    <div class="row mb-2">
        <label for="email" class="col-form-label col-sm-2">Correo electronico</label>
        <div class="col-sm-4">
            <input class="form-control" type="email" name="email" id="email" value="${usuario.email}" >
        </div>
        <c:if test="${errores != null && not empty errores.email}">
             <div style="color:red;">${errores.email}</div>
        </c:if>
    </div>

    <div class="row mb-2">
      <div>
         <input class="btn btn-primary" type="submit" value="${usuario.id!=null && usuario.id>0? "Editar": "Crear"}">
      </div>
    </div>
    <input type="hidden" name="id" value="${usuario.id}">
</form>

<jsp:include page="layout/footer.jsp" />
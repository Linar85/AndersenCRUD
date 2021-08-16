<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>AndersenHW</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand"> AndersenHW </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">People</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New Person
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                    </c:if>


                    <fieldset class="form-group">
                        <label>ID</label> <input type="text"
                                                   value="<c:out value='${user.id}' />" class="form-control"
                                                   name="id" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Name</label> <input type="text"
                                                   value="<c:out value='${user.name}' />" class="form-control"
                                                   name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Surname</label> <input type="text"
                                                      value="<c:out value='${user.surname}' />" class="form-control"
                                                      name="surname">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>age</label> <input type="text"
                                                  value="<c:out value='${user.age}' />" class="form-control"
                                                  name="age">
                    </fieldset>

                    <%--                    <fieldset class="form-group">--%>
                    <%--                        <label>User Country</label> <input type="text"--%>
                    <%--                                                           value="<c:out value='${user.country}' />" class="form-control"--%>
                    <%--                                                           name="country">--%>
                    <%--                    </fieldset>--%>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
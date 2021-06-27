
<h1><div style="text-align: center; color:#9152f8;" >CodeDNA</div> </h1>
<%@ include file="common/header.jspf" %>
<%@include file="common/navigation.jspf"%>
<h2>The list of Todo's for ${name} are:</h2>
<div class="container">
        <table border="2" class ="table table-striped" >
                <tshead>
                <tr>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is it Done</th>
                    <th> Update</th>
                    <th> Delete</th>
                </tr>
                </tshead>
            <tbody>

            <c:forEach items="${todos}" var="todos">
                <c:choose>

                    <c:when test="${todos.done == 'false'}" >
                        <tr class="info">
                            <td>${todos.desc}</td>
<%--                            <td><fmt:formatDate value="${todo.targetDate}" pattern="dd/mm yyyy"/></td>--%>
                            <td>${todos.targetDate}</td>
                            <td>${todos.done}</td>
                            <td> <a type="button" class="btn btn-success" href="/update-todo?id=${todos.id}">Update</a> </td>
                            <td> <a type="button" class="btn btn-warning" href="/delete-todo?id=${todos.id}">Delete</a> </td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <tr class="success">
                            <td>${todos.desc}</td>
                            <td>${todos.targetDate}</td>>
                            <td>${todos.done}</td>
                            <td> <a type="button" class="btn btn-success" href="/update-todo?id=${todos.id}">Update</a> </td>
                            <td> <a type="button" class="btn btn-warning" href="/delete-todo?id=${todos.id}">Delete</a> </td>
                        </tr>
                    </c:otherwise>

                </c:choose>
            </c:forEach>

            </tbody>
        </table>

    </div>
<br/>
<div> <a class="button" href="/add-todo">Add a Todo</a> </div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<%--<h2>Your password ${password}</h2>--%>
</div>
</body>
</html>

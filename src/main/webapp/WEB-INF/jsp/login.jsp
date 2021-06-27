<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
First Spring Application Login!!!
<br/>
<br/><br/>
<br/>
<br/><br/>

<div class="container">
<form method =POST>
    Name:  <input type ="text" name="name"/>
    <br/>
    <br/>
    Password: <input type ="password" name="password"/>
    <br/>
    <br/>
    <input type="submit"/>
</form>
</div>
<font color="red">${errorMessage}</font>
<%@include file="common/footer.jspf"%>
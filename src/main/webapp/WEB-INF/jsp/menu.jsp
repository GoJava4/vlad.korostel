<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="quote" type="com.morkva.entities.Quote"--%>
<c:set var="qt" value="${quote}"/>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<c:url value="/categories"/>">Home</a></li>
                <li><a href="#">${qt.value}</a></li>
                <li><a href="#">Link 3</a></li>
            </ul>
        </div>
    </div>
</div>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page import="ru.javawebinar.topjava.model.UserMeal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>

<html>
<dandelion:bundle includes="topjavaDatatable"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="meals.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add"><fmt:message key="meals.addButton"/></a>
                <datatables:table id="datatable" data="${mealList}" row="meal" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">
                    <fmt:message key='meals.tableColumnDateTime' var="tableColumnDateTime"/>
                    <datatables:column title="${tableColumnDateTime}">
                        <%=TimeUtil.toString(((UserMeal)meal).getDateTime())%>
                    </datatables:column>
                    <fmt:message key='meals.tableColumnDescription' var="tableColumnDescription"/>
                    <datatables:column title="${tableColumnDescription}" property="description"/>
                    <fmt:message key='meals.tableColumnCalories' var="tableColumnCalories"/>
                    <datatables:column title="${tableColumnCalories}" property="calories"/>
                    <datatables:column filterable="false" sortable="false">
                        <a class="btn btn-xs btn-danger delete" id="${meal.id}"><fmt:message key='meals.tableDelete'/></a>
                    </datatables:column>
                </datatables:table>
            </div>
        </div>
    </div>
</div>



<%--<section>--%>
    <%--<table border="1" cellpadding="8" cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Date</th>--%>
            <%--<th>Description</th>--%>
            <%--<th>Calories</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<c:forEach items="${mealList}" var="meal">--%>
            <%--<jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.UserMeal"/>--%>
            <%--<tr>--%>
                <%--<td><%=TimeUtil.toString(meal.getDateTime())%></td>--%>
                <%--<td>${meal.description}</td>--%>
                <%--<td>${meal.calories}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
<%--</section>--%>
<hr>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key='meals.modalTitle'/>:</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="item_id" name="item_id">
                    <div class="form-group">
                        <label for="datetime" class="control-label col-xs-3">${tableColumnDateTime}</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="datetime" name="datetime" placeholder="${tableColumnDateTime}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">${tableColumnDescription}</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="${tableColumnDescription}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">${tableColumnCalories}</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="calories" name="calories" placeholder="${tableColumnCalories}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary"><fmt:message key='meals.modalSubmit'/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var ajaxUrl='ajax/meals/';
    //        $(document).ready(function () {
    $(function () {
        makeEditable();
        $("#datetime").datetimepicker({ format: Date.patterns.SortableDateTimePattern });
    });
</script>
</html>
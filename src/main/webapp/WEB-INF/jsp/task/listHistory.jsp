<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/style.jsp"%>
<title>Example</title>

</head>
<body>
  <div id="wrap">
    <%@include file="/common/header.jsp"%>
    <!-- Begin page content -->
    <div class="container">
       <div class="page-header">
          <h2>任务列表</h2>
        </div>
        <table class="table">
          <tr>
            <th class="sorting" name="id">ID</th>
            <th class="sorting" name="startTime">开始时间</th>
            <th class="sorting" name="endTime">结束时间</th>
            <th width="150">&nbsp;</th>
          </tr>
          <c:forEach items="${historicProcessInstances }" var="item">
            <tr>
            <td>${item.id }</td>
            <td>${item.startTime }</td>
            <td>${item.endTime }</td>
          </tr>
          </c:forEach>
        </table>
      </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
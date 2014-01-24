<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/style.jsp"%>
<title>List process</title>

</head>
<body>
  <!-- Wrap all page content here -->
  <div id="wrap">
    <%@include file="/common/header.jsp"%>
    <!-- Begin page content -->
    <div class="container">

      <div class="page-header">
        <h1>ProcessDefinition</h1>
      </div>
      <table>
        <tr>
          <td>Id</td>
          <td>Name</td>
        </tr>
        <c:forEach items="${processDefinitions }" var="item">
          <tr>
          <td>${item.id }</td>
          <td>${item.name }</td>
        </tr>
        </c:forEach>
      </table>
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
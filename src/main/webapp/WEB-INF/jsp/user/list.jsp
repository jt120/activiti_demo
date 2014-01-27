<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
          <h2>用户列表</h2>
        </div>
        <table class="table">
          <tr>
            <th class="sorting" name="id">ID</th>
            <th class="sorting" name="first">名</th>
            <th class="sorting" name="last">姓</th>
            <th width="150">&nbsp;</th>
          </tr>
          <c:forEach items="${users }" var="item">
            <tr>
            <td>${item.id }</td>
            <td>${item.firstName }</td>
            <td>${item.lastName }</td>
            <td>
            <a href="${ctx }/user/${item.id}/view">查看</a>
            </td>
          </tr>
          </c:forEach>
        </table>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
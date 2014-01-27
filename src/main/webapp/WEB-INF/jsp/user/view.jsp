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
       <table class="table">
          <tr>
            <th class="sorting" name="id">ID</th>
            <th class="sorting" name="email">邮箱</th>
            <th class="sorting" name="type">姓名</th>
          </tr>
            <tr>
            <td>${user.id }</td>
            <td>${user.email }</td>
            <td>${user.lastName}&nbsp;${user.firstName }</td>
          </tr>
        </table>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
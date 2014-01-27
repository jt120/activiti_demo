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
      <table class="table">
        <tr>
          <th class="sorting" name="id">ID</th>
          <th class="sorting" name="name">名称</th>
          <th class="sorting" name="category">分类</th>
          <th class="sorting" name="description">部署时间</th>
          <th width="150">&nbsp;</th>
        </tr>
        <c:forEach items="${deployments }" var="item">
          <tr>
          <td>${item.id }</td>
          <td>${item.name }</td>
          <td>${item.category }</td>
          <td>${item.deploymentTime }</td>
          <td>
          <a href="${ctx }/process/${item.id}/prepare">提交</a>
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
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
        <h2>流程定义</h2>
      </div>
      <table class="table">
        <tr>
          <th class="sorting" name="id">ID</th>
          <th class="sorting" name="key">定义名</th>
          <th class="sorting" name="name">名称</th>
          <th class="sorting" name="category">分类</th>
          <th class="sorting" name="version">版本</th>
          <th class="sorting" name="description">描述</th>
          <th width="150">&nbsp;</th>
        </tr>
        <c:forEach items="${processDefinitions }" var="item">
          <tr>
          <td>${item.id }</td>
          <td>${item.key }</td>
          <td>${item.name }</td>
          <td>${item.category }</td>
          <td>${item.version }</td>
          <td>${item.description }</td>
          <td>
          <a href="${ctx }/process/${item.id}/image" target="_blank">流程图</a>
          <a href="${ctx }/process/${item.id}/prepare">提交</a>
          <a href="${ctx }/process/${item.id}/delete">删除</a>
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
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
            <th class="sorting" name="name">名称</th>
            <th class="sorting" name="priority">优先级</th>
            <th class="sorting" name="owner">拥有者</th>
            <th class="sorting" name="assignee">授权人</th>
            <th class="sorting" name="createTime">创建时间</th>
            <th width="150">&nbsp;</th>
          </tr>
          <c:forEach items="${tasks }" var="item">
            <tr>
            <td>${item.id }</td>
            <td>${item.name }</td>
            <td>${item.priority }</td>
            <td>${item.owner }</td>
            <td>${item.assignee }</td>
            <td><fmt:formatDate value="${item.createTime }" pattern="yyyy/MM/dd HH:mm"/></td>
            <td>
            <a href="${ctx }/task/${item.id}/complete">完成</a>
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
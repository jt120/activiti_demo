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
          <h2>历史详细记录</h2>
        </div>
        <h2>流程图</h2>
          <img src="${ctx }/process/${id}/instanceImage">
        <h2>列表</h2>
        <table class="table">
          <tr>
            <th class="sorting" name="id">ID</th>
            <th class="sorting" name="name">名称</th>
            <th class="sorting" name="assignee">责任人</th>
            <th class="sorting" name="startTime">开始时间</th>
            <th class="sorting" name="endTime">结束时间</th>
            <th class="sorting" name="DurationInMillis">耗时(小时)</th>
            <th class="sorting" name="deleteReason">结果</th>
          </tr>
          <c:forEach items="${historicTaskInstances }" var="item">
            <tr>
            <td>${item.id }</td>
            <td>${item.name }</td>
            <td>${item.assignee }</td>
            <td><fmt:formatDate value="${item.startTime }" pattern="yyyy/MM/dd HH:mm"/></td>
            <td><fmt:formatDate value="${item.endTime }" pattern="yyyy/MM/dd HH:mm"/></td>
            <td><fmt:formatNumber value="${item.durationInMillis / 3600000}" pattern="0.0"/></td>
            <td>${item.deleteReason }</td>
          </tr>
          </c:forEach>
        </table>
        <h2>历史相关表单</h2>
          <table class="table">
            <tr>
              <th class="sorting" name="variableName">名称</th>
              <th class="sorting" name="value">值</th>
            </tr>
            <c:forEach items="${historicVariableInstances }" var="item">
              <tr>
               <td>${item.variableName}</td>
	           <td>${item.value}</td>
            </tr>
            </c:forEach>
          </table>
      </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
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
    <h2>请输入流程表单相关数据</h2>
      <form id="demoForm" method="post" action="${ctx }/process/${id}/start"  role="form">
        <c:forEach items="${startFormData.formProperties }" var="item">
          <div class="control-group">
          <label class="control-label">${item.name}</label>
          <div class="controls">
          <c:if test="${type.name eq 'enum' }">
            <c:forEach items="${type.getInformation.values }" var="item">
            
              <label class="checkbox inline">
              <input type="radio" name="${item.id}" value="${key}" ${item.value == key ? 'checked' : ''}>
            ${value}
            </label>
            </c:forEach>
            </select>
            </c:if>
            <c:if test="${type.name ne 'enum' }">
            <input type="text" name="${item.id}" value="${item.value}" size="40" class="form-control ${item.required ? 'required' : ''}" ${item.writable ? '' : 'readonly'}>
            </c:if>
            </div>
          </div>
        </c:forEach>
        <div class="control-group">
          <div class="controls">
            <button id="submitButton" type="submit"  class="btn btn-default">保存</button>
      	  &nbsp;
            <button type="button" onclick="history.back();"  class="btn btn-default">返回</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
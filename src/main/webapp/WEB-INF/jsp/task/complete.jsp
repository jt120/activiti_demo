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
    <h2>请输入任务相关数据</h2>
      <form id="demoForm" method="post" action="${ctx }/task/${id}/complete"  role="form" class="form-horizontal">
        <c:forEach items="${taskFormData.formProperties }" var="item">
        <div class="form-group">
          <label for="${item.id}" class="col-sm-2 control-label">${item.name}</label>
          <div class="col-sm-10">
             <c:if test="${item.type.name eq 'enum' }">
                <c:forEach items="${item.type.getInformation('values') }" var="enumItem">
                  <label class="radio-inline">
                  <input type="radio" name="${item.id}" value="${enumItem.key}" ${item.value == enumItem.key ? 'checked' : ''}>
                  ${enumItem.value}
                </label>
                </c:forEach>
              </c:if>
              <c:if test="${item.type.name ne 'enum' }">
                <input type="text" name="${item.id}" value="${item.value}" size="40" class="form-control ${item.required ? 'required' : ''}" ${item.writable ? '' : 'readonly'}>
              </c:if>
          </div>
        </div>
        </c:forEach>
      
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">保存</button>
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
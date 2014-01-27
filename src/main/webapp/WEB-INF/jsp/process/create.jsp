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
      <form id="demoForm" method="post" action="${ctx }/process/create" class="form">
          <div class="form-group">
          <textarea name="xml" rows="20" style="width:80%"></textarea>
          </div>
           <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn btn-default">创建</button>
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
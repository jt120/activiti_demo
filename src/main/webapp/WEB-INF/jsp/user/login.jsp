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
        <form class="form-signin" action="${ctx }/user/login" method="post">
          <h2 class="form-signin-heading">请登录</h2>
          <input type="text" name="id" class="form-control" placeholder="Id" autofocus>
          <input type="password" name="password" class="form-control" placeholder="Password">
          <label class="checkbox">
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        </form>
      
    </div>
  </div>
  <%@include file="/common/footer.jsp"%>
  <%@include file="/common/js.jsp"%>
</body>
</html>
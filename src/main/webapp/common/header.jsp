<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Fixed navbar -->
      <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${hello }</a>
          </div>
          <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            
              <li class="active"><a href="${ctx }/process/list">主页</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">流程<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${ctx }/process/list">流程列表</a></li>
                  <li><a href="${ctx }/process/listDeploy">已部署实例</a></li>
                  <li><a href="${ctx }/process/create">部署实例</a></li>
                </ul>
              </li>
             <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">任务<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${ctx }/task/list">当前任务</a></li>
                  <li><a href="${ctx }/task/list/completed">发起的任务</a></li>
                  <li><a href="${ctx }/task/list/invovlved">参与的任务</a></li>
                </ul>
              </li>
              <li>
                <a href="${ctx }/history/list">历史</a>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">用户<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${ctx }/user/${loginUser.id }/view">用户信息</a></li>
                  <li><a href="${ctx }/user/list">用户列表</a></li>
                </ul>
              </li>
              <li>
                <a href="${ctx }/group/list">组</a>
              </li>
              <li>
                <a href="${ctx }/history/list">统计</a>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">实例 <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="${ctx }/process/listDeploy">已部署实例</a></li>
                  <li><a href="${ctx }/task/list">当前任务</a></li>
                  <li><a href="${ctx }/history/list">历史任务</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
             <ul class="nav navbar-nav navbar-right">
                <c:if test="${loginUser ne null}"><li><a href="./">${loginUser.id}</a></li></c:if>
                <c:if test="${loginUser eq null}"><li><a href="${ctx }/user/login">登录</a></li></c:if>
              </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
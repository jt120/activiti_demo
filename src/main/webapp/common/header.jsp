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
                  <li><a href="${ctx }/user/${loginUser}/view">用户信息</a></li>
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
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">登陆 <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="${ctx }/login?username=admin">as admin</a></li>
                      <li><a href="${ctx }/login?username=hr">as hr</a></li>
                      <li><a href="${ctx }/login?username=manager">as manager</a></li>
                    </ul>
                  </li>
                <li><a href="./">${loginUser}</a></li>
              </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
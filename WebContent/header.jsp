<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="divOuterFrame">
	<div class="divInnerFrame">欢迎使用SurveyDoor调查系统!</div>
</div>
<div class="divWhiteLine"></div>
<div class="divNavigatorOuterFrame">
	<div class="divNavigatorInnerFrame">
		<s:a action="LoginAction_toLoginPage" namespace="/">[首页]</s:a>&nbsp;
		<s:a action="SurveyAction_newSurvey" namespace="/">[新建调查]</s:a>&nbsp;
		<s:a action="SurveyAction_mySurveys" namespace="/">[我的调查]</s:a>&nbsp;
		<s:a action="EngageSurveyAction_findAllAvailableSurveys" namespace="/">[参与调查]</s:a>&nbsp;
		<s:a action="RegAction_toRegPage" namespace="/">[用户注册]&nbsp;</s:a>
		[用户授权管理]&nbsp;
		<s:a action="RoleAction_allRolesList" namespace="/">[角色管理]&nbsp;</s:a>
		<s:a action="RightAction_allRightsList" namespace="/">[权限管理]&nbsp;</s:a>
		[日志管理]&nbsp;
	</div>
</div>
<div class="divWhiteLine"></div>
package cn.itcast.surveypark.struts2.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.itcast.surveypark.domain.User;
import cn.itcast.surveypark.struts2.action.BaseAction;
import cn.itcast.surveypark.struts2.action.LoginAction;
import cn.itcast.surveypark.struts2.action.RegAction;
import cn.itcast.surveypark.struts2.action.UserAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * LoginInterceptor
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -5671658363347813780L;

	public void destroy() {
	}

	public void init() {
	}

	@SuppressWarnings("rawtypes")
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction action = (BaseAction) invocation.getAction();
		if(action instanceof LoginAction
				|| action instanceof RegAction){
			return invocation.invoke();
		}
		//登录判断
		else{
			HttpSession s = ServletActionContext.getRequest().getSession();
			User user = (User) s.getAttribute("user");
			if(user == null){
				return "login";
			}
			else{
				//处理action的user注入问题
				if(action instanceof UserAware){
					((UserAware)action).setUser(user);
				}
				return invocation.invoke();
			}
		}
	}

}

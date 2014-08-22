package cn.itcast.surveypark.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.domain.User;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

}

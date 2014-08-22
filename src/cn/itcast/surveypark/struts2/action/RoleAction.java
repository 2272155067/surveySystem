package cn.itcast.surveypark.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.domain.security.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

}

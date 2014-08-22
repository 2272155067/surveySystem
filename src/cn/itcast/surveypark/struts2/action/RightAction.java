package cn.itcast.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.service.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right>{
	private static final long serialVersionUID = 5870139400020247492L;

	//注入service
	@Resource
	private RightService rightService;

	private List allRights;
	private Integer rightId;
	
	/**
	 * 查询所有权限列表
	 */
	public String allRightsList(){
		this.allRights = rightService.findAllRights();
		return "rightsListPage";
	}
	
	//添加权限页面
	public String toAddRightPage(){
		
		return "toAddRightPage";
	}
	
	//保存/更新权限
	public String saveOrupdateRight(){
		this.rightService.saveOrupdateRight(model);
		return "findAllRights";
	}
	
	//修改权限
	public String editRight(){
		return "editRightPage";
	}
	
	//删除权限
	public String deleteRight(){
		
		return "findAllRights";//返回权限列表
	}
	
	
	
	public List getAllRights() {
		return allRights;
	}
	public void setAllRights(List allRights) {
		this.allRights = allRights;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	
	
	
	
}

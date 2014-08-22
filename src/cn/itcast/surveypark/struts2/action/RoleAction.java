package cn.itcast.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.domain.security.Role;
import cn.itcast.surveypark.service.RightService;
import cn.itcast.surveypark.service.RoleService;
import cn.itcast.surveypark.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	@Resource
	private RoleService roleService;
	@Resource
	private RightService rightService;
	
	private Integer roleId;
	private List<Role> allRoles;
	private Integer[] ownRightIds;//分配给的权限列表id
	private List<Right> noOwnRights;//查询还没有的权限对象列表
	
	/**
	 * 查询所有权限列表
	 */
	public String allRolesList(){
		this.allRoles = this.roleService.findAllRoles(roleId);
		return "rolesListPage";
	}
	
	//添加权限页面,查询出所有的已拥有权限列表
	public String toAddRolePage(){
		this.noOwnRights = this.rightService.findAllRights();
		return "toAddRolePage";
	}

	/**
	 * 保存/更新权限saveOrUpdateRight
	 * 处理授予的权限： ownRightIds
	 */
	public String saveOrUpdateRole(){
		
		if (ValidateUtil.isValid(ownRightIds)) {
			for (Integer id : ownRightIds) {
				Right r = rightService.getEntity(id);
				model.getRights().add(r);
			}
		}
		this.roleService.saveOrUpdateEntity(model);
		return "findAllRoles";
	}
	
	//编辑权限 
	public String editRole(){
		this.model = roleService.getEntity(roleId);

		//根据已有权限，查询还未被赋予的权限
		this.noOwnRights = rightService.findnoOwnRights(model.getRights());
		
		return "editRolePage";
	} 
	
	//删除权限
	public String deleteRole(){
		Role t = this.roleService.getEntity(roleId);
		roleService.deleteEntity(t);
		return "findAllRoles";//返回权限列表
	}
	
	//批量更新权限
	public String batchUpdateRoles(){

		return "findAllRoles";
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public Integer[] getOwnRightIds() {
		return ownRightIds;
	}

	public void setOwnRightIds(Integer[] ownRightIds) {
		this.ownRightIds = ownRightIds;
	}

	public List<Right> getNoOwnRights() {
		return noOwnRights;
	}

	public void setNoOwnRights(List<Right> noOwnRights) {
		this.noOwnRights = noOwnRights;
	}

	
	
}

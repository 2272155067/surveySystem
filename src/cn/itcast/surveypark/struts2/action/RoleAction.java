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
	private Integer[] ownRightIds;//�������Ȩ���б�id
	private List<Right> noOwnRights;//��ѯ��û�е�Ȩ�޶����б�
	
	/**
	 * ��ѯ����Ȩ���б�
	 */
	public String allRolesList(){
		this.allRoles = this.roleService.findAllRoles(roleId);
		return "rolesListPage";
	}
	
	//���Ȩ��ҳ��,��ѯ�����е���ӵ��Ȩ���б�
	public String toAddRolePage(){
		this.noOwnRights = this.rightService.findAllRights();
		return "toAddRolePage";
	}

	/**
	 * ����/����Ȩ��saveOrUpdateRight
	 * ���������Ȩ�ޣ� ownRightIds
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
	
	//�༭Ȩ�� 
	public String editRole(){
		this.model = roleService.getEntity(roleId);

		//��������Ȩ�ޣ���ѯ��δ�������Ȩ��
		this.noOwnRights = rightService.findnoOwnRights(model.getRights());
		
		return "editRolePage";
	} 
	
	//ɾ��Ȩ��
	public String deleteRole(){
		Role t = this.roleService.getEntity(roleId);
		roleService.deleteEntity(t);
		return "findAllRoles";//����Ȩ���б�
	}
	
	//��������Ȩ��
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

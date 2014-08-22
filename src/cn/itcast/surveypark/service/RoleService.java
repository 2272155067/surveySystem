package cn.itcast.surveypark.service;

import java.util.List;
import java.util.Set;

import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.domain.security.Role;

/**
 *角色，（职务管理） 
 */
public interface RoleService extends BaseService<Role> {

	//查询所有角色列表
	public List<Role> findAllRoles(Integer roleId);
	//处理被授予的权限
	public void saveOrUpdateEntityRole(Role model, Integer[] ownRightIds);
	public void delete(Integer roleId);
	//查询未被赋予的权限

}

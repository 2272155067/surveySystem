package cn.itcast.surveypark.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.surveypark.dao.BaseDao;
import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.domain.security.Role;
import cn.itcast.surveypark.service.RightService;
import cn.itcast.surveypark.service.RoleService;
import cn.itcast.surveypark.util.StringUtil;
import cn.itcast.surveypark.util.ValidateUtil;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Resource(name = "roleDao")
	public void setDao(BaseDao<Role> dao) {
		super.setDao(dao);
	}

	@Resource
	private RightService rightService;

	// 查询所有角色
	public List<Role> findAllRoles(Integer roleId) {
		String hql = "from Role";

		return this.findEntityByHQL(hql);
	}

	/**
	 * 处理被授予的权限
	 */
	public void saveOrUpdateEntityRole(Role model, Integer[] ownRightIds) {
		// 根据ownRightIds查询出权限对象列表
		String hql = "from Right r where r.id = ?";
		if (!ValidateUtil.isValid(ownRightIds)) {
			for (Integer id : ownRightIds) {
				Right r = rightService.getEntity(id);
				model.getRights().add(r);
			}
		}
		this.saveOrUpdateEntity(model);
	}

	public void delete(Integer roleId) {
		
	}




}

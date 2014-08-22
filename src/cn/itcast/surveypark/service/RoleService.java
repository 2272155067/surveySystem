package cn.itcast.surveypark.service;

import java.util.List;
import java.util.Set;

import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.domain.security.Role;

/**
 *��ɫ����ְ����� 
 */
public interface RoleService extends BaseService<Role> {

	//��ѯ���н�ɫ�б�
	public List<Role> findAllRoles(Integer roleId);
	//���������Ȩ��
	public void saveOrUpdateEntityRole(Role model, Integer[] ownRightIds);
	public void delete(Integer roleId);
	//��ѯδ�������Ȩ��

}

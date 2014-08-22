package cn.itcast.surveypark.service;

import java.util.List;
import java.util.Set;

import cn.itcast.surveypark.domain.security.Right;

public interface RightService extends BaseService<Right> {
	
	//查询所有权限列表
	public List findAllRights();

	//保存，更新权限列表
	public void saveOrupdateRight(Right model);

	//更新实体
	public void updateEntity(Integer rightId);

	//删除实体
	public void deleteEntity(Integer rightId);

	//根据拦截器获取URL 添加权限，保证所有的权限都能被包含进去
	public void addRightByUrl(String url);

	public List<Right> findnoOwnRights(Set<Right> rights);

}

package cn.itcast.surveypark.service;

import java.util.List;

import cn.itcast.surveypark.domain.security.Right;

public interface RightService {
	
	//查询所有权限列表
	public List findAllRights();

	//保存，更新权限列表
	public void saveOrupdateRight(Right model);

}

package cn.itcast.surveypark.service;

import java.util.List;

import cn.itcast.surveypark.domain.security.Right;

public interface RightService {
	
	//��ѯ����Ȩ���б�
	public List findAllRights();

	//���棬����Ȩ���б�
	public void saveOrupdateRight(Right model);

}

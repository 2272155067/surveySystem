package cn.itcast.surveypark.service;

import java.util.List;
import java.util.Set;

import cn.itcast.surveypark.domain.security.Right;

public interface RightService extends BaseService<Right> {
	
	//��ѯ����Ȩ���б�
	public List findAllRights();

	//���棬����Ȩ���б�
	public void saveOrupdateRight(Right model);

	//����ʵ��
	public void updateEntity(Integer rightId);

	//ɾ��ʵ��
	public void deleteEntity(Integer rightId);

	//������������ȡURL ���Ȩ�ޣ���֤���е�Ȩ�޶��ܱ�������ȥ
	public void addRightByUrl(String url);

	public List<Right> findnoOwnRights(Set<Right> rights);

}

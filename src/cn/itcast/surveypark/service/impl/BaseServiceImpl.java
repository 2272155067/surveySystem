package cn.itcast.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.surveypark.dao.BaseDao;
import cn.itcast.surveypark.service.BaseService;

/**
 * �����serviceʵ��,ר�����ڼ̳�
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> dao ;
	
	//ע��dao
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}
	
	//��ֵ����(��ѯ������ҽ���һ����¼)
	public Object uniqueResult(String hql,Object...objects){
		return dao.uniqueResult(hql, objects);
	}

}

package cn.itcast.surveypark.service;

import java.util.List;

public interface BaseService<T> {
	//写操作
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	//按照hql批处理实体
	public void batchEntityByHQL(String hql,Object...objects);
	
	public T getEntity(Integer id);
	public T loadEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	//单值检索(查询结果有且仅有一条记录)
	public Object uniqueResult(String hql,Object...objects);
}

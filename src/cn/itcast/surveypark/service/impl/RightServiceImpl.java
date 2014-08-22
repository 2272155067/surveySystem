package cn.itcast.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.surveypark.dao.BaseDao;
import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.service.RightService;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	//��дע��dao��������rightDaoע��
	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}
	
	//��ѯ����Ȩ��
	public List findAllRights() {
		String hql = "from Right ";
		return this.findEntityByHQL(hql);
	}

	//����/����Ȩ��
	public void saveOrupdateRight(Right model) {
		if(model.getId() == null){
			int rightPos = 0 ;
			long rightCode = 1 ;
			//��ѯ���Ȩ��λ�ϵ����Ȩ����
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r " +
					"where r.rightPos = (select max(rr.rightPos) from Right rr)" ;
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topRightPos = (Integer) arr[0] ;
			Long topRightCode = (Long) arr[1];
			if(topRightPos == null){
				rightPos = 0 ;
				rightCode = 1 ;
			}
			else{
				if(topRightCode >= (1L << 60)){
					rightPos = topRightPos + 1;
					rightCode = 1 ;
				}
				else{
					rightPos = topRightPos ;
					rightCode = topRightCode << 1 ;
				}
			}
			model.setRightPos(rightPos);
			model.setRightCode(rightCode);
		}
		this.saveOrUpdateEntity(model);
	}

}

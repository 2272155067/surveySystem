package cn.itcast.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.surveypark.dao.BaseDao;
import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.service.RightService;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements RightService {

	//复写注入dao方法，将rightDao注入
	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}
	
	//查询所有权限
	public List findAllRights() {
		String hql = "from Right ";
		return this.findEntityByHQL(hql);
	}

	//保存/更新权限
	public void saveOrupdateRight(Right model) {
		if(model.getId() == null){
			int rightPos = 0 ;
			long rightCode = 1 ;
			//查询最大权限位上的最大权限码
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

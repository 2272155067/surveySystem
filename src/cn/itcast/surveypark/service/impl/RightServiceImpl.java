package cn.itcast.surveypark.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.surveypark.dao.BaseDao;
import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.service.RightService;
import cn.itcast.surveypark.util.ValidateUtil;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {

	// 复写注入dao方法，将rightDao注入
	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	// 查询所有权限
	public List findAllRights() {
		String hql = "from Right ";
		return this.findEntityByHQL(hql);
	}

	// 保存/更新权限
	public void saveOrupdateRight(Right model) {
		// id为空是保存操作
		if (model.getId() == null) {
			int rightPos = 0;
			long rightCode = 1;
			// 查询最大权限位上的最大权限码
			String hql = "select max(r.rightPos),max(r.rightCode) from Right r "
					+ "where r.rightPos = (select max(rr.rightPos) from Right rr)";
			Object[] arr = (Object[]) this.uniqueResult(hql);
			Integer topRightPos = (Integer) arr[0];
			Long topRightCode = (Long) arr[1];
			if (topRightPos == null) {
				rightPos = 0;
				rightCode = 1;
			} else {
				if (topRightCode >= (1L << 60)) {
					rightPos = topRightPos + 1;
					rightCode = 1;
				} else {
					rightPos = topRightPos;
					rightCode = topRightCode << 1;
				}
			}

			model.setRightPos(rightPos);
			model.setRightCode(rightCode);
		}
		this.saveOrUpdateEntity(model);
	}

	// 更新实体
	public void updateEntity(Integer rightId) {
		if (rightId != null) {
			this.updateEntity(getEntity(rightId));
		}
	}

	// 删除实体
	public void deleteEntity(Integer rightId) {

		if (rightId != null) {

			this.deleteEntity(getEntity(rightId));
		}
	}

	public void addRightByUrl(String url) {
		// 查询出该URL是否已被插入
		String hql = "select count(*) from Right r where r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		//第一次时插入数据库，其他情况下不做修改
		if (count == 0) {
			// 添加权限到数据库中
			Right r = new Right();
			r.setRightUrl(url);
			saveOrupdateRight(r);
		}
	}

	/**
	 * 根据已有权限查找还未被赋予的权限
	 */
	public List<Right> findnoOwnRights(Set<Right> rights) {
		//如果不为空
		String arr = "";
		if(ValidateUtil.isValid(rights)){
			for(Right r: rights){
				Integer id = r.getId();
				arr += id+",";
			}
			arr = arr.substring(0,arr.lastIndexOf(","));
			String hql = "from Right r where r.id not in ("+arr+")";
			return this.findEntityByHQL(hql);
			
		}else{
			String hql2 = "from Right";
			return this.findEntityByHQL(hql2);
		}
	}

}

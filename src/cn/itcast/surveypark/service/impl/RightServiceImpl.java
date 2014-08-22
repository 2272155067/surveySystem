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

	// ��дע��dao��������rightDaoע��
	@Resource(name = "rightDao")
	public void setDao(BaseDao<Right> dao) {
		super.setDao(dao);
	}

	// ��ѯ����Ȩ��
	public List findAllRights() {
		String hql = "from Right ";
		return this.findEntityByHQL(hql);
	}

	// ����/����Ȩ��
	public void saveOrupdateRight(Right model) {
		// idΪ���Ǳ������
		if (model.getId() == null) {
			int rightPos = 0;
			long rightCode = 1;
			// ��ѯ���Ȩ��λ�ϵ����Ȩ����
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

	// ����ʵ��
	public void updateEntity(Integer rightId) {
		if (rightId != null) {
			this.updateEntity(getEntity(rightId));
		}
	}

	// ɾ��ʵ��
	public void deleteEntity(Integer rightId) {

		if (rightId != null) {

			this.deleteEntity(getEntity(rightId));
		}
	}

	public void addRightByUrl(String url) {
		// ��ѯ����URL�Ƿ��ѱ�����
		String hql = "select count(*) from Right r where r.rightUrl = ?";
		Long count = (Long) this.uniqueResult(hql, url);
		//��һ��ʱ�������ݿ⣬��������²����޸�
		if (count == 0) {
			// ���Ȩ�޵����ݿ���
			Right r = new Right();
			r.setRightUrl(url);
			saveOrupdateRight(r);
		}
	}

	/**
	 * ��������Ȩ�޲��һ�δ�������Ȩ��
	 */
	public List<Right> findnoOwnRights(Set<Right> rights) {
		//�����Ϊ��
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

package cn.itcast.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.surveypark.domain.security.Right;
import cn.itcast.surveypark.service.RightService;

@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right>{
	private static final long serialVersionUID = 5870139400020247492L;

	//ע��service
	@Resource
	private RightService rightService;

	private List<Right> allRights;
	private Integer rightId; //Ȩ��id
	
	/**
	 * ��ѯ����Ȩ���б�
	 */
	public String allRightsList(){
		this.allRights = rightService.findAllRights();
		return "rightsListPage";
	}
	
	//���Ȩ��ҳ��
	public String toAddRightPage(){
		
		return "toAddRightPage";
	}
	
	//����/����Ȩ��saveOrUpdateRight
	public String saveOrUpdateRight(){
		this.rightService.saveOrupdateRight(model);
		return "findAllRights";
	}
	
	//�༭Ȩ��
	public String editRight(){
		this.model = this.rightService.getEntity(rightId);
		return "editRightPage";
	} 
	
	//ɾ��Ȩ��
	public String deleteRight(){
		this.rightService.deleteEntity(rightId); 
		return "findAllRights";//����Ȩ���б�
	}
	
	//��������Ȩ��
	public String batchUpdateRights(){
		String hql = "update Right r set r.rightName = ? where r.id = ?";
		for(Right r : allRights){
			this.rightService.batchEntityByHQL(hql,r.getRightName(), r.getId());
		}
		return "findAllRights";
	}
	
	
	public List<Right> getAllRights() {
		return allRights;
	}
	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	
	
	
	
}

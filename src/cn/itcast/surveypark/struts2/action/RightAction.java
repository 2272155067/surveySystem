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

	private List allRights;
	private Integer rightId;
	
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
	
	//����/����Ȩ��
	public String saveOrupdateRight(){
		this.rightService.saveOrupdateRight(model);
		return "findAllRights";
	}
	
	//�޸�Ȩ��
	public String editRight(){
		return "editRightPage";
	}
	
	//ɾ��Ȩ��
	public String deleteRight(){
		
		return "findAllRights";//����Ȩ���б�
	}
	
	
	
	public List getAllRights() {
		return allRights;
	}
	public void setAllRights(List allRights) {
		this.allRights = allRights;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	
	
	
	
}

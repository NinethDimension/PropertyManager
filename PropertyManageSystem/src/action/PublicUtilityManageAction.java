package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import Model.PublicUtilityManage;
import service.IPublicUtilityManageService;

import javax.annotation.Resource;

@Action (value="publicUtilityManageAction",results={
		@Result(name="list",type="redirectAction",location="publicUtilityManageAssign!list"),
		@Result(name="suc",location="/index.jsp")
		})
		
@Controller
public class PublicUtilityManageAction {

	
	@Resource(name = "publicUtilityManageService")
	private IPublicUtilityManageService publicUtilityManageService;
	
	
	private PublicUtilityManage publicUtilityManage;
	
	private String publicUtilityManageId;
	
	
	public PublicUtilityManage getPublicUtilityManage() {
		return publicUtilityManage;
	}
	public void setPublicUtilityManage(PublicUtilityManage publicUtilityManage) {
		this.publicUtilityManage = publicUtilityManage;
	}
	public String getPublicUtilityManageId() {
		return publicUtilityManageId;
	}
	public void setPublicUtilityManageId(String publicUtilityManageId) {
		this.publicUtilityManageId = publicUtilityManageId;
	}
	
	
	
	public String add(){
		publicUtilityManageService.addoredit(publicUtilityManage);
		return "list";
	}
	public String edit(){
		publicUtilityManageService.addoredit(publicUtilityManage);
		return "list";
	}
	public String delete(){
		publicUtilityManageService.deletebyid(Integer.parseInt(publicUtilityManageId));
		return "list";
	}
}

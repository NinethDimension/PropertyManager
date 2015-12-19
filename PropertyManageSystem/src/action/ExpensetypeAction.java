package action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import Model.Expensetype;
import service.IExpensetypeService;

import javax.annotation.Resource;

@Action (value="expensetypeAction",results={
		@Result(name="list",type="redirectAction",location="expensetypeAssign!list"),
		@Result(name="suc",location="/index.jsp")
		})
		
@Controller
public class ExpensetypeAction extends ActionSupport {

	
	@Resource(name = "expensetypeService")
	private IExpensetypeService expensetypeService;
	
	
	private Expensetype expensetype;
	
	private String expensetypeId;
	
	
	public Expensetype getExpensetype() {
		return expensetype;
	}
	public void setExpensetype(Expensetype expensetype) {
		this.expensetype = expensetype;
	}
	public String getExpensetypeId() {
		return expensetypeId;
	}
	public void setExpensetypeId(String expensetypeId) {
		this.expensetypeId = expensetypeId;
	}
	
	
	
	public String add(){
		expensetypeService.addoredit(expensetype);
		return "list";
	}
	public String edit(){
		expensetypeService.addoredit(expensetype);
		return "list";
	}
	public String delete(){
		expensetypeService.deletebyid(Integer.parseInt(expensetypeId));
		return "list";
	}
}


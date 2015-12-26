package action;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import service.IExpensetypeService;
import Model.Expensetype;
import Utilx.ViewStringSet;
import ViewModel.ExpdetailViewModel;
import ViewModel.ExpensetypeViewModel;
import ViewModel.ViewClass;

import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;


@ParentPackage("json-default")
@Action (value="expensetypeAssign",results={
		@Result(name="add",location="/Expensetypeaddoredit.jsp"),
		@Result(name="edit",location="/Expensetypeaddoredit.jsp"),
		@Result(name="list",location="/Expensetypelist.jsp"),
		@Result(name="fkfind",type="json")
		
		
		})
	
@Controller
public class ExpensetypeAssign extends ActionSupport{
	
	private ExpensetypeViewModel cvm;
	
	 private String expenseId;
	 
	 private String str;
	 
	 @Resource(name = "expensetypeService")
	private IExpensetypeService expensetypeService;

	private List list;

	public ExpensetypeViewModel getCvm() {
		return cvm;
	}

	public void setCvm(ExpensetypeViewModel cvm) {
		this.cvm = cvm;
	}

	public String getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(String expenseId) {
		this.expenseId = expenseId;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String add(){
		ExpensetypeViewModel expensetypeViewModel=new ExpensetypeViewModel();
		
		Expensetype expensetype = new Expensetype();
		
		ViewClass vc=new ViewClass();
		 vc.action="expensetypeAction!add";
		 vc.message="<div class='col-md-6 col-md-offset-1'><h2>添加收费种类信息</h2></div>";
		 expensetypeViewModel.viewClass=vc;
		
		ViewStringSet viewStringSet = new ViewStringSet(expensetype);
		
		expensetypeViewModel.setInput(viewStringSet.Addset());
		
		cvm = expensetypeViewModel;
		
		
		return "add";
		
	}
	
	public String edit(){
		
		ExpensetypeViewModel expensetypeViewModel=new ExpensetypeViewModel();
		
		Expensetype expensetype = expensetypeService.edit(Integer.parseInt(expenseId));
		
		ViewClass vc=new ViewClass();
		 vc.action="expensetypeAction!edit";
		 vc.message="<div class='col-md-6 col-md-offset-1'><h2>编辑收费种类信息</h2></div>";
		 expensetypeViewModel.viewClass=vc;
		
		ViewStringSet viewStringSet = new ViewStringSet(expensetype);
		
		expensetypeViewModel.setInput(viewStringSet.updateset());
		
		cvm = expensetypeViewModel;
		
		
		return "edit";
		
	}
	
	public String list() throws IllegalArgumentException, IllegalAccessException, IntrospectionException{
		
		 ExpensetypeViewModel expensetypeViewModel=new ExpensetypeViewModel();
		
		 list = expensetypeService.findall();
		 
		 ViewStringSet viewStringSet = new ViewStringSet();
			
			expensetypeViewModel.setList(viewStringSet.listSet(list));
			
			cvm = expensetypeViewModel;
			
			
			return "list";
	}
	public String findbyid() throws IllegalArgumentException, IllegalAccessException, IntrospectionException{
		 ExpensetypeViewModel expensetypeViewModel=new ExpensetypeViewModel();
		List list1=new ArrayList();
		Expensetype expensetype = expensetypeService.edit(Integer.parseInt(expenseId));
		list1.add(expensetype);
		
		ViewStringSet viewStringSet = new ViewStringSet();
			
		expensetypeViewModel.setList(viewStringSet.listSet(list1));
		
		cvm=expensetypeViewModel;
		
		return "list";
		
	}
	
	
	//@Action(value="fkfind",results={@Result(type="json",params={"options","str"})})
	public String fkfind(){
		
		List<Expensetype> list1= new ArrayList();
		
		list1=expensetypeService.findall();
		String s="";
		for (Expensetype expensetype : list1) {
			s+="&lt;option value='"+expensetype.getExpensename()+"'&gt;"+expensetype.getExpensename()+"&lt;/option&gt;";
		}
		this.str=s;
		return "fkfind";
		
		
		
	}

	
	 
	 
	 
	 
}

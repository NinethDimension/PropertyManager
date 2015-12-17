package action;

import java.beans.IntrospectionException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import service.ICarService;
import Model.Car;
import Utilx.ViewStringSet;
import ViewModel.CarViewModel;
import ViewModel.ViewClass;

import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;

@Action (value="carAssign",results={
		@Result(name="add",location="/Caraddoredit.jsp"),
		@Result(name="edit",location="/Caraddoredit.jsp"),
		@Result(name="list",location="/Carlist.jsp")})
@Controller
public class CarAssign extends ActionSupport{
	
	private CarViewModel cvm;
	
	 private String carId;
	 
	 @Resource(name = "carService")
	private ICarService carService;

	private List list;

	public CarViewModel getCvm() {
		return cvm;
	}

	public void setCvm(CarViewModel cvm) {
		this.cvm = cvm;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	
	
	public String add(){
		CarViewModel carViewModel=new CarViewModel();
		
		Car car = new Car();
		
		ViewClass vc=new ViewClass();
		 vc.action="carAction!add";
		 carViewModel.viewClass=vc;
		
		ViewStringSet viewStringSet = new ViewStringSet(car);
		
		carViewModel.setInput(viewStringSet.Addset());
		
		cvm = carViewModel;
		
		
		return "add";
		
	}
	
	public String edit(){
		
		CarViewModel carViewModel=new CarViewModel();
		
		Car car = carService.edit(Integer.parseInt(carId));
		
		ViewClass vc=new ViewClass();
		 vc.action="carAction!edit";
		 carViewModel.viewClass=vc;
		
		ViewStringSet viewStringSet = new ViewStringSet(car);
		
		carViewModel.setInput(viewStringSet.updateset());
		
		cvm = carViewModel;
		
		
		return "edit";
		
	}
	
	public String list() throws IllegalArgumentException, IllegalAccessException, IntrospectionException{
		
		 CarViewModel carViewModel=new CarViewModel();
		
		 list = carService.findall();
		 
		 ViewStringSet viewStringSet = new ViewStringSet();
			
			carViewModel.setList(viewStringSet.listSet(list));
			
			cvm = carViewModel;
			
			
			return "list";
	}
	 
	 
	 
	 
}
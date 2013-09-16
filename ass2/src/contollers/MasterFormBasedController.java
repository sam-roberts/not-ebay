package contollers;

import ass2.FormManager;
import ass2.ParameterManager;

public abstract class MasterFormBasedController {
	
	public ParameterManager paramManager;
	FormManager formManager;
	
	public String message;
	
	public MasterFormBasedController (ParameterManager params) {
		this.paramManager = params;
		message = null;
		
		formManager = new FormManager();
		createForm();
	}
	
	public String getMessage() {
		return message;
	}
	
	public boolean isInvalidForm() {
		return formManager.isMissingDetails();
	}
	
	public String getFormMessage() {
		return formManager.getMessage();
	}
	
	protected abstract void createForm();


}

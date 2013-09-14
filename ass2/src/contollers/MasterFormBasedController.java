package contollers;

import ass2.ParameterManager;

public class MasterFormBasedController {
	
	public ParameterManager paramManager;
	
	public String message;
	
	public MasterFormBasedController (ParameterManager params) {
		this.paramManager = params;
		message = null;
		
	}

}

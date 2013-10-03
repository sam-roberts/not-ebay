package ass2;

public class Form {
	String key;
	String value;
	String errorMessage;
	int restrictionType;
	Boolean invalid;
	int restrictionMax = 0;
	int restrictionMin = 0;
	boolean optional;
	
	public Form(String key, String value) {
		super();
		this.key = key;
		this.value = value;
		this.invalid = false;
		this.restrictionType = 0;
		this.optional = false;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	public int getRestrictionType() {
		return restrictionType;
	}

	public void setRestrictionType(int restrictionType) {
		this.restrictionType = restrictionType;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}
	public void setRestrictionMax(int howMuch) {
		restrictionMax = howMuch;
	}
	
	public int getRestrictionMax() {
		return restrictionMax;
	}

	public void setOptional() {
		this.optional= true;
		
	}
	
	
	public int getRestrictionMin() {
		return restrictionMin;
	}

	public void setRestrictionMin(int restrictionMin) {
		this.restrictionMin = restrictionMin;
	}

	public boolean isOptional() {
		return this.optional;
	}

}

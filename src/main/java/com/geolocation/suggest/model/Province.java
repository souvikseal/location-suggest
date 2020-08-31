package com.geolocation.suggest.model;

/**
 * Province codes for Canada
 */
public enum Province {
	AL("1", "Alberta"), 
	BC("2", "British Columbia"), 
	MB("3", "Manitoba"), 
	NB("4", "New Brunswick"),
	NT("13", "Northwest Territories"), 
	NS("7", "Nova Scotia"), 
	NU("14", "Nunavut"), 
	ON("8", "Ontario"),
	PE("9", "Prince Edward Island"), 
	QC("10", "Quebec"), 
	SK("11", "Saskatchewan"), 
	YT("12", "Yukon"),
	NL("5", "Newfoundland and Labrador");

	private String code;
	private String description;

	private Province(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * retrieve province shorthand from code
	 * @param code
	 * @return
	 */
	public static String getNameByCode(String code) {
		String selectedProvince = "";
		for (Province province : Province.values()) {
			if (code.equalsIgnoreCase(province.getCode())) {
				selectedProvince = province.name();
				break;
			}
		}
		return selectedProvince;
	}
}
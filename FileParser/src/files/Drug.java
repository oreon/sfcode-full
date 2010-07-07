package files;

public class Drug {

	private String atcCodes;

	private String absorption;

	private String cAS_Registry_Number;

	private String chemical_Formula;

	private String description;

	private String dosageForm;
	
	private String name;

	public String getAtcCodes() {
		return atcCodes;
	}

	public void setAtcCodes(String atcCodes) {
		this.atcCodes = atcCodes;
	}

	public String getAbsorption() {
		return absorption;
	}

	public void setAbsorption(String absorption) {
		this.absorption = absorption;
	}

	public String getcAS_Registry_Number() {
		return cAS_Registry_Number;
	}

	public void setcAS_Registry_Number(String cASRegistryNumber) {
		cAS_Registry_Number = cASRegistryNumber;
	}

	public String getChemical_Formula() {
		return chemical_Formula;
	}

	public void setChemical_Formula(String chemicalFormula) {
		chemical_Formula = chemicalFormula;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

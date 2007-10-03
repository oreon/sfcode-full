package bizobjects;

import javax.persistence.*;

@Entity
public class /*0 */Product extends org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String brand;

	private double listPrice;

	public String getName() {
		return this.name;
	}

	public String getBrand() {
		return this.brand;
	}

	public double getListPrice() {
		return this.listPrice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

}

package bizobjects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class /*0 */OrderItem
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private double salePrice;

	private int quantity = 1;

	public double getSalePrice() {
		return this.salePrice;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	private bizobjects.Product product;

	public void setProduct(bizobjects.Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "product_ID", nullable = false)
	public bizobjects.Product getProduct() {
		return this.product;
	}

}

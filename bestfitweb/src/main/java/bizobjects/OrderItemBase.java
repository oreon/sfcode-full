package bizobjects;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="OrderItem",uniqueConstraints={@UniqueConstraint(columnNames={product_id, order_id})})*/
public abstract class OrderItemBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected double salePrice;

	protected int quantity = 1;

	protected double total;

	public double getSalePrice() {
		return this.salePrice;
	}

	public int getQuantity() {
		return this.quantity;
	}

	@Transient
	public double getTotal() {

		total = 0.0;
		total = quantity * salePrice;

		return this.total;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTotal(double total) {
		this.total = total;
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

	public abstract OrderItem orderItemInstance();

	@Transient
	public String getDisplayName() {
		return product + "";
	}

}

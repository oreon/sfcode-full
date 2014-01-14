package com.oreon.phonestore.web.action.commerce;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Observer;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import com.oreon.phonestore.domain.commerce.Product;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge -
 * 
 */
public abstract class ProductListQueryBase extends BaseQuery<Product, Long> {

	private static final String EJBQL = "select product from Product product";

	protected Product product = new Product();

	@In(create = true)
	ProductAction productAction;

	public ProductListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public Product getInstance() {
		return getProduct();
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	// @Restrict("#{s:hasPermission('product', 'view')}")
	public List<Product> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<BigDecimal> priceRange = new Range<BigDecimal>();

	public Range<BigDecimal> getPriceRange() {
		return priceRange;
	}

	public void setPrice(Range<BigDecimal> priceRange) {
		this.priceRange = priceRange;
	}

	private static final String[] RESTRICTIONS = {
			"product.id = #{productList.product.id}",

			"product.archived = #{productList.product.archived}",

			"lower(product.name) like concat(lower(#{productList.product.name}),'%')",

			"product.price >= #{productList.priceRange.begin}",
			"product.price <= #{productList.priceRange.end}",

			"lower(product.description) like concat(lower(#{productList.product.description}),'%')",

			"product.dateCreated <= #{productList.dateCreatedRange.end}",
			"product.dateCreated >= #{productList.dateCreatedRange.begin}", };

	@Observer("archivedProduct")
	public void onArchive() {
		refresh();
	}

	// @Restrict("#{s:hasPermission('product', 'delete')}")
	public void archiveById(Long id) {
		productAction.archiveById(id);
		refresh();
	}

	/**
	 * create comma delimited row
	 * 
	 * @param builder
	 */
	// @Override
	public void createCsvString(StringBuilder builder, Product e) {

		builder.append("\""
				+ (e.getName() != null ? e.getName().replace(",", "") : "")
				+ "\",");

		builder.append("\"" + (e.getPrice() != null ? e.getPrice() : "")
				+ "\",");

		builder.append("\""
				+ (e.getDescription() != null ? e.getDescription().replace(",",
						"") : "") + "\",");

		builder.append("\r\n");
	}

	/**
	 * create the headings
	 * 
	 * @param builder
	 */
	// @Override
	public void createCSvTitles(StringBuilder builder) {

		builder.append("Name" + ",");

		builder.append("Price" + ",");

		builder.append("Description" + ",");

		builder.append("\r\n");
	}

	private void writeWithCsvBeanWriter() throws Exception {

		final List<Product> products = getAll();

		ICsvBeanWriter beanWriter = null;
		try {
			beanWriter = new CsvBeanWriter(new FileWriter(
					"target/writeWithCsvBeanWriter.csv"),
					CsvPreference.STANDARD_PREFERENCE);

			// the header elements are used to map the bean values to each
			// column (names must match)
			final String[] header = new String[] { "customerNo", "firstName",
					"lastName", "birthDate", "mailingAddress", "married",
					"numberOfKids", "favouriteQuote", "email", "loyaltyPoints" };

			final CellProcessor[] processors = getProcessors();

			// write the header
			beanWriter.writeHeader(header);

			// write the beans
			for (Product product : products) {
				beanWriter.write(product, header, processors);
			}

		} finally {
			if (beanWriter != null) {
				beanWriter.close();
			}
		}
	}

	private static CellProcessor[] getProcessors() {

		final CellProcessor[] processors = new CellProcessor[] {
				new UniqueHashCode(), // customerNo (must be unique)
				new NotNull(), // firstName
				new NotNull(), // lastName
				new FmtDate("dd/MM/yyyy"), // birthDate
				new NotNull(), // mailingAddress
				new Optional(new FmtBool("Y", "N")), // married
				new Optional(), // numberOfKids
				new NotNull(), // favouriteQuote
				new NotNull(), // email
				new LMinMax(0L, LMinMax.MAX_LONG) // loyaltyPoints
		};

		return processors;
	}
}

package oaw4.demo.classic.uml.meta;

/**
 * Represents a reports chart
 * 
 * @author jsingh
 * 
 */

public class ReportChart extends
		org.openarchitectureware.meta.uml.classifier.Class {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8360328761731599509L;
	private String seriesExpression;
	private String categoryExpression;
	private String valueExpression;
	private String chartType;

	/**
	 * @return
	 */
	public String getSeriesExpression() {
		return seriesExpression;
	}

	public void setSeriesExpression(String seriesExpression) {
		this.seriesExpression = seriesExpression;
	}

	public String getCategoryExpression() {
		return categoryExpression;
	}

	public void setCategoryExpression(String categoryExpression) {
		this.categoryExpression = categoryExpression;
	}

	public String getValueExpression() {
		if(valueExpression != null &&  valueExpression.contains("(")){
			GroupSummaryField gsf = new GroupSummaryField(valueExpression );
			return gsf.getOperationName();
		}
		return valueExpression;
	}

	public void setValueExpression(String valueExpression) {
		this.valueExpression = valueExpression;
	}

	/**
	 * Get the chart type - 3dpie, bar, xy etc.
	 * 
	 * @return
	 */
	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	/**
	 * The name of category expression will vary depending upon the chart type -
	 * intended use of this property is the wc engine
	 * 
	 * @return
	 */
	public String getCategoryExpressionName() {
		if (isPie())
			return "keyExpression";
		else if (chartType.equalsIgnoreCase("xyLine"))
			return "xValueExpression";
		else
			return "categoryExpression";
	}

	public String getValueExpressionName() {
		if (chartType.equalsIgnoreCase("xyLine"))
			return "yValueExpression";
		else
			return "valueExpression";
	}
	
	public boolean isPie(){
		return chartType.equalsIgnoreCase("Pie") ||
			chartType.equalsIgnoreCase("pie3D");
	}
	
	/**
	 * @return chart type without dimension indicator e.g pie3d would return
	 * pie
	 */
	public String getChartTypeWithoutD(){
		String type = chartType.toUpperCase();
		if(type.indexOf("3D") > 0 ) {
			return chartType.substring(0, type.indexOf("3D"));
		}
		return chartType;
	}

}

package org.witchcraft.base.entity;

/**
 * Bean to hold resutls of text search - the entity as well as the highlighted fragment
 * @author singj3
 *
 */
public class TextSearchResultHolder {

	private BaseEntity baseEntity;
	private String highlightedFragment;
	
	
	public TextSearchResultHolder( BaseEntity baseEntity, String highlightedFragment ) {
		super();
		this.baseEntity = baseEntity;
		this.highlightedFragment = highlightedFragment;
	}
	public BaseEntity getBaseEntity() {
		return baseEntity;
	}
	public void setBaseEntity( BaseEntity baseEntity ) {
		this.baseEntity = baseEntity;
	}
	public String getHighlightedFragment() {
		return highlightedFragment;
	}
	public void setHighlightedFragment( String highlightedFragment ) {
		this.highlightedFragment = highlightedFragment;
	}
	
	
}


/**
 * This is generated code - to edit code or override methods use - Category class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class CategoryDto {

	private String name;

	private CategoryDto parent;

	private java.util.Set<CategoryDto> subcategories = new java.util.HashSet<CategoryDto>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDto getParent() {
		return this.parent;
	}

	public void setParent(CategoryDto parent) {
		this.parent = parent;
	}

	public void addSubcategorie(CategoryDto subcategories) {
		//checkMaximumSubcategories();
		subcategories.setParent(this);
		this.subcategories.add(subcategories);
	}

	public void removeSubcategorie(CategoryDto subcategories) {
		this.subcategories.remove(subcategories);
	}

	public java.util.Set<CategoryDto> getSubcategories() {
		return this.subcategories;
	}

	public void setSubcategories(java.util.Set<CategoryDto> subcategories) {
		this.subcategories = subcategories;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getSubcategoriesCount() {
		return this.subcategories.size();
	}

	/*
	public void checkMaximumSubcategories(){
		// if(subcategories.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + subcategories );
	}*/

}

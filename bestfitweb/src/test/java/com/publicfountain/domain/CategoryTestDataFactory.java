package com.publicfountain.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.springframework.transaction.annotation.Transactional;

import com.publicfountain.domain.service.CategoryService;

@Transactional
public class CategoryTestDataFactory extends AbstractTestDataFactory<Category> {

	List<Category> categorys = new ArrayList<Category>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void register(Category category) {
		categorys.add(category);
	}

	public Category createCategoryOne() {
		Category category = new Category();

		try {

			category.setName("Wilson");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryTwo() {
		Category category = new Category();

		try {

			category.setName("Lavendar");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryThree() {
		Category category = new Category();

		try {

			category.setName("zeta");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryFour() {
		Category category = new Category();

		try {

			category.setName("Lavendar");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category createCategoryFive() {
		Category category = new Category();

		try {

			category.setName("Mark");

			TestDataFactory parentTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			register(category);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return category;
	}

	public Category loadOneRecord() {
		List<Category> categorys = categoryService.loadAll();

		if (categorys.isEmpty()) {
			persistAll();
			categorys = categoryService.loadAll();
		}

		return categorys.get(new Random().nextInt(categorys.size()));
	}

	public List<Category> getAllAsList() {

		if (categorys.isEmpty()) {
			createCategoryOne();
			createCategoryTwo();
			createCategoryThree();
			createCategoryFour();
			createCategoryFive();

		}

		return categorys;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Category category : categorys) {
			categoryService.save(category);
		}

		alreadyPersisted = true;
	}

}

package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.Category;
import java.util.List;

@WebService
public interface CategoryWebService {

	public Category loadById(Long id);

	public List<Category> findByExample(Category exampleCategory);

	public void save(Category category);

}

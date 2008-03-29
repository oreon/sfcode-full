package com.oreon.kgauge.web.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.richfaces.component.UITree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;
import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.service.BaseService;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.UnexpectedRollbackException;

import com.oreon.kgauge.domain.Category;
import com.oreon.kgauge.service.CategoryService;

public class CategoryBackingBean extends CategoryBackingBeanBase {

	private static final Logger log = Logger
	.getLogger(CandidateBackingBean.class);

}

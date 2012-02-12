package com.hrb.tservices.web.action.taxnews;

import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.taxnews.Language;
import com.hrb.tservices.domain.taxnews.TaxNewsTranslation;

//@Scope(ScopeType.CONVERSATION)
@Name("taxNewsAction")
public class TaxNewsAction extends TaxNewsActionBase implements
		java.io.Serializable {

	@Override
	public void prePopulateListTaxNewsTranslations() {

		if (listTaxNewsTranslations.isEmpty()) {
			TaxNewsTranslation transfr = new TaxNewsTranslation();
			transfr.setLanguage(Language.FRENCH);
			transfr.setTaxNews(instance);

			TaxNewsTranslation transen = new TaxNewsTranslation();
			transen.setLanguage(Language.ENGLISH);
			transen.setTaxNews(instance);

			listTaxNewsTranslations.add(transen);
			listTaxNewsTranslations.add(transfr);
		}

	}
}

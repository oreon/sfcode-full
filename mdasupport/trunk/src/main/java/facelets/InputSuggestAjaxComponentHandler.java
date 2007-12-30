/*
 * Copyright 2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package facelets;

import java.util.List;
import org.apache.log4j.*;

import com.sun.facelets.tag.MetaRule;
import com.sun.facelets.tag.MetaRuleset;
import com.sun.facelets.tag.MethodRule;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.jsf.ComponentConfig;
import com.sun.facelets.tag.jsf.html.HtmlComponentHandler;

/**
 * @author Peter Mahoney
 * @Milo van der Zee
 *            2007/02/07:  Changed a bit to allow the "itemLabelMethod" to be set.
 */

public class InputSuggestAjaxComponentHandler extends HtmlComponentHandler {

   private static final String SUGGESTED_ITEMS_METHOD_ATTRIBUTE_NAME = "suggestedItemsMethod";
   private static final String ITEM_LABEL_METHOD_ATTRIBUTE_NAME = "itemLabelMethod";
   private static final String MAX_SUGGESTED_ITEMS_ATTRIBUTE_NAME = "maxSuggestedItems";
   private static Logger _logger = Logger.getLogger(InputSuggestAjaxComponentHandler.class.getName());

   public InputSuggestAjaxComponentHandler(ComponentConfig tagConfig) {
       super(tagConfig);
       _logger.info("InputSuggestAjaxComponentHandler: " + tagConfig);
   }

   protected MetaRuleset createMetaRuleset(Class type)
   {
      _logger.info("createMetaRuleset");
       MetaRuleset m = super.createMetaRuleset(type);
        
       m.addRule(getSuggestedItemsMethodRule(getAttribute(MAX_SUGGESTED_ITEMS_ATTRIBUTE_NAME)));
       m.addRule(getItemLabelMethodRule());

       return m;
   }

   private MetaRule getItemLabelMethodRule() {
         Class[] paramList = new Class[]{Object.class};
               
         _logger.info("getItemLabelMethodRule");
         return new MethodRule(ITEM_LABEL_METHOD_ATTRIBUTE_NAME, String.class, paramList);
   }
   
   private MetaRule getSuggestedItemsMethodRule(TagAttribute maxItems) {
      Class[] paramList = maxItems != null ? 
            new Class[] {String.class, Integer.class} : new Class[]{String.class};
            
      _logger.info("getSuggestedItemsMethodRule: " + maxItems);
      return new MethodRule(SUGGESTED_ITEMS_METHOD_ATTRIBUTE_NAME, List.class, paramList);
   }
}
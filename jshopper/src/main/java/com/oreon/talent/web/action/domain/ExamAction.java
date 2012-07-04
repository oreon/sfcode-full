package com.oreon.talent.web.action.domain;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;

@Named
@Stateful
@ConversationScoped
public class ExamAction extends ExamActionBase implements java.io.Serializable {

	public void startProcess() {
		try {
			KnowledgeBase kbase = readKnowledgeBase();
			// create the entity manager factory and register it in the environment
			EntityManagerFactory emf = Persistence.createEntityManagerFactory( "main" );
			Environment env = KnowledgeBaseFactory.newEnvironment();
			env.set( EnvironmentName.ENTITY_MANAGER_FACTORY, emf );
			
			
			 
	        Map<String, Object> params = new HashMap<String, Object>();
	        
	      //  org.drools.persistence.info.WorkItemInfo wi;
	        //org.jbpm.task.Comment comment;

	        params.put("name", "Arthur");
	        params.put("money", 1800);
	        
			
			// create a new knowledge session that uses JPA to store the runtime state
			StatefulKnowledgeSession ksession = JPAKnowledgeService.newStatefulKnowledgeSession( kbase, null, env );
			int sessionId = ksession.getId();
			// invoke methods on your method here
			ksession.startProcess( "process_1" , params);
			ksession.dispose();
		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( "process_1.bpmn" ), ResourceType.BPMN2 );
		return kbuilder.newKnowledgeBase();
	}

}

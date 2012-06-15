package com.oreon.talent;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class ProcessMain {

    public static final void main(String[] args) throws Exception {
        // load up the knowledge base
        KnowledgeBase kbase = readKnowledgeBase();
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        
        Map<String, Object> params = new HashMap<String, Object>();
        
      //  org.drools.persistence.info.WorkItemInfo wi;
        //org.jbpm.task.Comment comment;

        params.put("name", "Arthur");
        params.put("money", 1800);
        
        // start a new process instance
        ksession.startProcess("process_1",params);
    }

    private static KnowledgeBase readKnowledgeBase() throws Exception {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("process_1.bpmn"), ResourceType.BPMN2);
        return kbuilder.newKnowledgeBase();
    }
    
}
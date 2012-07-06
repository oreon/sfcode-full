package com.oreon.talent.web.action.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.SystemEventListenerFactory;
import org.drools.base.MapGlobalResolver;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.workitem.wsht.CommandBasedWSHumanTaskHandler;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.mina.MinaTaskClientConnector;
import org.jbpm.task.service.mina.MinaTaskClientHandler;
import org.jbpm.task.service.responsehandlers.BlockingTaskSummaryResponseHandler;

import com.oreon.talent.TaskManagerServer;
import com.oreon.talent.domain.Exam;

@Named
@Stateful
@ConversationScoped
public class ExamAction extends ExamActionBase implements java.io.Serializable {

	// @Inject
	// @DefaultTransaction
	// SeamTransaction transaction;

	public void startProcess() {
		try {

			new TaskManagerServer().startServer();
			KnowledgeBase kbase = readKnowledgeBase();
			TransactionManager manager = (TransactionManager) new InitialContext().lookup( "java:jboss/TransactionManager" );
			// create the entity manager factory and register it in the environment
			EntityManagerFactory emf = Persistence.createEntityManagerFactory( "main" );
			Environment env = KnowledgeBaseFactory.newEnvironment();
			env.set( EnvironmentName.ENTITY_MANAGER_FACTORY, emf );
			// env.set( EnvironmentName.TRANSACTION, getUserTransaction());
			env.set( EnvironmentName.GLOBALS, new MapGlobalResolver() );

			env.set( EnvironmentName.APP_SCOPED_ENTITY_MANAGER, entityManager );
			// env.set(EnvironmentName.TRANSACTION_MANAGER, manager);

			Map<String,Object> params = new HashMap<String,Object>();

			// org.drools.persistence.info.WorkItemInfo wi;
			// org.jbpm.task.Comment comment;

			params.put( "name", "Arthur" );
			params.put( "money", 800 );

			// create a new knowledge session that uses JPA to store the runtime state
			StatefulKnowledgeSession ksession = JPAKnowledgeService.newStatefulKnowledgeSession( kbase, null, env );

			CommandBasedWSHumanTaskHandler wsHumanTaskHandler = new CommandBasedWSHumanTaskHandler( ksession );
			ksession.getWorkItemManager().registerWorkItemHandler( "Human Task", wsHumanTaskHandler );
			String name = "ksclient-" + UUID.randomUUID();
			
			// invoke methods on your method here
			ksession.startProcess( "process_1", params );


			TaskClient client = new TaskClient( new MinaTaskClientConnector( name, new MinaTaskClientHandler(
							SystemEventListenerFactory.getSystemEventListener() ) ) );

			client.connect( "127.0.0.1", 9123 );

			

			BlockingTaskSummaryResponseHandler taskSummaryResponseHandler = new BlockingTaskSummaryResponseHandler();

			client.getTasksAssignedAsPotentialOwner( "john", "en-US", taskSummaryResponseHandler );

			List<TaskSummary> tasks = taskSummaryResponseHandler.getResults();
			
			for ( TaskSummary taskSummary : tasks ) {
				System.out.println(taskSummary.getName());
			}

			
			getUserTransaction().commit();
			// ksession.dispose();
		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( "process_1.bpmn" ), ResourceType.BPMN2 );

		return kbuilder.newKnowledgeBase();
	}

	protected javax.transaction.UserTransaction getUserTransaction() throws NamingException {
		Context initialContext = new InitialContext();
		try {
			return (javax.transaction.UserTransaction) initialContext.lookup( "java:jboss/UserTransaction" );
		} catch ( NameNotFoundException nnfe ) {
			try {
				// Embedded JBoss has no java:comp/UserTransaction
				javax.transaction.UserTransaction ut = (javax.transaction.UserTransaction) initialContext.lookup( "UserTransaction" );
				ut.getStatus(); // for glassfish, which can return an unusable UT
				return ut;
			} catch ( Exception e ) {
				throw nnfe;
			}
		}
	}

	@Override
	public void paginate() {
		// TODO Auto-generated method stub
		super.paginate();
		setupInit();
	}

	public void setupInit() {

		if ( !getAll().isEmpty() )
			return;

		createExam( "Java", "This exam is to test people for their capabilities in basic to intermediate java." );
		createExam( "EJB", "This exam is to test candidates for their capabilities in basic to intermediate JEE (enterprise java)." );
		createExam( "dot net", "This exam test dot net skills as well as familiarity with other microsoft technologies." );
		createExam( "Data Modeling", "This exam tests people for their command over data modeling and comfort level with relational databases such as Oracle" );
	}

	private void createExam( String name, String description ) {
		Exam exam = new Exam();
		exam.setTitle( name );
		exam.setDescription( description ); // "This exam is to test people for their capabilities in basic to intermediate java." );

		entityManager.persist( exam );
	}
}

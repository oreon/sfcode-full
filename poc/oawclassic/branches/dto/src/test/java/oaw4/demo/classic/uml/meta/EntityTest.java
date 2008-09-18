package oaw4.demo.classic.uml.meta;

import junit.framework.TestCase;

public class EntityTest extends TestCase {

	public void testCreateQryFromXML(){
		String qt = "<query name=\"examsTakenByCandidate\" genericReturnType=\"ExamInstance\"" +
			" text=\"select e from ExamInstance e where e.candidate.id = ?1\" />";
		NamedQuery nq = new Entity().createQryFromXML(qt);
		assertEquals(nq.getName(), "examsTakenByCandidate");
	} 
}

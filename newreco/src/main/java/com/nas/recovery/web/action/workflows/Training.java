
	
	package com.nas.recovery.web.action.workflows;
	
	
	import org.jboss.seam.ScopeType;
	import org.jboss.seam.annotations.In;
	import org.jboss.seam.annotations.Name;
	import org.jboss.seam.annotations.Out;
	import org.jboss.seam.annotations.bpm.CreateProcess;
	import org.jboss.seam.annotations.bpm.EndTask;
	import org.jboss.seam.annotations.bpm.StartTask;
	import org.jbpm.JbpmContext;
	import org.witchcraft.jbpm.BaseJbpmProcessAction;

	
	@Name("trainingProcessAction")	
	//@Scope(ScopeType.CONVERSATION)
	public class Training extends TrainingBase {
	
		@CreateProcess(definition = "training")
		public void startProcess() {
		
		}
		
	}
	
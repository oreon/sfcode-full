package com.wc.sample;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.annotations.*;
import org.jboss.seam.bpm.TaskInstance;
import org.jboss.seam.ScopeType;
import org.jbpm.taskmgmt.def.Task;

@Name("orderStock")
//@Scope(ScopeType.CONVERSATION)
public class OrderStock 
{
 
   @Out(scope=ScopeType.BUSINESS_PROCESS, required=false)
   Long processQuantity;
   
   private int quantity ;
     
   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
     
  @CreateProcess(definition="simple")
  public  void startProcess() {      
    processQuantity = new Long(getQuantity());
  }
  @StartTask
  @EndTask(transition="next")
    public void done() {
	  
	  //TaskInstance task = new TaskInstance();
	 // task.getTaskInstance().ge
	  //task.getActorIdExpression()
	 // task.getName()
	//  task.getStartState().getName()
  }
  @StartTask
  @EndTask(transition="cancel")
    public void cancel() {
  }
   
}


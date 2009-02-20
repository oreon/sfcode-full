package com.wcmda.model.action;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.annotations.security.Restrict;


@Name("todoList")
@Restrict("#{identity.loggedIn}")
public class TodoList
{
   private String description;
   
   public String getDescription()
   {
	   
	  // org.jboss.seam.bpm.
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }
              
   @CreateProcess(definition="todo")
   public void createTodo() {}
              
   @StartTask @EndTask
   public void done() {}

}
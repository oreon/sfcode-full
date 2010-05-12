package com.wc.sample;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class GetFromStockActionHandler implements ActionHandler {

	public void execute(ExecutionContext context) throws Exception {
		System.out.println("Getting goods from factory!");

		context.getContextInstance().setVariable("goodsInStock",
				new Integer(300));

		context.leaveNode();
	}

}

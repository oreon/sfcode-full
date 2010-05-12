package com.wc.sample;

import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.graph.node.DecisionHandler;

public class VerifyOrder implements DecisionHandler {

	public String decide(ExecutionContext executionContext) throws Exception {

		Integer goodsOrdered = (Integer) executionContext.getContextInstance()
				.getVariable("goodsOrdered");
		
		Order order = (Order) executionContext.getContextInstance().getVariable("order");
		
		System.out.println("Goods ordered " + goodsOrdered.intValue() + " " + order.getQty() + " " + order.getProductName());

		Integer goodsInStock = (Integer) executionContext.getContextInstance()
				.getVariable("goodsInStock");
		System.out.println("Goods in stock " + goodsInStock.intValue());

		if (goodsOrdered.intValue() > goodsInStock.intValue()) {
			return "trGetFromStock";
		} else {
			return "trDeliver";
		}
	}

}

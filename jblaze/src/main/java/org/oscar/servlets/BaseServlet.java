package org.oscar.servlets;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.caisi.persistence.base.exceptions.BusinessException;
import org.caisi.persistence.base.exceptions.CriticalException;
import org.caisi.persistence.viewhelper.ViewHelper;
import org.springframework.security.AccessDeniedException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseServlet extends HttpServlet {

	public static final String REFERER = "referer";
	private static final Logger logger = Logger.getLogger(BaseServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			invokeMethod(request, response);
		} catch (org.caisi.persistence.base.ValidationException ve) {
			ViewHelper.createMessage(request, ViewHelper.createMessage(ve
					.getMessages()), true);
			forwardBackToReferrer(request, response);
		} catch (BusinessException e) {
			handleError(request, response, e.getMessage(), e);
		} catch (AccessDeniedException ade) {
			handleError(request, response,
					"You dont have previlige to execute this operation.", ade);
		} catch (Exception e) {
			handleError(request, response, "A serious error has occured: "
					+ e.getMessage(), e);
		}
	}

	private void handleError(HttpServletRequest request,
			HttpServletResponse response, String message, Exception e)
			throws ServletException, IOException {
		logger.error(e);
		e.printStackTrace();
		ViewHelper.createMessage(request, message, true);
		forwardBackToReferrer(request, response);
	}

	private void forwardBackToReferrer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String referrer = getReferrer(request.getHeader("Referer"));
		HttpSession session = request.getSession();

		if (referrer.startsWith("/formHandler/")) {
			referrer = (String) session.getAttribute(REFERER);
		} else
			session.setAttribute(REFERER, referrer);

		RequestDispatcher dispatcher = request.getRequestDispatcher(referrer);
		if (dispatcher != null)
			dispatcher.forward(request, response);
		else
			response.getWriter().print(
					"No request dispatcher to forward  to !!");
	}

	private String getReferrer(String msg) {

		String[] arr = msg.split("/");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 3)
				builder.append("/" + arr[i]);
		}

		return StringUtils.isEmpty(builder.toString()) ? "/" : builder
				.toString();
	}

	/**
	 * @param request
	 * @param response
	 */
	public void invokeMethod(HttpServletRequest request,
			HttpServletResponse response) {
		String uri[] = request.getRequestURI().split("/");
		String classAndMethod = uri[uri.length - 1];
		String clsNameArr[] = classAndMethod.split("\\.");

		if (clsNameArr.length < 2)
			throw new BusinessException(
					"formahndler target should be of format beanName.methodName");

		String beanName = clsNameArr[0];
		String methodName = clsNameArr[1];

		doInvoke(request, response, beanName, methodName);

	}

	/** Actual invoke
	 * @param request
	 * @param response
	 * @param beanName
	 * @param methodName
	 */
	private void doInvoke(HttpServletRequest request,
			HttpServletResponse response, String beanName, String methodName) {
			
		Object bean = getSpringcontext().getBean(beanName);
		try {
			Method method = bean.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(bean, request, response);
		} catch (InvocationTargetException ite) {
			if (ite.getTargetException() instanceof org.caisi.persistence.base.ValidationException)
				throw (org.caisi.persistence.base.ValidationException) ite
						.getTargetException();
			else if(ite.getTargetException() instanceof BusinessException)
				throw (BusinessException)ite.getTargetException();
			else 
				throw new CriticalException("Unknown error" , ite.getTargetException());
		} catch (Exception e) {
			throw new CriticalException("An error occured invoking mentod: "+ beanName + ":" + methodName, e);
		}
	}

	private WebApplicationContext getSpringcontext() {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}

}

package com.chinadovey.parking.core.supports.spring;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.annotation.SecurityIgnoreHandler;
import com.chinadovey.parking.core.secu.SecuObject;
import com.chinadovey.parking.core.secu.SessionOpt;

import freemarker.core._RegexBuiltins.matchesBI;

/**
 * URL权限验证拦截器
 * 
 * @author Bean
 *
 */
public class InterceptorSessiionCheck extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(InterceptorSessiionCheck.class);
	private String loginUrl;
	private List<String> uncheckUrls;
	private List<String> uncheckRegexUrls;
	private String SESSIONOUT = "";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 对微信访问路劲不做拦截
		String requestUrl = request.getServletPath();
		if (uncheckRegexUrls != null && checkContains(requestUrl)) {
			return true;
		}
		if (uncheckUrls != null && uncheckUrls.contains(requestUrl)) {
			return true;
		}

		LocalObjects.setRequest(request);
		LocalObjects.setResponse(response);

		if (handler instanceof HandlerMethod) {

			HandlerMethod hm = (HandlerMethod) handler;

			if (logger.isDebugEnabled()) {
				logger.info("Request:" + request.getRequestURL());
			}
			// 忽略
			if (hm.getMethodAnnotation(SecurityIgnoreHandler.class) != null) {
				if (logger.isDebugEnabled()) {
					logger.info(SecurityIgnoreHandler.class.getName());
				}
				return true;
			}
			// 如果会话已不存在
			SecuObject secuObj = SessionOpt.getSecuObject(request.getSession());
			if (secuObj == null) {
				if (logger.isInfoEnabled()) {
					logger.info("=============认证失败================");
					logger.info(request.getRemoteHost());
					logger.info(request.getRequestURL());
				}
				if (request.getHeader("x-requested-with") != null
						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					// 在响应头设置session状态
					response.setHeader("sessionstatus", "sessionOut");
					response.sendRedirect(request.getContextPath() + loginUrl);
				} else {
					response.sendRedirect(request.getContextPath() + loginUrl);
				}
				return false;
			} else {
				SecurityAccessCheckable checkable = hm.getMethodAnnotation(SecurityAccessCheckable.class);
				if (checkable == null) {
					if (request.getHeader("x-requested-with") != null
							&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
						// 在响应头设置session状态
						response.setHeader("sessionstatus", "permissionDenied");
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("没有配置权限，请与管理员进行联系");
						request.getRequestDispatcher("/_permission_undefined").forward(request, response);
					} else {
						request.setAttribute("_msg",
								hm.getBeanType().getName() + ":" + hm.getMethod().getName() + "(...)");
						response.setContentType("text/html;charset=utf-8");
						request.getRequestDispatcher("/_permission_undefined").forward(request, response);
					}
					return false;
				}
				if (checkable == null
						|| !secuObj.validate(checkable.resource().getName(), checkable.operation().name())) {
					if (request.getHeader("x-requested-with") != null
							&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
						// 在响应头设置session状态
						response.setHeader("sessionstatus", "permissionDenied");
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().print("没有访问权限，请与管理员进行联系!");
					} else {
						request.getRequestDispatcher("/_permission_denied").forward(request, response);
					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkContains(String requestUrl) {
		boolean flag = false;
		for (String uncheckRegexUrl : uncheckRegexUrls) {
			uncheckRegexUrl = "^" + uncheckRegexUrl.replaceAll("[*]", "(.+)?") + "$";
			flag = Pattern.matches(uncheckRegexUrl,requestUrl);
			if(flag){
				return flag;
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		// if (logger.isDebugEnabled()) {
		// logger.debug("========Interceptor-postHandle:Do Something here...");
		// logger.debug("Interceptor-Handler:" + handler);
		// }
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		// if (logger.isDebugEnabled()) {
		// logger.debug("========Interceptor-afterCompletion:Do Something
		// here...");
		// logger.debug("Interceptor-Handler:" + handler);
		// }
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	public List<String> getUncheckRegexUrls() {
		return uncheckRegexUrls;
	}

	public void setUncheckRegexUrls(List<String> uncheckRegexUrls) {
		this.uncheckRegexUrls = uncheckRegexUrls;
	}

}

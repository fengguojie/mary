package com.chinadovey.parking.core.secu;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;

public class SecuValidateTag extends TagSupport {

	private static final long serialVersionUID = 7802808241999959119L;

	private String resource;
	
	private String operation = SecurityAccessCheckable.Operation.VIEW.name();
	
	public SecuValidateTag() {
	}
	
	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		SecuObject secuObj = SessionOpt.getSecuObject(pageContext.getSession());
		if(secuObj!=null&&secuObj.validate(resource, operation)){
			return EVAL_PAGE;
		}
		return SKIP_BODY;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}

package com.chinadovey.parking.core.secu;

import java.io.IOException;
import java.util.Map;

import com.chinadovey.parking.core.annotation.SecurityAccessCheckable;
import com.chinadovey.parking.core.supports.spring.LocalObjects;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class SecuValidateTemplateDirectiveModel implements
		TemplateDirectiveModel {

	public SecuValidateTemplateDirectiveModel() {
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		if (params.isEmpty()) {
			throw new TemplateModelException(
					"This directive doesn't allow parameters.");
		}
		if (loopVars.length != 0) {
			throw new TemplateModelException(
					"This directive doesn't allow loop variables.");
		}

		String resource = params.containsKey("resource")?params.get("resource").toString():null;
		String operation = params.containsKey("operation")?params.get("operation").toString():SecurityAccessCheckable.Operation.VIEW.name();
		
		SecuObject secuObj = SessionOpt.getSecuObject(LocalObjects.getRequest().getSession());
		if(secuObj!=null&&secuObj.validate(resource, operation)){
			if (body != null) {
				body.render(env.getOut());
			}
		}
	}
}

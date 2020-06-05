package com.authorization.authorizationService.security.auth;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityEvaluationContextExtension {

	public String getExtensionId() {
		return "security";
	}


	public SecurityExpressionRoot getRootObject() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return new SecurityExpressionRoot(authentication) { };
	}

}
package baldwin.dgte.aop;

import javax.inject.Named;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect @Named
public class ManagementArchitecture {
	@Pointcut("execution(* baldwin.dgte.controller.ManagementController.*(..))")
	public void managementOperation() {}
}

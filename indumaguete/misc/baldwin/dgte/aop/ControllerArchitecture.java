package baldwin.dgte.aop;

import javax.inject.Named;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect @Named
public class ControllerArchitecture {
	@Pointcut("execution(* baldwin.dgte.controller.ReadOnlyController.*(..))")
	public void readOnlyOperation() {}
	
	@Pointcut("execution(* baldwin.dgte.controller.ManagementController.*(..))")
	public void managementOperation() {}
}

package baldwin.dgte.aop;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect @Named
public class ManagementServiceAspect {
	static Logger log = Logger.getLogger(ManagementServiceAspect.class);
	
	@Before("baldwin.dgte.aop.ManagementArchitecture.managementOperation()")
	public void logItemViewed(JoinPoint joinPoint) {
		StringBuilder message = new StringBuilder();
		for(Object o : joinPoint.getArgs()) {
			message.append(" Method: [" + joinPoint.getSignature().getName() + "]");
			if(o instanceof HttpServletRequest) {
				message.append(" Ip: [" + ((HttpServletRequest)o).getRemoteAddr() + "]");
			}
		}
		log.info(message);
	}
}

package baldwin.dgte.aop;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect @Named
public class ReadOnlyServiceAspect {
	static Logger log = Logger.getLogger(ReadOnlyServiceAspect.class);
	
	@Before("baldwin.dgte.aop.ControllerArchitecture.readOnlyOperation() && " +
			"args(domain,..)")
	public void logItemViewed(JoinPoint joinPoint, String domain) {
		StringBuilder message = new StringBuilder("[" + domain + "]");
		for(Object o : joinPoint.getArgs()) {
			message.append(" Method: [" + joinPoint.getSignature().getName() + "]");
			if(o instanceof HttpServletRequest) {
				message.append(" Ip: [" + ((HttpServletRequest)o).getRemoteAddr() + "]");
			} else {
				message.append("param: [" + o + "]");
			}
		}
		log.info(message);
	}
}

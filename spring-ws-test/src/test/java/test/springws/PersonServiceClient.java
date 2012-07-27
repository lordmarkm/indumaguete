package test.springws;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.springws.MarshallingPersonService;
import test.springws.dto.GetPersonsRequest;
import test.springws.dto.PersonResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:personServiceClientTest.xml")
public class PersonServiceClient implements MarshallingPersonService {
    static Logger log = Logger.getLogger(PersonServiceClient.class);
	
	@Autowired
    private WebServiceTemplate wsTemplate;
   
    /**
     * Gets person list.
     */
    public PersonResponse getPersons(GetPersonsRequest request) {
        PersonResponse response = (PersonResponse) wsTemplate.marshalSendAndReceive(request);

        return response;        
    }
    
    @Test
    public void test() {
    	log.info("About to hit " + wsTemplate.getDefaultUri());
    	
    	GetPersonsRequest getPersonsRequest = new GetPersonsRequest();
    	getPersonsRequest.setName("Mark?");
    	
    	PersonResponse response = getPersons(getPersonsRequest);
    	log.info("People: " + response.getPerson());
    }
}
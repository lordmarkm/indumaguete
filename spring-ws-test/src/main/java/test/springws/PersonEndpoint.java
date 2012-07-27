package test.springws;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import test.springws.dto.GetPersonsRequest;
import test.springws.dto.Person;
import test.springws.dto.PersonResponse;

@Endpoint
public class PersonEndpoint implements MarshallingPersonService {
    static Logger log = Logger.getLogger(PersonEndpoint.class);
	
    /**
     * Gets person list.
     */
    @PayloadRoot(localPart=GET_PERSONS_REQUEST, namespace=NAMESPACE)
    public PersonResponse getPersons(GetPersonsRequest request) {
    	log.info("Request received: " + request);
    	
        return new PersonResponse().withPerson(
                   new Person().withId(1).withFirstName("Joe").withLastName("Smith"),
                   new Person().withId(2).withFirstName("John").withLastName("Jackson"));        
    }
}
package test.springws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import test.springws.dto.GetPersonsRequest;
import test.springws.dto.Person;
import test.springws.dto.PersonResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ws-ctx.xml")
public class MockServerTest {
	static Logger log = LoggerFactory.getLogger(MockServerTest.class);
	
	@Autowired private ApplicationContext ctx;
	
	private MockWebServiceClient mockClient;
	
	@Before
	public void init() {
		mockClient = MockWebServiceClient.createClient(ctx);
	}
	
	@Test
	public void personEndpoint() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("test.springws.dto");
		Marshaller m = jc.createMarshaller();
		
		GetPersonsRequest request = new GetPersonsRequest();
		StringWriter sw = new StringWriter();
		m.marshal(request, sw);
		String marshalledRequest = sw.toString();
		log.info("About to send " + marshalledRequest);
		Source requestPayload = new StringSource(marshalledRequest);
		
		PersonResponse expectedResponse = new PersonResponse().withPerson(
                new Person().withId(1).withFirstName("Joe").withLastName("Smith"),
                new Person().withId(2).withFirstName("John").withLastName("Jackson"));        
		StringWriter respSw = new StringWriter();
		m.marshal(expectedResponse, respSw);
		String marshalledResponse = respSw.toString();
		log.info("Expecting response " + marshalledResponse);
		Source responsePayload = new StringSource(marshalledResponse);
		
		mockClient.sendRequest(withPayload(requestPayload))
				.andExpect(payload(responsePayload));
	}
}

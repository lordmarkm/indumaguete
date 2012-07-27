package test.springws;

import test.springws.dto.GetPersonsRequest;
import test.springws.dto.PersonResponse;

public interface MarshallingPersonService {
    
    public final static String NAMESPACE = "http://www.springws.test/dto";
    public final static String GET_PERSONS_REQUEST = "get-persons-request";

    /**
     * Gets person list.
     */
    public PersonResponse getPersons(GetPersonsRequest request);

}
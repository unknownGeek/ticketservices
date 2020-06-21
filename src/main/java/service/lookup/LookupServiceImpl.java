package service.lookup;

import service.model.HelloMessage;
import service.wrapper.ServiceResponse;
import service.wrapper.ServiceResponseBuilder;


public class LookupServiceImpl implements LookupService{

    public ServiceResponse<HelloMessage> getHelloMessage(String name) {
        HelloMessage helloMessage = new HelloMessage(name);
        return ServiceResponseBuilder.ok(helloMessage,  null);
    }
}
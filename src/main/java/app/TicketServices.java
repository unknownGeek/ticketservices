package app;

import service.consumer.ConsumerServiceImpl;
import service.filters.CORSFilter;
import service.lookup.LookupServiceImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class TicketServices extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    private LookupServiceImpl lookupService;

    private ConsumerServiceImpl consumerService;

    public TicketServices() {
        this.lookupService = new LookupServiceImpl();
        this.consumerService = new ConsumerServiceImpl();
        singletons.add(lookupService);
        singletons.add(consumerService);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(CORSFilter.class);
        return resources;
    }


}
        
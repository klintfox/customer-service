package com.bank.resource;

import com.bank.entity.Customer;
import com.bank.repository.CustomerRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.annotation.Counted;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.Optional;
import java.util.UUID;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private static final Logger Log = Logger.getLogger(CustomerResource.class);

    @Inject
    CustomerRepository customerRepository;

    @Inject
    MeterRegistry meterRegistry;

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") UUID id) {
        meterRegistry.counter("customer_service_customers_get_by_id_requests_total").increment();
        Optional<Customer> customer = customerRepository.findByIdOptional(id);
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " not found");
        }
        return Response.ok(customer.get()).build();
    }

    @POST
    @Transactional
    @RolesAllowed("USER")
    public Response createCustomer(Customer customer) {
        Log.info("Creating customer with document: " + customer.document);
        if (customer.name == null || customer.name.isBlank() ||
                customer.document == null || customer.document.isBlank() ||
                customer.email == null || customer.email.isBlank()) {
            throw new ValidationException("Name, document and email are required");
        }
        customer.persist();
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    @Path("/exists")
    public Response customerExists(@QueryParam("document") String document) {
        Customer customer = customerRepository.findByDocument(document);
        boolean exists = customer != null;
        return Response.ok(exists).build();
    }
}

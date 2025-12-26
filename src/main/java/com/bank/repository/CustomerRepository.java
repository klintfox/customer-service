package com.bank.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import com.bank.entity.Customer;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {
    /**
     * Finds a customer by their unique document.
     * @param document the document to search for
     * @return the customer if found, otherwise null
     */
    public Customer findByDocument(String document) {
        return find("document", document).firstResult();
    }
}

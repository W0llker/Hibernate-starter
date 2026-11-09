package org.example.mapper;

import org.example.dto.CustomersRequest;
import org.example.dto.CustomersResponce;
import org.example.entity.Customers;
import org.example.entity.CustomersInfo;
import org.example.entity.Role;

public class CustomerMapper implements BaseMapper<Customers, CustomersResponce, CustomersRequest> {
    @Override
    public Customers getE(CustomersRequest r) {
        return Customers.builder()
                .fullName(r.fullName())
                .customersInfo(r.customersInfo())
                .role(Role.USER)
                .build();
    }

    @Override
    public CustomersResponce getRESP(Customers customers) {
        return null;
    }
}

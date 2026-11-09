package org.example.service;

import org.example.dao.CustomersDao;
import org.example.dto.CustomersRequest;
import org.example.dto.CustomersResponce;
import org.example.entity.CustomersInfo;
import org.example.mapper.CustomerMapper;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.xml.validation.Validator;

public class ServiceCustomers {
    private CustomersDao customersDao;
    private CustomerMapper customerMapper;

    public ServiceCustomers(CustomersDao customersDao, CustomerMapper customerMapper) {
        this.customersDao = customersDao;
        this.customerMapper = customerMapper;
    }

    public CustomersResponce addCustomer(CustomersRequest customersRequest) {
        var validatorsFactory = Validation.buildDefaultValidatorFactory();
        var validators = validatorsFactory.getValidator();
        var validatorResult = validators.validate(customersRequest);

        if(!validatorResult.isEmpty()) {
            throw new ConstraintViolationException(validatorResult);
        }

        var customer = customersDao.addCustomers(customerMapper.getE(customersRequest));
        System.out.println(customer);
        return new CustomersResponce();
    }
}

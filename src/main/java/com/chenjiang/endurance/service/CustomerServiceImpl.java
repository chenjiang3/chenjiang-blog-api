package com.chenjiang.endurance.service;

import com.chenjiang.endurance.entity.Customer;
import com.chenjiang.endurance.mapper.CustomerMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String add(Customer customer) {
        return customerMapper.add(customer);
    }

}

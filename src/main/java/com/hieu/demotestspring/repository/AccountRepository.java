package com.hieu.demotestspring.repository;

import com.hieu.demotestspring.entity.Account;
import org.springframework.stereotype.Repository;

import javax.naming.OperationNotSupportedException;

@Repository
public class AccountRepository {
    public Account getAccountByNumber(String accNumber){
        throw new RuntimeException("Function is not implemented");
    }

    public boolean updateAccount(Account account)
    {
        throw new RuntimeException("Function is not implemented");
    }
}


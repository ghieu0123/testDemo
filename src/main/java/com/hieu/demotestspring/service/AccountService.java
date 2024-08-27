package com.hieu.demotestspring.service;

import com.hieu.demotestspring.entity.Account;
import com.hieu.demotestspring.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;

@Service
public class AccountService {
    AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public String withDrawMoney(String accNumber, Long amount)
    {
        Account acc = accountRepository.getAccountByNumber(accNumber);
        if(acc==null)
            return "Tài khoản không hợp lệ.";

        if(acc.getMoney()< amount)
            return "Số dư không đủ để thực hiện.";

        acc.setMoney(acc.getMoney()-amount);
        if(accountRepository.updateAccount(acc))
            return "Giao dịch thành công.";

        return "Giao dịch thất bại";
    }
}
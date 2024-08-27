package com.hieu.demotestspring.service;

import com.hieu.demotestspring.entity.Account;
import com.hieu.demotestspring.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.OperationNotSupportedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServiceTest {
    @Mock //Tạo một mock implementation for Repository
    private AccountRepository accountRepository;

    @InjectMocks //Inject đối tượng cần test (có sử dụng mock)
    private AccountService accountService;
    @Test
    public void should_return_account_does_not_exist_with_invalid_account()
    {
        //Arrange
        String accountNumber = "0541001456123";
        String expected = "Tài khoản không hợp lệ.";

        //Setup mock
        when(accountRepository.getAccountByNumber(accountNumber)).thenReturn(null);

        //Act
        String result = accountService.withDrawMoney(accountNumber, 100000l);

        //Assert
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void should_return_money_is_not_enough_message()
    {
        String accountNumber = "0541001456123";
        Long amount = 500000l;
        String expected = "Số dư không đủ để thực hiện.";

        //Create account to mock
        Account fakeAcc = Account.builder()
                .name("My Account")
                .number(accountNumber)
                .money(200000l).build();

        when(accountRepository.getAccountByNumber(accountNumber)).thenReturn(fakeAcc);

        //Act
        String result = accountService.withDrawMoney(accountNumber, amount);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void should_return_successful_transaction_with_draw()
    {
        String accountNumber = "0541001456123";
        Long amount = 500000l;
        String expected = "Giao dịch thành công.";

        //Create account to mock
        Account fakeAcc = Account.builder()
                .name("My Account")
                .number(accountNumber)
                .money(2000000l).build();

        when(accountRepository.getAccountByNumber(accountNumber)).thenReturn(fakeAcc);
        when(accountRepository.updateAccount(fakeAcc)).thenReturn(true);
        //Act
        String result = accountService.withDrawMoney(accountNumber, amount);

        //Assert
        assertThat(result).isEqualTo(expected);
        assertThat(fakeAcc.getMoney()).isEqualTo(1500000l);
    }
}

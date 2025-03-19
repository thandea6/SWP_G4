/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.util.List;
import model.Account;

/**
 *
 * @author admin
 */
public class UpdateOldPasswords {
      public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();

        
        List<Account> accounts = accountDAO.getAllAccounts();
        
//        Account acc = accountDAO.getAccountByAID(11);
//         String hashedPassword = Mahoa.toSHA1(acc.getPassword());
//          accountDAO.updatePassword(acc.getAccountId(), hashedPassword);
        
        for (Account account : accounts) {
            String hashedPassword = Mahoa.toSHA1(account.getPassword());
            accountDAO.updatePassword(account.getAccountId(), hashedPassword);
        }
        
        // NHỚ LÀ RUN FILE 1 LẦN DUY NHẤT THÔI NHÁ
        // RUN LẦN 2 LÀ MẤT PASS HẾT ĐẤY CÁC VUA
    }
}

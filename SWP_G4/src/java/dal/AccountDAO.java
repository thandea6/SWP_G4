/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VIET HOANG
 */
public class AccountDAO extends DBContext {

    public Account Login(String username, String pass) {
        String sql = "SELECT * FROM Account WHERE username = ? AND password = ?";
        String xUsername, xPass;
        int xId, xRole;
        String xEmail, xPhone;
        Account x = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                xId = rs.getInt("accountId");
                xUsername = rs.getString("username");
                xPass = rs.getString("password");
                xRole = rs.getInt("roleId");
                xEmail = rs.getString("email");
                xPhone = rs.getString("phone");
                x = new Account(xId, username, pass, xRole, xEmail, xPhone);
                System.out.println("successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public Account getAccountByAID(int id) {
        String sql = "SELECT * FROM Account WHERE AccountId = ?";
        String xUsername, xPass;
        int xId, xRole;
        String xEmail, xPhone;
        Account x = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                xId = rs.getInt("accountId");
                xUsername = rs.getString("username");
                xPass = rs.getString("password");
                xRole = rs.getInt("roleId");
                xEmail = rs.getString("email");
                xPhone = rs.getString("phone");
                x = new Account(xId, xUsername, xPass, xRole, xEmail, xPhone);
                System.out.println("successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public void changePassword(Account a) {
        String sql = "update account\n"
                + "set password = ?\n"
                + "where username =?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getUsername());

            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public Account getEmail(String email) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        Account account = null;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    account = new Account();
                    account.setAccountId(rs.getInt("accountId"));
                    account.setUsername(rs.getString("username"));
                    account.setEmail(rs.getString("email"));
                    account.setPassword(rs.getString("password"));
                    account.setRoleId(rs.getInt("roleId"));
                    account.setCreated_at(rs.getDate("created_at"));
                    account.setUpdated_at(rs.getDate("updated_at"));
                    account.setIs_deleted(rs.getBoolean("is_deleted"));
                    account.setPhone(rs.getString("phone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public Account getPassWord(String password) {
        String sql = "SELECT * FROM Account WHERE password = ?";
        Account c = null; // Initialize as null
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, password); // Set the email parameter
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) { // Use if instead of while
                    c = new Account(); // Instantiate Account object only if result is found
                    c.setAccountId(rs.getInt("accountId"));
                    c.setUsername(rs.getString("username"));
                    c.setPassword(rs.getString("password"));
                    c.setEmail(rs.getString("email"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public void ChangePassByGetEmail(Account a) {
        String sql = "UPDATE [dbo].[account]\n"
                + "   SET [password] = ?\n"
                + " WHERE email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public void updateEmail(int id, String email) {
        String sql = "UPDATE [dbo].[account]\n"
                + "   SET [email] = ?\n"
                + " WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updatePhone(int id, String phone) {
        String sql = "UPDATE [dbo].[account]\n"
                + "   SET [phone] = ?\n"
                + " WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    //  Duc
    public Account isExistAccount(String username, String email, String phone) {
        String sql = "SELECT * FROM Account WHERE username = ? or email =? or phone=?";
        String xUsername, xPass;
        int xId, xRole;
        Account x = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                xId = rs.getInt("accountId");
                xUsername = rs.getString("username");
                xPass = rs.getString("password");
                xRole = rs.getInt("roleId");                
                x = new Account(xId, username, xPass, xRole,rs.getString("email"),rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public int add(Account a) {
        String sql = "insert into [Account] (username, password, roleId, phone, email) values (? , ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setInt(3, a.getRoleId());
            st.setString(4, a.getPhone());
            st.setString(5, a.getEmail());
            int result = st.executeUpdate();
            if(result >= 1) {
                try {
                    ResultSet gene = st.getGeneratedKeys();
                    if(gene.next()) {
                        return gene.getInt(1);
                    }
                }catch(Exception e) {
                    System.err.println("Error: " + e);
                }
            }
        }catch(Exception e) {
            System.out.println("Add new : " + e);
        }
        return 0;
    }
    
    public int addAccount(Account a) {
        String sql = "insert into [Account] (username, password, roleId, email, phone) values (? ,?,?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setInt(3, a.getRoleId());
            st.setString(4, a.getEmail());
            st.setString(5, a.getPhone());
            int result = st.executeUpdate();
            if(result >= 1) {
                try {
                    ResultSet gene = st.getGeneratedKeys();
                    if(gene.next()) {
                        return gene.getInt(1);
                    }
                }catch(Exception e) {
                    System.err.println("Error: " + e);
                }
            }
        }catch(Exception e) {
            System.out.println("Add new : " + e);
        }
        return 0;
    }
    
    public List<Account> getAccountByRole(int role) {
        String sql = "SELECT * FROM Account WHERE roleId =?";
        List<Account> accounts = new ArrayList<>();
        int xId, xRole;
        Account x = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("accountId");
                String xUsername = rs.getString("username");
                String xPass = rs.getString("password");
                xRole = rs.getInt("roleId");
                x = new Account(xId, xUsername, xPass, xRole);
                accounts.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
         public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT accountId, username, password, roleId, email, phone FROM Account";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int accountId = resultSet.getInt("accountId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("roleId");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                accounts.add(new Account(accountId, username, password, roleId, email, phone));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    public void updatePassword(int accountId, String hashedPassword) {
        String sql = "UPDATE Account SET password = ? WHERE accountId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hashedPassword);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
//        Account c = a.getEmail("nangthocon123@gmail.com");
//        a.ChangePassByGetEmail(c);
//        System.out.println(c);
        a.updatePhone(3, "099123456");

    }

}

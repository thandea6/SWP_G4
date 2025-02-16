/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

public class Account {
    /*
    [accountId] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](255) NULL,
	[password] [nvarchar](255) NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
	[is_deleted] [bit] NULL,
	[roleId] [int] NULL,
    */
    private int accountId;
    private String username;
    private String password;
    private Date created_at;
    private Date updated_at;
    private boolean is_deleted;
    private int roleId;
    private String phone;
    private String email;
    public Account() {
    }

    public Account(int accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }

    public Account(int accountId, String username, String password, int roleId) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
    
    public Account(int accountId, String username, String password, int roleId, String email, String phone) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.email = email;
        this.phone = phone;
    }

    public Account(int accountId, String username, String password, Date created_at, Date updated_at, boolean is_deleted, int roleId, String phone, String email) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_deleted = is_deleted;
        this.roleId = roleId;
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", username=" + username + ", password=" + password + ", created_at=" + created_at + ", updated_at=" + updated_at + ", is_deleted=" + is_deleted + ", roleId=" + roleId + ", phone=" + phone + ", email=" + email + '}';
    }
    
}

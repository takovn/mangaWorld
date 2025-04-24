package model;

import java.util.List;

public class Account {

    private int uid;
    private String user;
    private String pass;
    private String email;
    private String accountType;
    private List<Integer> follow;
    private List<Integer> owner;

    public Account() {
    }

    public Account(int uid, String user, String pass, String email, String accountType, List<Integer> follow, List<Integer> owner) {
        this.uid = uid;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.accountType = accountType;
        this.follow = follow;
        this.owner = owner;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Integer> getFollow() {
        return follow;
    }

    public void setFollow(List<Integer> follow) {
        this.follow = follow;
    }

    public List<Integer> getOwner() {
        return owner;
    }

    public void setOwner(List<Integer> owner) {
        this.owner = owner;
    }
    
}
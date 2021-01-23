package com.placetalkr.prototype.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * User model for Spring authentication and authorization
 */
public class User implements UserDetails {
    private String id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User(String id, String username, String password, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static final class UserBuilder{
        private String id;
        private String username;
        private String password;
        private String firstname;
        private String lastname;

        public User.UserBuilder id(String id){
            this.id = id;
            return this;
        }

        public User.UserBuilder username(String username){
            this.username = username;
            return this;
        }

        public User.UserBuilder password(String password){
            this.password = password;
            return this;
        }

        public User.UserBuilder firstname(String firstname){
            this.firstname = firstname;
            return this;
        }

        public User.UserBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public User build(){
            return new User(this.id, this.username, this.password, this.firstname, this.lastname);
        }
    }


}

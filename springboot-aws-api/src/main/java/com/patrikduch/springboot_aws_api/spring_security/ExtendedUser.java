package com.patrikduch.springboot_aws_api.spring_security;

import com.patrikduch.springboot_aws_api.utils.UserAuthorityUtil;
import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * A {@link ExtendedUser} custom Spring Security user model.
 * @author Patrik Duch
 */
@Getter
@Setter
public class ExtendedUser extends User implements UserDetails {

    public ExtendedUser(UserModel userModel) {
        super(
                userModel.getUsername(),
                userModel.getPassword(),
                !userModel.isDisabled(),
                true,
                true,
                true,
                UserAuthorityUtil.createAuthorities(userModel));
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
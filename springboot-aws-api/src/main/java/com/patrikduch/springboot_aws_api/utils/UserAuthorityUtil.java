package com.patrikduch.springboot_aws_api.utils;

import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A utility class used for creating the {@link GrantedAuthority}'s given a {@link UserModel}. In a real solution
 * this would be looked up in the existing system, but for simplicity our original system had no notion of authorities.
 * @author Patrik Duch
 *
 */
public class UserAuthorityUtil {

    /**
     * Create authorities for particular user.
     * @return Collection of role authorities.
     */
    public static Collection<GrantedAuthority> createAuthorities(final @NotNull UserModel user) {
        String username = user.getEmail();

        // Fetch role names...
        ArrayList<String> roleNames = new ArrayList<>();

        user.getRoles().forEach(roleName -> {
            roleNames.add("ROLE_"+ roleName.toString().toUpperCase());
        });

        // Generated role authorities
        String[] generatedRoles = StringConversionUtil.GetStringArray(roleNames);

        return AuthorityUtils.createAuthorityList(generatedRoles);
    }
}
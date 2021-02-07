package com.patrikduch.springboot_aws_api_core.models.user_management;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.patrikduch.springboot_aws_api_core.enums.RoleEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;
import java.util.Set;

/**
 * Model for managing all users of the system.
 * @author Patrik Duch
 */
@Document(collection="User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @Id
    private Long id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String firstName;

    @Indexed(unique = true)
    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String password;
    private Set<RoleEnum> roles = new HashSet<>();
    private boolean isDisabled;

    @PersistenceConstructor
    public UserModel(Long id, String username, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isDisabled = false;
    }

    @JsonIgnore
    @Transient
    public String getName() {
        return getEmail();
    }

    @JsonIgnore
    @Transient
    public void addRole(RoleEnum role){
        this.roles.add(role);
    }

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
}
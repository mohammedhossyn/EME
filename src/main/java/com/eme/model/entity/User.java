package com.eme.model.entity;

import com.eme.model.entity.enums.Role;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Named
@Dependent
@Entity(name = "userEntity")
@Table(name = "user_table")
@NamedQueries({
        @NamedQuery(name = "user.validate",
                query = "SELECT u FROM userEntity u WHERE u.username = :username AND u.password = :password AND u.status = :status AND u.role = :role")
})
public class User extends Base {

    @Id
    @JsonbProperty("نام کاربری")
    @Column(name = "username", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", message = "UserUsername_Error")
    private String username;

    @JsonbTransient
    @JsonbProperty("رمز عبور")
    @Column(name = "password", columnDefinition = "NVARCHAR2(10)")
    @Pattern(regexp = "^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*()\\-_+.])+).{8,10}$", message = "UserPassword_Error")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @JsonbProperty("نقش")
    private Role role;

}

package ru.kata.spring.boot_security.demo.model;

import javax.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="roles")
public class Role implements GrantedAuthority {

    @Id
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString(){
        return String.join(" ",name).replace("ROLE_", "");
    }
}

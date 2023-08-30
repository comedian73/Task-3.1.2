package ru.kata.spring.boot_security.demo.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
   }
)

public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Email(regexp = ".+@.+\\..+|")
   @NotEmpty(message = "Не может быть пустым")
   @Column(name = "email")
   private String email;

   @NotEmpty(message = "Не может быть пустым")
   @Size(min = 3, message = "Не менее 3-х символов")
   @Column(name = "username")
   private String username;

   @NotEmpty(message = "Не может быть пустым")
   @Size(min = 4, message = "Не менее 4-х сомволов")
   @Column(name = "password")
   private String password;

   @Transient
   private String pasConfirm;
   @ManyToMany(fetch = FetchType.EAGER)
   private Set<Role> roles;

   public User() {}

   public User(String username, String firstName, String lastName, String email, String password, String pasConfirm) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.pasConfirm = pasConfirm;
   }
   public User(String username, String firstName, String lastName, String email, String password, Set<Role> roles) {
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.roles = roles;
   }

   public User(String username, String email, String password, String pasConfirm) {
      this.username = username;
      this.email = email;
      this.password = password;
      this.pasConfirm = pasConfirm;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   public void setPasConfirm(String pasConfirm) {
      this.pasConfirm = pasConfirm;
   }

   public String getPasConfirm() {
      return pasConfirm;
   }

   public Set<Role> getRoles() {
      return roles;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return username;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
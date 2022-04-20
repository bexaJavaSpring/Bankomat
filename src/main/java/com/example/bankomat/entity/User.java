package com.example.bankomat.entity;

import com.example.bankomat.entity.ebuns.PermissionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String phone;
    @Column(updatable = false, nullable = false)
    private Timestamp createdAt;
    @Column(nullable = false)
    private Timestamp updateAt;
    @ManyToMany
    private Set<Role> roles;

    private boolean active;
    public User(String fullName, Set<Role> roles, String username, String password, boolean enabled) {
        this.fullName = fullName;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : this.roles) {
            Set<PermissionEnum> permissionEnumSet1 = role.getPermissionEnumSet();
            for (PermissionEnum permissionEnum : permissionEnumSet1) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permissionEnum.name()));
            }
        }
        System.out.println(grantedAuthorities);
        return grantedAuthorities;
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}

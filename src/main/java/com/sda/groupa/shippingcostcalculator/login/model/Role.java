package com.sda.groupa.shippingcostcalculator.login.model;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "roleSeq")
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserAuthority userAuthority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(UserAuthority userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(userAuthority, role.userAuthority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userAuthority);
    }

    @Override
    public String getAuthority() {
        return userAuthority.name();
    }
}

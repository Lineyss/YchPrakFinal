package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "user_spring")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Login;
    @Size(min = 3)
    private String Password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Role Role;

    @ManyToMany(mappedBy = "Users")
    private Set<Group> Groups;

    public User() {}

    public User(String Login, String Password, Role Role)
    {
        this.Role = Role;
        this.Login = Login;
        this.Password = Password;
    }

    public User(Long ID, String Login, String Password, Role Role)
    {
        this.ID = ID;
        this.Role = Role;
        this.Login = Login;
        this.Password = Password;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public @Size(min = 3) String getLogin() {
        return Login;
    }

    public void setLogin(@Size(min = 3) String login) {
        Login = login;
    }

    public @Size(min = 3) String getPassword() {
        return Password;
    }

    public void setPassword(@Size(min = 3) String password) {
        Password = password;
    }

    public com.back.Crystal.Model.Entity.Role getRole() {
        return Role;
    }

    public void setRole(com.back.Crystal.Model.Entity.Role role) {
        Role = role;
    }

    public Set<Group> getGroups() {
        return Groups;
    }

    public void setGroups(Set<Group> groups) {
        Groups = groups;
    }
}

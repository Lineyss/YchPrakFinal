package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Title;

    @OneToMany(mappedBy = "Role", cascade = CascadeType.ALL)
    private Set<User> Users;

    public Role() {}

    public Role(String Title)
    {
        this.Title = Title;
    }

    public Role(String Title, Set<User> Users)
    {
        this.Title = Title;
        this.Users = Users;
    }

    public Role(Long ID, String Title, Set<User> Users)
    {
        this.ID = ID;
        this.Title = Title;
        this.Users = Users;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }
}

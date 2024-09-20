package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "group_spring")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Title;

    private int NumberConnected = 0;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "id_group_spring"),
            inverseJoinColumns = @JoinColumn(name = "id_user_spring")
    )
    private Set<User> Users;

    @OneToMany(mappedBy = "Group", cascade = CascadeType.ALL)
    private Set<Host> Hosts;

    public Group() {}

    public Group(String Title)
    {
        this.Title = Title;
    }

    public Group(Long ID, String Title)
    {
        this.ID = ID;
        this.Title = Title;
    }

    public Group(String Title, Set<User> Users, Set<Host> Hosts)
    {
        this.Title = Title;
        this.Users = Users;
        this.Hosts = Hosts;
    }

    public Group(Long ID, String Title, Set<User> Users, Set<Host> Hosts)
    {
        this.ID = ID;
        this.Title = Title;
        this.Users = Users;
        this.Hosts = Hosts;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public @Size(min = 3) String getTitle() {
        return Title;
    }

    public void setTitle(@Size(min = 3) String title) {
        Title = title;
    }

    public int getNumberConnected() {
        return NumberConnected;
    }

    public void setNumberConnected(int numberConnected) {
        NumberConnected = Hosts.size();
    }

    public Set<User> getUsers() {
        return Users;
    }

    public void setUsers(Set<User> users) {
        Users = users;
    }

    public Set<Host> getHosts() {
        return Hosts;
    }

    public void setHosts(Set<Host> hosts) {
        Hosts = hosts;
    }
}

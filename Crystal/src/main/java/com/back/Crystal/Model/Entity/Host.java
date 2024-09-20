package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "host")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Hostname;

    @ManyToOne
    @JoinColumn(name = "group_spring_id", nullable = true)
    private Group Group;

    @ManyToMany
    @JoinTable(
            name = "host_software",
            joinColumns = @JoinColumn(name = "id_host"),
            inverseJoinColumns = @JoinColumn(name = "id_software")
    )
    private Set<Software> Softwares;

    public Host() {}

    public Host(String HostName)
    {
        this.Hostname = HostName;
    }

    public Host(String Hostname, Group Group)
    {
        this.Group = Group;
        this.Hostname = Hostname;
    }

    public Host(String Hostname, Group Group, Set<Software> Softwares)
    {
        this.Group = Group;
        this.Hostname = Hostname;
        this.Softwares = Softwares;
    }

    public Host(Long ID, String Hostname, Group Group, Set<Software> Softwares)
    {
        this.ID = ID;
        this.Group = Group;
        this.Hostname = Hostname;
        this.Softwares = Softwares;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public @Size(min = 3) String getHostname() {
        return Hostname;
    }

    public void setHostname(@Size(min = 3) String hostname) {
        Hostname = hostname;
    }

    public com.back.Crystal.Model.Entity.Group getGroup() {
        return Group;
    }

    public void setGroup(com.back.Crystal.Model.Entity.Group group) {
        Group = group;
    }

    public Set<Software> getSoftwares() {
        return Softwares;
    }

    public void setSoftwares(Set<Software> softwares) {
        Softwares = softwares;
    }
}

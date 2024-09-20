package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "software")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Title;

    private String Description;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = true)
    private Publisher Publisher;

    @ManyToMany(mappedBy = "Softwares", cascade = CascadeType.ALL)
    private Set<Host> Hosts;

    public Software() {}

    public Software(String Title, String Description)
    {
        this.Title = Title;
        this.Description = Description;
    }

    public Software(String Title, @Nullable String Description, Publisher Publisher)
    {
        this.Title = Title;
        this.Description = Description;
        this.Publisher = Publisher;
    }

    public Software(Long ID, String Title, @Nullable String Description, Publisher Publisher, Set<Host> Hosts)
    {
        this.ID = ID;
        this.Title = Title;
        this.Hosts = Hosts;
        this.Publisher = Publisher;
        this.Description = Description;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public com.back.Crystal.Model.Entity.Publisher getPublisher() {
        return Publisher;
    }

    public void setPublisher(com.back.Crystal.Model.Entity.Publisher publisher) {
        Publisher = publisher;
    }

    public Set<Host> getHosts() {
        return Hosts;
    }

    public void setHosts(Set<Host> hosts) {
        Hosts = hosts;
    }
}

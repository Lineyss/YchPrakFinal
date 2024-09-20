package com.back.Crystal.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "publisher")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Size(min = 3)
    private String Title;

    @OneToMany(mappedBy = "Publisher", cascade = CascadeType.ALL)
    private Set<Software> Softwares;

    public Publisher() {}

    public Publisher(String Title)
    {
        this.Title = Title;
    }

    public Publisher(String Title, Set<Software> Softwares)
    {
        this.Title = Title;
        this.Softwares = Softwares;
    }

    public Publisher(Long ID, String Title, Set<Software> Softwares)
    {
        this.ID = ID;
        this.Title = Title;
        this.Softwares = Softwares;
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

    public Set<Software> getSoftwares() {
        return Softwares;
    }

    public void setSoftwares(Set<Software> softwares) {
        Softwares = softwares;
    }
}

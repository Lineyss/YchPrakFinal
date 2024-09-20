package com.back.Crystal.DTO;

public class SoftwareCreateDTO {

    private String Title;
    private String Description;
    private Long IDPublisher;

    public SoftwareCreateDTO(String title, String description, Long IDPublisher) {
        Title = title;
        Description = description;
        this.IDPublisher = IDPublisher;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getIDPublisher() {
        return IDPublisher;
    }

    public void setIDPublisher(Long IDPublisher) {
        this.IDPublisher = IDPublisher;
    }
}

package com.back.Crystal.DTO;

public class PublisherCreateDTO {
    private String Title;

    public PublisherCreateDTO(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

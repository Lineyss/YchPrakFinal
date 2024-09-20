package com.back.Crystal.DTO;

public class RoleCreateDTO {
    private String Title;

    public RoleCreateDTO(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}

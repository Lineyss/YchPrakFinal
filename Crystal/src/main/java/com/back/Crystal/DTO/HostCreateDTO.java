package com.back.Crystal.DTO;

public class HostCreateDTO {
    private Long IDGroup;
    private String Hostname;

    public HostCreateDTO(Long idGroup, String hostname) {
        IDGroup = idGroup;
        Hostname = hostname;
    }

    public String getHostname() {
        return Hostname;
    }

    public void setHostname(String hostname) {
        Hostname = hostname;
    }

    public Long getIDGroup() {
        return IDGroup;
    }

    public void setIDGroup(Long IDGroup) {
        this.IDGroup = IDGroup;
    }
}

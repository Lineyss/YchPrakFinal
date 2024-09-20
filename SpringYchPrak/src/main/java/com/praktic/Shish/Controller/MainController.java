package com.praktic.Shish.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main(Model model)
    {
        return "admin-main.html";
    }

    @GetMapping("/roles")
    public String Roles(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/role");
        model.addAttribute("js", "./models/roles.js");
        return "admin-main.html";
    }

    @GetMapping("/publishers")
    public String Publishers(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/publisher");
        model.addAttribute("js", "./models/publishers.js");
        return "admin-main.html";
    }

    @GetMapping("/users")
    public String Users(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/user");
        model.addAttribute("js", "./models/users.js");
        return "admin-main.html";
    }

    @GetMapping("/softwares")
    public String Softwares(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/software");
        model.addAttribute("js", "./models/softwares.js");
        return "admin-main.html";
    }

    @GetMapping("/hosts")
    public String Hosts(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/host");
        model.addAttribute("js", "./models/hosts.js");
        return "admin-main.html";
    }

    @GetMapping("/groups")
    public String Groups(Model model)
    {
        model.addAttribute("apiUrl", "http://localhost:8000/api/group");
        model.addAttribute("js", "./models/groups.js");
        return "admin-main.html";
    }
}

package com.back.Crystal.Controller.API;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.GroupCreateDTO;
import com.back.Crystal.Model.Entity.Group;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private AService<Group, GroupCreateDTO> service;

    @GetMapping("/groups")
    public ResponseEntity<?> GetAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page)
    {
        Result<ArrayList<Group>> result = service.GetAll();
        if(!result.IsError())
        {
            ArrayList<Group> users = result.getModel();
            return ResponseEntity.ok(new Pagination<>(users, page));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<Group> role = service.GetByID(id);

        if(!role.IsError())
        {
            Group model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<Group> role = service.Delete(id);

        if(!role.IsError())
        {
            Group model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/group")
    public ResponseEntity<?> Create(GroupCreateDTO roleCreate)
    {
        Result<Group> user = service.Create(roleCreate);

        if(!user.IsError())
        {
            Group model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    GroupCreateDTO roleCreate)
    {
        Result<Group> user = service.Update(id, roleCreate);

        if(!user.IsError())
        {
            Group model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }
}

package com.back.Crystal.Controller.API;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.UserCreateDTO;
import com.back.Crystal.Model.Entity.User;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class UserController{

    @Autowired
    private AService<User, UserCreateDTO> service;

    @GetMapping("/users")
    public ResponseEntity<?> GetAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page)
    {
        Result<ArrayList<User>> result = service.GetAll();
        if(!result.IsError())
        {
            ArrayList<User> users = result.getModel();
            return ResponseEntity.ok(new Pagination<>(users, page));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<User> role = service.GetByID(id);

        if(!role.IsError())
        {
            User model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<User> role = service.Delete(id);

        if(!role.IsError())
        {
            User model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/user")
    public ResponseEntity<?> Create(UserCreateDTO roleCreate)
    {
        Result<User> user = service.Create(roleCreate);

        if(!user.IsError())
        {
            User model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    UserCreateDTO roleCreate)
    {
        Result<User> user = service.Update(id, roleCreate);

        if(!user.IsError())
        {
            User model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }
}

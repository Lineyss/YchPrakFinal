package com.back.Crystal.Controller.API;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.SoftwareCreateDTO;
import com.back.Crystal.Model.Entity.Software;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class SoftwareController {

    @Autowired
    private AService<Software, SoftwareCreateDTO> service;

    @GetMapping("/softwares")
    public ResponseEntity<?> GetAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page)
    {
        Result<ArrayList<Software>> result = service.GetAll();
        if(!result.IsError())
        {
            ArrayList<Software> users = result.getModel();
            return ResponseEntity.ok(new Pagination<>(users, page));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
    }

    @GetMapping("/software/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<Software> role = service.GetByID(id);

        if(!role.IsError())
        {
            Software model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/software/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<Software> role = service.Delete(id);

        if(!role.IsError())
        {
            Software model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/software")
    public ResponseEntity<?> Create(SoftwareCreateDTO roleCreate)
    {
        Result<Software> user = service.Create(roleCreate);

        if(!user.IsError())
        {
            Software model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }

    @PutMapping("/software/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    SoftwareCreateDTO roleCreate)
    {
        Result<Software> user = service.Update(id, roleCreate);

        if(!user.IsError())
        {
            Software model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }
}

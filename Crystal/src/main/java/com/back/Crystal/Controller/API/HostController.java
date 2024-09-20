package com.back.Crystal.Controller.API;


import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.HostCreateDTO;
import com.back.Crystal.Model.Entity.Host;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class HostController {

    @Autowired
    private AService<Host, HostCreateDTO> service;

    @GetMapping("/hosts")
    public ResponseEntity<?> GetAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page)
    {
        Result<ArrayList<Host>> result = service.GetAll();
        if(!result.IsError())
        {
            ArrayList<Host> users = result.getModel();
            return ResponseEntity.ok(new Pagination<>(users, page));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
    }

    @GetMapping("/host/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<Host> role = service.GetByID(id);

        if(!role.IsError())
        {
            Host model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/host/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<Host> role = service.Delete(id);

        if(!role.IsError())
        {
            Host model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/host")
    public ResponseEntity<?> Create(HostCreateDTO roleCreate)
    {
        Result<Host> user = service.Create(roleCreate);

        if(!user.IsError())
        {
            Host model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }

    @PutMapping("/host/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    HostCreateDTO roleCreate)
    {
        Result<Host> user = service.Update(id, roleCreate);

        if(!user.IsError())
        {
            Host model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }
}

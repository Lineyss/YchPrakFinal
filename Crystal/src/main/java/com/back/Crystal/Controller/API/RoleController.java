package com.back.Crystal.Controller.API;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.RoleCreateDTO;
import com.back.Crystal.Model.Entity.Role;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private AService<Role, RoleCreateDTO> service;

    @GetMapping("/roles")
    @CrossOrigin(origins = "http://localhost:8001")
    public ResponseEntity<?> GetAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page)
    {
         Result<ArrayList<Role>> roles = service.GetAll();

         if(!roles.IsError())
         {
             ArrayList<Role> models = roles.getModel();
             return ResponseEntity.ok(new Pagination<>(models, page));
         }

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roles.getError());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<Role> role = service.GetByID(id);

        if(!role.IsError())
        {
            Role model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<Role> role = service.Delete(id);

        if(!role.IsError())
        {
            Role model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/role")
    public ResponseEntity<?> Create(RoleCreateDTO roleCreate)
    {
        Result<Role> role = service.Create(roleCreate);

        if(!role.IsError())
        {
            Role model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    RoleCreateDTO roleCreate)
    {
        Result<Role> role = service.Update(id, roleCreate);

        if(!role.IsError())
        {
            Role model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }
}

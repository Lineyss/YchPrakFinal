package com.back.Crystal.Controller.API;

import com.back.Crystal.Abstracts.AService;
import com.back.Crystal.DTO.PublisherCreateDTO;
import com.back.Crystal.Model.Entity.Publisher;
import com.back.Crystal.Model.Help.Pagination;
import com.back.Crystal.Model.Help.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class PublisherController {

    @Autowired
    private AService<Publisher, PublisherCreateDTO> service;

    @GetMapping("/publishers")
    public ResponseEntity<?> GetAll(@RequestParam(defaultValue = "1", name = "page", required = false) int page)
    {
        Result<ArrayList<Publisher>> result = service.GetAll();
        if(!result.IsError())
        {
            ArrayList<Publisher> publishers = result.getModel();
            return ResponseEntity.ok(new Pagination<>(publishers, page));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getError());
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<?> GetByID(@PathVariable Long id)
    {
        Result<Publisher> role = service.GetByID(id);

        if(!role.IsError())
        {
            Publisher model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @DeleteMapping("/publisher/{id}")
    public ResponseEntity<?> Delete(@PathVariable Long id)
    {
        Result<Publisher> role = service.Delete(id);

        if(!role.IsError())
        {
            Publisher model = role.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(role.getError());
    }

    @PostMapping("/publisher")
    public ResponseEntity<?> Create(PublisherCreateDTO roleCreate)
    {
        Result<Publisher> user = service.Create(roleCreate);

        if(!user.IsError())
        {
            Publisher model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }

    @PutMapping("/publisher/{id}")
    public ResponseEntity<?> Update(@PathVariable Long id,
                                    PublisherCreateDTO roleCreate)
    {
        Result<Publisher> user = service.Update(id, roleCreate);

        if(!user.IsError())
        {
            Publisher model = user.getModel();
            return ResponseEntity.ok(model);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user.getError());
    }
}

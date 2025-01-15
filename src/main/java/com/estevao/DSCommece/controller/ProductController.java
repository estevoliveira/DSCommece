package com.estevao.DSCommece.controller;

import com.estevao.DSCommece.dto.CustomErrorDTO;
import com.estevao.DSCommece.dto.ProductDTO;
import com.estevao.DSCommece.services.ProductService;
import com.estevao.DSCommece.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> buscarProdutoId(@PathVariable Long id){
        ProductDTO p =service.findById(id);
        return ResponseEntity.ok(p);
    }
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> searchPage(Pageable pageable){
        Page<ProductDTO> page =service.findAll(pageable);
        return ResponseEntity.ok(page);

        /** try {
            Page<ProductDTO> page = service.findAll(pageable);
            return ResponseEntity.ok(page);
        }catch (ResourceNotFoundException e){
            CustomErrorDTO err = new CustomErrorDTO(Instant.now(),404,e.getMessage(),"teste");
            return ResponseEntity.status(404).body(err);
        } **/
    }
    @PostMapping
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO p){
        ProductDTO dto = service.insert(p);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,@RequestBody ProductDTO p){
        ProductDTO dto = service.update(id,p);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

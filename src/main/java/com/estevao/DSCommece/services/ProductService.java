package com.estevao.DSCommece.services;

import com.estevao.DSCommece.dto.ProductDTO;
import com.estevao.DSCommece.entities.Product;
import com.estevao.DSCommece.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        return new ProductDTO(repository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(ProductDTO::new);
         //new ProductDTO(repository.findById(id).get());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setUmgUrl(dto.getImgUrl());

        p = repository.save(p);
        return new ProductDTO(p);
    }
}

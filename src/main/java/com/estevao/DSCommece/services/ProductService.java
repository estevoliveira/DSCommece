package com.estevao.DSCommece.services;

import com.estevao.DSCommece.dto.ProductDTO;
import com.estevao.DSCommece.entities.Product;
import com.estevao.DSCommece.repositorys.ProductRepository;
import com.estevao.DSCommece.services.exceptions.ResourceNotFoundException;
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
        return new ProductDTO(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found")));
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(ProductDTO::new);
         //new ProductDTO(repository.findById(id).get());
    }
    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product p = new Product();
        dtoToProduct(dto,p);
        p = repository.save(p);
        return new ProductDTO(p);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        Product p = repository.getReferenceById(id);
        dtoToProduct(dto,p);
        p = repository.save(p);
        return new ProductDTO(p);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void dtoToProduct(ProductDTO dto, Product p) {
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setUmgUrl(dto.getImgUrl());
    }
}

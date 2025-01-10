package com.estevao.DSCommece.repositorys;

import com.estevao.DSCommece.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

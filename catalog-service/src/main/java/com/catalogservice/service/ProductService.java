package com.catalogservice.service;

import java.util.List;

import com.catalogservice.dto.ProductDTO;
import com.catalogservice.dto.ProductUpdateDTO;
import com.catalogservice.entity.Product;

public interface ProductService {

    ProductDTO createProduct(Product product);

    ProductDTO updateProduct(Long id, ProductUpdateDTO dto);

    void deleteProduct(Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> searchProductsByName(String name);

}

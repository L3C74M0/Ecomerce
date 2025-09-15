package com.catalogservice.service;

import java.util.List;

import com.catalogservice.dto.ProductCreateDTO;
import com.catalogservice.dto.ProductDTO;
import com.catalogservice.dto.ProductUpdateDTO;

public interface ProductService {

    ProductDTO createProduct(ProductCreateDTO dto);

    ProductDTO updateProduct(Long id, ProductUpdateDTO dto);

    void deleteProduct(Long id);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    List<ProductDTO> searchProductsByName(String name);

}

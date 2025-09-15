package com.catalogservice.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catalogservice.dto.ProductDTO;
import com.catalogservice.dto.ProductUpdateDTO;
import com.catalogservice.entity.Product;
import com.catalogservice.mapper.ProductMapper;
import com.catalogservice.repository.ProductRepository;
import com.catalogservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public ProductDTO getProductById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProductsByName'");
    }

}

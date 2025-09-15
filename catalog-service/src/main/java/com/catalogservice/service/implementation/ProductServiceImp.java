package com.catalogservice.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.catalogservice.dto.ProductCreateDTO;
import com.catalogservice.dto.ProductDTO;
import com.catalogservice.dto.ProductUpdateDTO;
import com.catalogservice.entity.Product;
import com.catalogservice.exception.InvalidInputException;
import com.catalogservice.exception.ResourceNotFoundException;
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
    public ProductDTO createProduct(ProductCreateDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new InvalidInputException("Product with name " + dto.getName() + " already exists.");
        }

        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductUpdateDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found."));

        productMapper.updateEntityFromDTO(dto, product);

        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with id " + id + " not found.");
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found."));
        
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findAll().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .map(productMapper::toDto)
                .toList();
    }

}

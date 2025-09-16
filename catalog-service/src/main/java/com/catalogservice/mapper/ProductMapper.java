package com.catalogservice.mapper;

import com.catalogservice.dto.ProductCreateDTO;
import com.catalogservice.dto.ProductDTO;
import com.catalogservice.dto.ProductUpdateDTO;
import com.catalogservice.entity.Product;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "status", expression = "java(product.getStatus().name())")
    ProductDTO toDto(Product product);

    @Mapping(target = "status", expression = "java(com.catalogservice.entity.ProductStatus.valueOf(dto.getStatus()))")
    Product toEntity(ProductCreateDTO dto);

    @Mapping(target = "status", expression = "java(com.catalogservice.entity.ProductStatus.valueOf(dto.getStatus()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ProductUpdateDTO dto, @MappingTarget Product entity);

}

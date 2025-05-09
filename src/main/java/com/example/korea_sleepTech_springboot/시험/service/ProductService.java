package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productrepository;

    public ResponseDto<ProductResponseDto> createProduct(ProductCreateRequestDto requestDto) {
        try {
            Product newProduct = new Product(null, requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(),
                            null, null);

            Product savedProduct = productrepository.save(newProduct);

            ProductResponseDto response = new ProductResponseDto(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getDescription(),
                    savedProduct.getPrice()
            );

            return ResponseDto.setSuccess("새 상품 등록 완료!", response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("상품 등록 중 오류 발생" + e.getMessage());
        }
    }

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> responseDto = null;
        try {
            List<Product> products = productrepository.findAll();

            responseDto = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto responseDto = null;
        try {
            Product product = productrepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 없습니다." + id));

            responseDto = new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto dto) {
        ProductResponseDto responseDto = null;
        try {
            Product product = productrepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 id책 찾기 불가" + id));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            Product updateProduct = productrepository.save(product);

            responseDto = new ProductResponseDto(
                    updateProduct.getId(),
                    updateProduct.getName(),
                    updateProduct.getDescription(),
                    updateProduct.getPrice()
            );
        } catch(Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    public void deleteProduct(Long id) {
        try {
            Product product = productrepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디 없음" + id));

            productrepository.delete(product);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

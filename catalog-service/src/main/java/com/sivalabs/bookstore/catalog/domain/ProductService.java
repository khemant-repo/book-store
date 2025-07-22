package com.sivalabs.bookstore.catalog.domain;

import com.sivalabs.bookstore.catalog.ApplicationProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Product service
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository repository;
    private final ApplicationProperties properties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.repository = productRepository;
        this.properties = applicationProperties;
    }

    public PageResult<Product> getproducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1
                ? 0
                : pageNo - 1; // because spring jpa pagination start with 0 and user expect pagination from 1
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<Product> productsPage = repository.findAll(pageable).map(ProductMapper::toProduct);

        return new PageResult<Product>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber()
                        + 1, //// because spring jpa pagination start with 0 and user expect pagination from 1
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }

    public Optional<Product> findByCode(String code) {
        return repository.findByCode(code).map(ProductMapper::toProduct);
    }
}

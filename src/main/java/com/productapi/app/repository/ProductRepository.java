package com.productapi.app.repository;

import com.productapi.app.domain.Product;
import com.productapi.app.domain.enums.ProductStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductId(Long productId);

    Product findByProductIdAndStatusNot(Long product, ProductStatusEnum statusCd);

    @Query(value = "select * from product p " +
            " where (COALESCE(:status) is null or p.status like :status) " +
            " and (COALESCE(:title) is null or p.title like CONCAT('%', :title, '%')) " +
            " order by CASE COALESCE(:sortField,'') WHEN 'status' then p.status else p.title END ", nativeQuery = true)
    List<Product> findProductsByParameters(@Param("status") String status, @Param("title") String title, @Param("sortField") String sortField);


}
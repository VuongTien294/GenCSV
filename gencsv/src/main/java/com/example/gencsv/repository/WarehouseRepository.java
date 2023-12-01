package com.example.gencsv.repository;

import com.example.gencsv.Entity.WareHouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<WareHouseProduct, Long> {

    @Query(value = "select * from warehouse_product wp where wp.number_product_in_warehouse > 0", nativeQuery = true)
    List<WareHouseProduct> listWhereHasProduct();
}

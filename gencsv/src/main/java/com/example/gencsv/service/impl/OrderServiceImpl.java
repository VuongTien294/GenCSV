package com.example.gencsv.service.impl;

import com.example.gencsv.Entity.WareHouseProduct;
import com.example.gencsv.Model.CreateItem;
import com.example.gencsv.repository.OrderRepository;
import com.example.gencsv.repository.ProductRepository;
import com.example.gencsv.repository.WarehouseRepository;
import com.example.gencsv.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final OrderRepository orderRepository;

//    String fileCsvLink = "C:/Users/HLC2023/Desktop/csv/createOrderFake.csv";
    String fileCsvLink = "C:/Users/EdsoLabs/Desktop/FakeData/createOrderFake.csv";

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng bản ghi muốn fake :");
        int slBanGhi = sc.nextInt(); //1
        System.out.println("Nhập Product Id:");
        int productId = sc.nextInt(); //1
        System.out.println("Nhập number Product in Order:");
        int numberProductInOrder = sc.nextInt(); //1
        System.out.println("Nhập số shipping address id :");
        int shippingAddressId = sc.nextInt(); //11 ứng với phonenumber = 0333333334

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCsvLink));
        CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.INFORMIX_UNLOAD_CSV);
        Gson gson = new Gson();

        for (int i = 1; i <= slBanGhi; i++) {
            CreateItem item = CreateItem.builder()
                    .product_id(productId)
                    .number_product_in_order(numberProductInOrder)
                    .build();

            String gsonItem = gson.toJson(item);
            List<String> list = new ArrayList<>();
            list.add(gsonItem);

            csvPrinter.printRecord(productId, numberProductInOrder, shippingAddressId, list);
            System.out.println(i);
        }
        csvPrinter.flush();
    }
}

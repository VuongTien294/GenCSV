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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        int slBanGhi = sc.nextInt();
        System.out.println("Nhập số shipping address id :");
        int shippingAddressId = sc.nextInt();
        System.out.println("Nhập Product Id:");
        int productId = sc.nextInt();
        System.out.println("Nhập number Product in Order:");
        int numberProductInOrder = sc.nextInt();

//        List<WareHouseProduct> listWhereHasProduct = warehouseRepository.listWhereHasProduct();

        int orderId = orderRepository.findMaxId();
        int z = orderId;

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCsvLink));
//        CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.DEFAULT.withHeader("shipping_address_id", "status", "type", "items"));
        CSVPrinter csvPrinter = new CSVPrinter(writer,CSVFormat.INFORMIX_UNLOAD_CSV);
        Gson gson = new Gson();

        for (int i = 0; i < slBanGhi; i++) {
//            WareHouseProduct wareHouseProduct;
//            Random random = new Random();
//            wareHouseProduct = list.get(random.nextInt(list.size()));

            CreateItem item = CreateItem.builder()
                    .product_id(productId)
                    .number_product_in_order(numberProductInOrder)
                    .build();

            String gsonItem = gson.toJson(item);
            List<String> list = new ArrayList<>();
            list.add(gsonItem);
            z++;
            System.out.println(z);

//            csvPrinter.printRecord(shippingAddressId,"waiting_for_pay", "buy_auto", list);
            csvPrinter.printRecord(productId, numberProductInOrder, shippingAddressId, list, z);
        }
        csvPrinter.flush();
    }
}

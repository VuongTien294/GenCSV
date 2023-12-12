package com.example.gencsv.service.impl;

import com.example.gencsv.Model.CreateItem;
import com.example.gencsv.service.UserService;
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
import java.util.Scanner;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements CommandLineRunner {

    //    String fileCsvLink = "C:/Users/HLC2023/Desktop/csv/createUserFake.csv";
    String fileCsvLink = "C:/Users/EdsoLabs/Desktop/FakeData/createUserFake.csv";

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số lượng bản ghi muốn fake :");
        int slBanGhi = sc.nextInt();

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCsvLink));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.INFORMIX_UNLOAD_CSV);
        for (int i = 1; i <= slBanGhi; i++) {
            String phone = "0712" + i;
            String passWord = "123456a@";

            csvPrinter.printRecord(phone, passWord);
            System.out.println(i);
        }
        csvPrinter.flush();
    }
}

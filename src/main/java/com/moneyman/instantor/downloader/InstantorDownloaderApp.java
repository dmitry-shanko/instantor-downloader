package com.moneyman.instantor.downloader;

import com.moneyman.instantor.downloader.service.DownloaderService;
import com.moneyman.instantor.downloader.service.DownloaderServiceImpl;
import com.moneyman.instantor.downloader.service.ExcelReader;
import com.moneyman.instantor.downloader.service.ExcelReaderImpl;

import java.io.File;
import java.util.List;

public class InstantorDownloaderApp {

    public static void main(String[] args) {
        try {
            File xlsx = new File(InstantorDownloaderApp.class.getResource("/instantor_credit_examples.xlsx").toURI());
            ExcelReader excelReader = new ExcelReaderImpl();
            DownloaderService downloaderService = new DownloaderServiceImpl();
            List<Long> instantorUserDetailIds = excelReader.readInstantorUserDetailIdList(xlsx);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

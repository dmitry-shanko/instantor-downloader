package com.moneyman.instantor.downloader;

import com.jcraft.jsch.JSchException;
import com.moneyman.instantor.downloader.net.SSHConnector;
import com.moneyman.instantor.downloader.service.DownloaderService;
import com.moneyman.instantor.downloader.service.DownloaderServiceImpl;
import com.moneyman.instantor.downloader.service.ExcelReader;
import com.moneyman.instantor.downloader.service.ExcelReaderImpl;

import java.io.File;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class InstantorDownloaderApp {

    private static SSHConnector sshConnector;

    public static void main(String[] args) throws JSchException {
        try {
            sshConnector = SSHConnector.builder().build();
            File xlsx = new File(InstantorDownloaderApp.class.getResource("/instantor_credit_examples.xlsx").toURI());
            ExcelReader excelReader = new ExcelReaderImpl();
            DownloaderService downloaderService = new DownloaderServiceImpl();
            List<Pair<Long, String>> instantorUserDetailIds = excelReader.readInstantorUserDetailIdList(xlsx);
            downloaderService.downloadReportsByInstantorUserDetailIds("E://instantor_reports//",
                instantorUserDetailIds);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            sshConnector.finalize();
        }
    }
}

package com.moneyman.instantor.downloader.service;

import com.moneyman.instantor.downloader.repository.MongoRepository;
import com.moneyman.instantor.downloader.repository.MongoRepositoryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DownloaderServiceImpl implements DownloaderService {

    private MongoRepository mongoRepository;

    public DownloaderServiceImpl() {
        this.mongoRepository = MongoRepositoryImpl.getInstance();
    }

    public void downloadReportsByInstantorUserDetailIds(String path, List<Long> instantorUserDetailIds) {
        for (Long instantorUserDetailsId : instantorUserDetailIds) {
            File f = new File(path + "instantor_user_details_" + instantorUserDetailsId + ".txt");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(new byte[11]);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

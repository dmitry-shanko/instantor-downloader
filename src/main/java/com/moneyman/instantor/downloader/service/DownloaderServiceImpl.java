package com.moneyman.instantor.downloader.service;

import com.moneyman.instantor.downloader.repository.MongoRepository;
import com.moneyman.instantor.downloader.repository.MongoRepositoryImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class DownloaderServiceImpl implements DownloaderService {

  private MongoRepository mongoRepository;

  public DownloaderServiceImpl() {
    this.mongoRepository = MongoRepositoryImpl.getInstance();
  }

  public void downloadReportsByInstantorUserDetailIds(String path, List<Pair<Long, String>> instantorUserDetailIds) {
    for (Pair<Long, String> pair : instantorUserDetailIds) {
      Long instantorUserDetailsId = pair.getLeft();
      String fileUid = pair.getRight();
      File f = new File(path + "instantor_user_details_" + instantorUserDetailsId + ".txt");
      try {
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(mongoRepository.downloadInstantorReport(fileUid));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

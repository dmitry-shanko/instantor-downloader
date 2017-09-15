package com.moneyman.instantor.downloader.service;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface DownloaderService {

  void downloadReportsByInstantorUserDetailIds(String path, List<Pair<Long, String>> instantorUserDetailIds);
}

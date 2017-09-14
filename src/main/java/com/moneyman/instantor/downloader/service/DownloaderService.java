package com.moneyman.instantor.downloader.service;

import java.util.List;

public interface DownloaderService {

    void downloadReportsByInstantorUserDetailIds(String path, List<Long> instantorUserDetailIds);
}

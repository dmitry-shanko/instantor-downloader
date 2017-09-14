package com.moneyman.instantor.downloader.service;

import java.util.List;

public interface DownloaderService {

    void downloadReportsByInstantorUserDetailIds(List<Long> instantorUserDetailIds);
}

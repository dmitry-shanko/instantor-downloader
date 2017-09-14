package com.moneyman.instantor.downloader.service;

import java.io.File;
import java.util.List;

public interface ExcelReader {

    List<Long> readInstantorUserDetailIdList(File file);
}

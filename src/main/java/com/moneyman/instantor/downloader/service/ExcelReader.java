package com.moneyman.instantor.downloader.service;

import java.io.File;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public interface ExcelReader {

  List<Pair<Long, String>> readInstantorUserDetailIdList(File file);
}

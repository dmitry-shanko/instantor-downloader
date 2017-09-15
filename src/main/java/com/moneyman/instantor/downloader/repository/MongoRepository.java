package com.moneyman.instantor.downloader.repository;

public interface MongoRepository {

    byte[] downloadInstantorReport(String requestUid);
}

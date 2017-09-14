package com.moneyman.instantor.downloader.repository;

public interface MongoRepository {

    byte[] downloadInstantorReport(Long instantorUserDetailsId);
}

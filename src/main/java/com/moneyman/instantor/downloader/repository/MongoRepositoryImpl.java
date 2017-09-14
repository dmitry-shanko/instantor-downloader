package com.moneyman.instantor.downloader.repository;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class MongoRepositoryImpl implements MongoRepository {

    private static final String HOST = "host";
    private static final Integer PORT = 27015;

    private MongoClient mongoClient;
    private MongoDatabase fileStorage;
    private GridFSBucket gridFSBucket;
    private MongoCollection fileFsCollection;

    private MongoRepositoryImpl() {
        mongoClient = new MongoClient(HOST, PORT);
        fileStorage = mongoClient.getDatabase("file-storage");
        gridFSBucket = GridFSBuckets.create(fileStorage);
        fileFsCollection = fileStorage.getCollection("");
    }

    private void init() {

    }

    public static final MongoRepository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public byte[] downloadInstantorReport(Long instantorUserDetailsId) {
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Object result = fileFsCollection.find(Filters.eq("id", "")).limit(1).first();
            GridFSFile fileResult = gridFSBucket.find(Filters.eq("name", "")).limit(1).first();
            gridFSBucket.downloadToStream(fileResult.getId(), outputStream);
            return outputStream.toByteArray();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class InstanceHolder {

        private static final MongoRepository INSTANCE = new MongoRepositoryImpl();
    }
}

package com.moneyman.instantor.downloader.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bson.Document;

public final class MongoRepositoryImpl implements MongoRepository {

  private static final String HOST = "localhost";
  private static final Integer PORT = 27017;

  private static final int TIMEOUT = 1000 * 60 * 10;

  private MongoClient mongoClient;
  private MongoDatabase fileStorage;
  private GridFSBucket gridFSBucket;
  private MongoCollection fileFsCollection;

  private MongoRepositoryImpl() {
    init();
  }

  private void init() {
    mongoClient = new MongoClient(new ServerAddress(HOST, PORT),
        MongoClientOptions.builder().serverSelectionTimeout(TIMEOUT).connectTimeout(TIMEOUT).socketTimeout(TIMEOUT).build());
    fileStorage = mongoClient.getDatabase("file-storage");
    gridFSBucket = GridFSBuckets.create(fileStorage);
    fileFsCollection = fileStorage.getCollection("fs.files");
  }

  public static final MongoRepository getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public byte[] downloadInstantorReport(String requestUid) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      Document result = (Document) fileFsCollection.find(Filters
          .and(Filters.eq("metadata.sourceSystem", "INSTANTOR"), Filters.eq("metadata.marker.requestUid", requestUid),
              Filters.eq("metadata.type", "RESPONSE")))
          .limit(1000).sort(Sorts.descending("length", "uploadDate")).first();
      if (result == null) {
        return new byte[0];
      }
      String fileName = result.get("filename").toString();
      System.out.println(fileName);
      GridFSFile fileResult = gridFSBucket.find(Filters.eq("filename", fileName)).limit(1).first();
      gridFSBucket.downloadToStream(fileResult.getId(), outputStream);
      return outputStream.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static class InstanceHolder {

    private static final MongoRepository INSTANCE = new MongoRepositoryImpl();
  }
}

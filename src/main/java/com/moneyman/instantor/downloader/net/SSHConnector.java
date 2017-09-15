package com.moneyman.instantor.downloader.net;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public final class SSHConnector {

  private static final String HOST = "52.67.134.152";
  private static final Integer PORT = 22;

  private static final String LOCAL_HOST = "localhost";
  private static final Integer LOCAL_PORT = 27017;

  private static final String USERNAME = "*****";
  private static final String PPK_PATH = "*****";

  private final JSch jsch;
  private final Session sshSession;

  private SSHConnector(Builder builder) throws JSchException {
    Properties config = new java.util.Properties();
    config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
    config.put("StrictHostKeyChecking", "no");

    jsch = new JSch();
    jsch.addIdentity(builder.ppkPath);

    sshSession = jsch.getSession(builder.username, builder.host, builder.port);
    sshSession.setConfig(config);

    sshSession.connect();
    sshSession.setPortForwardingL(LOCAL_HOST, LOCAL_PORT, LOCAL_HOST, LOCAL_PORT);
  }

  public static SSHConnector.Builder builder() {
    return new SSHConnector.Builder();
  }

  public void finalize() throws JSchException {
    sshSession.delPortForwardingL(LOCAL_PORT);
    sshSession.disconnect();
  }

  public static class Builder {

    private String host;
    private Integer port;
    private String localHost;
    private Integer localPort;
    private String username;
    private String ppkPath;
    private String passPhrase;

    private Builder() {
      this.host = HOST;
      this.port = PORT;
      this.localHost = LOCAL_HOST;
      this.localPort = LOCAL_PORT;
      this.username = USERNAME;
      this.ppkPath = PPK_PATH;
      this.passPhrase = null;
    }

    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    public Builder setPort(Integer port) {
      this.port = port;
      return this;
    }

    public Builder setLocalHost(String localHost) {
      this.localHost = localHost;
      return this;
    }

    public Builder setLocalPort(Integer localPort) {
      this.localPort = localPort;
      return this;
    }

    public Builder setUsername(String username) {
      this.username = username;
      return this;
    }

    public Builder setPpkPath(String ppkPath) {
      this.ppkPath = ppkPath;
      return this;
    }

    public Builder setPassPhrase(String passPhrase) {
      this.passPhrase = passPhrase;
      return this;
    }

    public SSHConnector build() throws JSchException {
      return new SSHConnector(this);
    }
  }
}

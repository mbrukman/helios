/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.helios.common.descriptors;

import com.google.common.base.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.Nullable;

/**
 * Defines the health check for the Helios job. There are 3 types of health checks.
 *
 * HTTP healthcheck:
 *
 * <pre>
 * {
 *   "type" : "http",
 *   "path" : "/path/to/healthcheck",
 *   "port" : "http-admin"
 * }
 * </pre>
 *
 * TCP healthcheck:
 *
 * <pre>
 * {
 *   "type" : "tcp",
 *   "port" : "http-admin"
 * }
 * </pre>
 *
 * Command-based healthcheck:
 *
 * <pre>
 * {
 *   "type" : "cmd",
 *   "cmd" : "bash -c '/usr/bin/curl 127.0.0.1:9200/_cluster/health?pretty=true | grep green'"
 * }
 * </pre>
 */

public class HealthCheck extends Descriptor {
  private final String type;
  private final String path;
  private final String port;
  private final String cmd;

  public HealthCheck(@JsonProperty("type") final String type,
                     @JsonProperty("path") @Nullable final String path,
                     @JsonProperty("port") @Nullable final String port,
                     @JsonProperty("cmd") @Nullable final String cmd) {
    this.type = type;
    this.path = path;
    this.port = port;
    this.cmd = cmd;
  }

  @Nullable
  public String getType() {
    return type;
  }

  @Nullable
  public String getPath() {
    return path;
  }

  @Nullable
  public String getPort() {
    return port;
  }

  @Nullable
  public String getCmd() {
    return cmd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    HealthCheck that = (HealthCheck) o;

    if (type != null ? !type.equals(that.type) : that.type != null) {
      return false;
    }
    if (path != null ? !path.equals(that.path) : that.path != null) {
      return false;
    }
    if (port != null ? !port.equals(that.port) : that.port != null) {
      return false;
    }
    if (cmd != null ? !cmd.equals(that.cmd) : that.cmd != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = type != null ? type.hashCode() : 0;
    result = 31 * result + (path != null ? path.hashCode() : 0);
    result = 31 * result + (port != null ? port.hashCode() : 0);
    result = 31 * result + (cmd != null ? cmd.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return Objects.toStringHelper(this)
        .add("type", type)
        .add("path", path)
        .add("port", port)
        .add("cmd", cmd)
        .toString();
  }
}

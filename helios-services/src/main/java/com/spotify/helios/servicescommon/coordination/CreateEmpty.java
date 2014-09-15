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

package com.spotify.helios.servicescommon.coordination;

import com.google.common.base.Joiner;

import org.apache.curator.framework.api.transaction.CuratorTransaction;

import java.util.Arrays;
import java.util.List;

class CreateEmpty implements ZooKeeperOperation {

  private final List<String> paths;

  CreateEmpty(final String... paths) {
    this.paths = Arrays.asList(paths);
  }

  CreateEmpty(final List<String> paths) {
    this.paths = paths;
  }

  @Override
  public void register(final CuratorTransaction transaction) throws Exception {
    for (String path : paths) {
      transaction.create().forPath(path);
    }
  }

  @Override
  public String toString() {
    return "CreateEmpty{" +
           "paths='" + Joiner.on(",").join(paths) + '\'' +
           '}';
  }
}

/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.solr.model;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Data;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 01, 2021
 */
@Data
public class Item {
  @Field
  private String id;

  @Field
  private String description;

  @Field
  private String category;

  @Field
  private float price;
}

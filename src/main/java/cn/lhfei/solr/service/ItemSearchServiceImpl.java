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

package cn.lhfei.solr.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import cn.lhfei.solr.model.Item;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 01, 2021
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {
  private final SolrClient solrClient;

  public ItemSearchServiceImpl(SolrClient solrClient) {
      this.solrClient = solrClient;
  }

  @Override
  public void index(String id, String description, String category, float price) throws SolrServerException, IOException {
      SolrInputDocument doc = new SolrInputDocument();
      doc.addField("id", id);
      doc.addField("description", description);
      doc.addField("category", category);
      doc.addField("price", price);
      solrClient.add(doc);
      solrClient.commit();
  }

  @Override
  public void indexBean(Item item) throws IOException, SolrServerException {
      solrClient.addBean(item);
      solrClient.commit();
  }

}

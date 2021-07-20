/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.lhfei.solr.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Mar 01, 2021
 */
@RestController
@RequestMapping("/techproducts")
public class TechProductResource {
  private static SolrClient solrClient;
  private static final String solrUrl = "http://11.91.141.240/solr";

  @GetMapping("/")
  public SolrDocumentList process() throws SolrServerException, IOException {
    solrClient = new HttpSolrClient.Builder(solrUrl).build();

    Map<String, Object> fq = new HashMap<String, Object>();
    fq.put("name", "CORSAIR"); // field query
    JsonQueryRequest simpleQuery = new JsonQueryRequest()
        .setQuery("*:*")
        .withFilter("name:CORSAIR*")
        .withFilter("popularity:[5 TO 7]");
    QueryResponse response = simpleQuery.process(solrClient, "techproducts");
    
    solrClient.close();

    return response.getResults();

  }
  
  @GetMapping("/list")
  public SolrDocumentList query() throws SolrServerException, IOException {
    solrClient = new HttpSolrClient.Builder(solrUrl).build();

    final Map<String, String> queryParamMap = new HashMap<String, String>();
    queryParamMap.put("q", "*:*"); // the query string
    queryParamMap.put("fl", "id, name"); // field list
    queryParamMap.put("fq", "name:CORSAIR*"); // field query
    queryParamMap.put("sort", "id asc");
    MapSolrParams queryParams = new MapSolrParams(queryParamMap);
    final QueryResponse response = solrClient.query("techproducts", queryParams);
    
    solrClient.close();
    
    return response.getResults();

  }
}

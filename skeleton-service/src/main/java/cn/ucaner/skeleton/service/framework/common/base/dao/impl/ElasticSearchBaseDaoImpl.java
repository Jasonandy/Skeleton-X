/******************************************************************************
 * ~ Copyright (c) 2018 [jasonandy@hotmail.com | https://github.com/Jasonandy] *
 * ~                                                                           *
 * ~ Licensed under the Apache License, Version 2.0 (the "License”);           *
 * ~ you may not use this file except in compliance with the License.          *
 * ~ You may obtain a copy of the License at                                   *
 * ~                                                                           *
 * ~    http://www.apache.org/licenses/LICENSE-2.0                             *
 * ~                                                                           *
 * ~ Unless required by applicable law or agreed to in writing, software       *
 * ~ distributed under the License is distributed on an "AS IS" BASIS,         *
 * ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * ~ See the License for the specific language governing permissions and       *
 * ~ limitations under the License.                                            *
 ******************************************************************************/
package cn.ucaner.skeleton.service.framework.common.base.dao.impl;

import cn.ucaner.skeleton.common.base.entity.BaseEntity;
import cn.ucaner.skeleton.service.framework.common.base.dao.ElasticSearchBaseDao;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.framework.common.base.dao.impl
 * @Description： <p> ElasticSearchBaseDaoImpl </p>
 * @Author： - Jason
 * @CreatTime：2019/4/26 - 10:32
 * @Modify By：
 * @ModifyTime： 2019/4/26
 * @Modify marker：
 */
@Service
public class ElasticSearchBaseDaoImpl  implements ElasticSearchBaseDao {

    private static Logger logger = LoggerFactory.getLogger(ElasticSearchBaseDaoImpl.class);

    @Autowired
    private TransportClient transportClient;

    /**
     * createIndexByJson
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param indexId   建立索引的唯一标识ID
     * @param jsonStr   索引对象实例的对应json串
     * @return
     */
    @Override
    public IndexResponse createIndexByJson(String indexName, String indexType, String indexId, String jsonStr) {
        IndexResponse response = transportClient.prepareIndex(indexName, indexType,indexId)
                .setSource(jsonStr).execute().actionGet();
        return response;
    }

    /**
     * createIndexsByBeans
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param entry     对象集合
     */
    @Override
    public void createIndexsByBeans(String indexName, String indexType, List<BaseEntity> entry) {
        BulkRequestBuilder builder = transportClient.prepareBulk();
        for (BaseEntity entity : entry) {
            String jsonStr = JSON.toJSONString(entity);
            String id = entity.getId();
            builder.add(transportClient.prepareIndex(indexName, indexType, id).setSource(jsonStr).request());
        }
        builder.execute().actionGet();
    }

    /**
     * searchById
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param queryName 查询字段名
     * @param queryValue 查询字段值
     * @param pageInfo   分页pageInfo
     * @return
     */
    @Override
    public List<Object> searchById(String indexName, String indexType, String queryName,
                                   String queryValue, PageInfo pageInfo) {
        return null;
    }

    /**
     *search
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param bqbl     QueryBuilder
     * @param highLighterFields key 查询条件 value 字段域
     * @param pageInfo 分页信息
     * @param cls
     * @param entities 实体信息
     * notice: {
     *     List<FilterBuilder> fbList 弃用 - 所有FilterBuilder全都要用QueryBuilder的各种子类来调整
     *     https://segmentfault.com/a/1190000015863043?utm_source=tag-newest
     * }
     * @return
     * @throws Exception
     */
    @Override
    public List search(String indexName, String indexType, List<QueryBuilder> bqbl, String[] highLighterFields,
                       PageInfo pageInfo, Class cls, List<BaseEntity> entities) throws Exception {
        int pageIndex ;
        int pageSize;
        int from ;

        /**
         * boolQueryBuilderTotal
         */
        BoolQueryBuilder boolQueryBuilderTotal = QueryBuilders.boolQuery();
        if (bqbl != null) {
            for (QueryBuilder bqb : bqbl) {
                boolQueryBuilderTotal.must(bqb);
            }
        }

        /**
         * SearchRequestBuilder
         */
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(indexName).setTypes(indexType);

        searchRequestBuilder.setQuery(boolQueryBuilderTotal).setExplain(true);

        /**
         * 分页信息
         */
        if (pageInfo != null) {
            pageIndex = pageInfo.getPageNum();
            pageSize = pageInfo.getPageSize();
            from = (pageIndex - 1) * pageSize;
            searchRequestBuilder.setFrom(from).setSize(pageSize);
        }

        if (entities != null && entities.size() > 0) {
            /**
             * 如果需要排序
             */
            for (BaseEntity order : entities) {
                searchRequestBuilder.addSort("",SortOrder.DESC);
            }

        }

        /**
         * 设置 高亮的样式
         */
        HighlightBuilder highlightBuilder  = new HighlightBuilder();

        /**
         * 需要高亮的字段
         */
        if (highLighterFields != null && highLighterFields.length > 0) {
            /**
             * 设置前缀
             */
            highlightBuilder.preTags("<font class='font-highlight'>");
            /**
             * 设置后缀
             */
            highlightBuilder.postTags("</font>");
            highlightBuilder.field(highLighterFields[0]);
        }

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        SearchHits hits = searchResponse.getHits();
        SearchHit[] hts = hits.getHits();

        return null;
    }

    @Override
    public List searchCount(String indexName, String indexType, List<QueryBuilder> bqbl,
                            PageInfo pageInfo, String groupFiled) {


        return null;
    }


    /**
     * searchByOnlyValue
     * @param indexName 索引信息
     * @param indexType 索引类型
     * @param queryFiled 查询字段
     * @param queryValue 查询条件
     * @param highligtFiled 高亮字段
     * @param pageInfo 分页信息
     * @return
     */
    @Override
    public SearchHits searchByOnlyValue(String indexName, String indexType,String queryFiled,
                                        String queryValue,String highligtFiled, PageInfo pageInfo) {
        /**
         * 分页信息
         */
        int pageIndex = pageInfo.getPageNum();
        int pageSize = pageInfo.getPageSize();
        int from = (pageIndex - 1) * pageSize;

        /**
         *  模糊查询
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        QueryBuilder qu = QueryBuilders.fuzzyQuery(queryFiled, queryValue);
        boolQueryBuilder.should(qu);

        /**
         * 设置 高亮的样式
         */
        HighlightBuilder highlightBuilder  = new HighlightBuilder();
        /**
         * 设置前缀
         */
        highlightBuilder.preTags("<font class='font-highlight'>");
        /**
         * 设置后缀
         */
        highlightBuilder.postTags("</font>");
        /**
         *设置高亮字段
         */
        highlightBuilder.field(highligtFiled);

        /**
         * 构建搜索 bulid
         */
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch(indexName).setTypes(indexType)
                /**
                 * bqb
                 */
                .setQuery(boolQueryBuilder)
                /**
                 * eg:
                 * form 1 - 2 从几个开始 每页多少数据量 当前页如果为1  (1-1)*2 = 0  也就是 0-2 数据要load出来展示
                 */
                .setFrom(from).setSize(pageSize)
                /**
                 * 设置高亮
                 */
                .highlighter(highlightBuilder)
                .setExplain(true);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        /**
         * 获取数据
         */
        SearchHits hits = searchResponse.getHits();
        return hits;
    }

    /**
     * deleteIndexById
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param id         指定索引文档Id
     * @return
     */
    @Override
    public DeleteResponse deleteIndexById(String indexName, String indexType, String id) {
        DeleteResponse response = transportClient.prepareDelete(indexName, indexType, id).execute().actionGet();
        return response;
    }

    /**
     * deleteIndexByIds
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param ids       指定索引文档Id集合
     */
    @Override
    public void deleteIndexByIds(String indexName, String indexType, List<String> ids) {
        BulkRequestBuilder builder = transportClient.prepareBulk();
        for (String id : ids) {
            builder.add(transportClient.prepareDelete(indexName, indexType, id).request());
        }
        builder.execute().actionGet();
    }


    /**
     * deleteAllIndex  不能以type为分类
     * @param indexName 索引组名
     * @param indxType  索引类型
     */
    @Override
    public void deleteAllIndex(String indexName, String indxType) {
        /**
         * 先判断下索引是否存在
         */
        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse inExistsResponse = transportClient.admin().indices().exists(inExistsRequest).actionGet();

        /**
         * 存在即进行删除操作
         */
        if(inExistsResponse.isExists()){
            DeleteIndexRequestBuilder delete = transportClient.admin().indices().prepareDelete(indexName);
            delete.execute().actionGet();
        }
    }

    /**
     * updateIndex
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param entry     更新数据
     * @param id        更新条件Id
     */
    @Override
    public void updateIndex(String indexName, String indexType, BaseEntity entry, String id) {
        /**
         * 先删除
         */
        transportClient.prepareDelete(indexName, indexType, id).execute().actionGet();
        String jsonStr = JSON.toJSONString(entry);

        /**
         * 再update进去
         */
        transportClient.prepareIndex(indexName, indexType, id).setSource(jsonStr).execute().actionGet();
    }

    /**
     * 判断指定的索引的类型是否存在
     * @param indexNames 索引s
     * @param indexTypes 类型s
     * @return true存在 false 不存在
     */
    @Override
    public boolean isExistsType(String[] indexNames, String indexTypes) {
        TypesExistsRequest typeRequest = new TypesExistsRequest(indexNames, indexTypes);
        TypesExistsResponse response = transportClient.admin().indices().typesExists(typeRequest).actionGet();
        if (response.isExists()){
            return true;
        }
        return false;
    }

    /**
     * indexExists 指定的索引是否存在
     * @param index 索引name  indexName
     * @return true存在 false 不存在
     */
    @Override
    public boolean indexExists(String index) {
        IndicesExistsRequest request = new IndicesExistsRequest(index);
        IndicesExistsResponse response = transportClient.admin().indices().exists(request).actionGet();
        if (response.isExists()){
            return true;
        }
        return false;
    }

    /**
     * cleanAllIndex
     */
    @Override
    public void cleanAllIndex() {
        ClusterStateResponse response = transportClient.admin().cluster().prepareState().execute().actionGet();
        String[] indexs = response.getState().getMetaData().getConcreteAllIndices();
        if (null != indexs && indexs.length!=0){
            for (String index : indexs){
                logger.warn("----- warning 危险操作:删除所有的index:{} -----",indexs);
                transportClient.admin().indices().prepareDelete(index).execute().actionGet();
            }
        }
    }

    /**
     * getIndexDataCount 获取index对应的总数据量
     * @param index index
     * @return
     */
    @Override
    public long getIndexDataCount(String index) {
        SearchRequestBuilder responsebuilder = transportClient.prepareSearch(index);
        SearchResponse myresponse = responsebuilder.get();
        return myresponse.getHits().getTotalHits();
    }

}

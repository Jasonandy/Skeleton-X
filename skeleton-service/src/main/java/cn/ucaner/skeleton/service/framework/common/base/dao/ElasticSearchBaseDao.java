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
package cn.ucaner.skeleton.service.framework.common.base.dao;

import cn.ucaner.skeleton.common.base.entity.BaseEntity;
import com.github.pagehelper.PageInfo;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;
import java.util.Map;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.framework.common.base.dao
 * @Description： <p> ElasticSearchBaseDao </p>
 * @Author： - Jason
 * @CreatTime：2019/4/26 - 10:08
 * @Modify By：
 * @ModifyTime： 2019/4/26
 * @Modify marker：
 */
public interface ElasticSearchBaseDao {

    /**
     * createIndexByJson 根据对象实例的json串创建索引(单条索引).
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param indexId   建立索引的唯一标识ID
     * @param jsonStr   索引对象实例的对应json串
     * @return
     */
     IndexResponse createIndexByJson(String indexName, String indexType,String indexId, String jsonStr);


    /**
     * 根据对象集合批量索引(多条批量)
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param entry     对象集合
     */
     void createIndexsByBeans(String indexName, String indexType,List<BaseEntity> entry);


    /**
     * 根据索引唯一标识ID查找对象
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param queryName 查询字段名
     * @param queryValue 查询字段值
     * @param pageInfo   分页pageInfo
     * @return
     */
     List<Object> searchById(String indexName, String indexType,String queryName, String queryValue, PageInfo pageInfo);


    /**
     * 根据传入的值进行模糊查询(分页查询)
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
    List search(String indexName, String indexType, List<QueryBuilder> bqbl,
                String[] highLighterFields, PageInfo pageInfo, Class cls,List<BaseEntity> entities) throws Exception;


    /**
     * 根据传入的值进行模糊查询(分组查询个数)
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param bqbl     QueryBuilder
     * @param pageInfo     分页信息
     * @param groupFiled   分页字段
     * @return
     */
    List searchCount(String indexName, String indexType,List<QueryBuilder> bqbl,PageInfo pageInfo,String groupFiled);


    /**
     * searchByOnlyValue
     * @param indexName 索引信息
     * @param indexType 索引类型
     * @param queryFiled 查询字段
     * @param queryValue 查询条件
     * @param highligtFiled 高亮字段
     * @param pageInfo 分页信息
     * @return SearchHits
     */
    SearchHits searchByOnlyValue(String indexName, String indexType,String queryFiled,String queryValue, String highligtFiled,PageInfo pageInfo) ;


    /**
     * 根据指定属性键值对删除索引(查询删除)
     * @param indexName 索引组名
     * @param indexType  索引类型
     * @param name  删除依据的字段属性名
     * @param value 删除依据的字段属性值
     * @return
     */
     //DeleteByQueryResponse deleteIndexByNameAndValue(String indexName,String indexType, String name, String value);


    /**
     * 根据索引的Id删除索引(单条删除).
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param id         指定索引文档Id
     * @return
     */
     DeleteResponse deleteIndexById(String indexName, String indexType,String id);


    /**
     * 根据索引的Id集合删除索引(批量删除)
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param ids       指定索引文档Id集合
     */
     void deleteIndexByIds(String indexName, String indexType,List<String> ids);


    /**
     * 删除确定索引组和索引类型下所有数据(危险，慎用)
     * @param indexName 索引组名
     * @param indxType  索引类型
     */
     void deleteAllIndex(String indexName, String indxType);

    /**
     * 根据ID更新索引(单条更新)
     * @param indexName 索引组名
     * @param indexType 索引类型
     * @param entry     更新数据
     * @param id        更新条件Id
     */
     void updateIndex(String indexName, String indexType,BaseEntity entry, String id);


    /**
     * 判断该index/type 的index 是否存在
     * @param indexNames 索引s
     * @param indexTypes 类型s
     * @return 存在返回true
     */
     boolean isExistsType(String[] indexNames, String indexTypes);

    /**
     * indexExists 判断index是否存在
     * @param index
     * @return 返回 false
     */
    boolean indexExists(String index);

    /**
     * 清除所有的index
     */
    void cleanAllIndex();


    /**
     * 获取索引对应的总数据
     * @param index index
     * @return 数据量[条数]
     */
    long getIndexDataCount(String index);

    /**
     * type 类型 多条件 模糊查询
     * @param index    索引
     * @param type     类型
     * @param queryMap queryMap K-V 查询条件
     * @return
     */
    List getDataByMuchIllegible(String index,String type, Map<String,String> queryMap);
}

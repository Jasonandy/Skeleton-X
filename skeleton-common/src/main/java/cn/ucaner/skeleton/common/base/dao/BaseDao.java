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
package cn.ucaner.skeleton.common.base.dao;

import cn.ucaner.skeleton.common.base.entity.BaseEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @projectName：watchDog-framework
 * @Package：cn.ucaner.skeleton.common.base.dao
 * @Description： <p> BaseDao  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/18 - 16:42
 * @Modify By：
 * @ModifyTime： 2019/3/18
 * @Modify marker：
 */
public interface BaseDao<T extends BaseEntity, PK extends Serializable> {

    /**
     * 查询
     *
     * @param entity
     * @return
     */
    public T find(T entity);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    public T findById(PK id);

    /**
     * 根据ID集合来查询
     *
     * @param ids
     * @return
     */
    public List<T> findByIds(List<PK> ids);

    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 查询列表
     * @param params
     * @return
     */
    public List<T> findListByParams(Map<String, Object> params);

    /**
     * 查询列表
     * @param params
     * @param page
     * @return
     */
    public PageInfo<T> findListByPage(Map<String, Object> params, Page<T> page);

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 查询总记录数
     *
     * @return
     */
    public Long count();

    /**
     * 查询总记录数
     *
     * @param entity
     * @return
     */
    public Long count(T entity);

    /**
     * 添加
     *
     * @param entity
     */
     void insert(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(T entity);

    /**
     * 根据Id删除
     *
     * @param id
     */
    public void deleteById(PK id);

    /**
     * 根据ID集合删除
     *
     * @param ids
     * @return
     */
    public Long deleteByIds(List<PK> ids);

    /**
     * 删除所有记录
     */
    public void deleteAll();

    /**
     * 更新
     *
     * @param entity
     */
    public void update(T entity);

    /**
     * 更新
     * @param params
     */
    public void updateByParams(Map<String, Object> params);

    /**
     * 检查数据是否已经存在
     * @param params
     * @return
     */
    public boolean check(Map<String, Serializable> params);

    /**
     * 根据ID集合批量逻辑删除<br/>
     * 删除执行update xx set IS_DELETE = true操作.
     * @param map， map.list 为List类型，包含要删除的ids
     */
    public void batchLogicalDelete(Map<String, Object> map);

    /**
     * 根据ID集合批量删除
     *
     * @param ids
     */
    public void batchDelete(List<PK> ids);

    /**
     * @Description: 批量插入
     * @param entitys
     */
    public void batchInsert(List<T> entitys);

    /**
     * @Description: 按分表批量插入 -- [后期如果数据量较大的话 添加分表逻辑  在后期用分库]
     * @param paramsMap
     */
    public void batchInsertByMap(Map<String, Object> paramsMap);

    /**
     * @Description: batchUpdate 批量更新
     * @param entitys
     */
    public void batchUpdate(List<T> entitys);
}

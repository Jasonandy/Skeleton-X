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
package cn.ucaner.skeleton.common.base.service;

import cn.ucaner.skeleton.common.base.entity.BaseEntity;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName：BaseService
 * @Description： <p> BaseService  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/18 - 16:48
 * @Modify By：
 * @ModifyTime： 2019/3/18
 * @Modify marker：
 * @version V1.0
*/
public interface BaseService <T extends BaseEntity, PK extends Serializable>{

    /**
     * @Description: 查询
     * @param entity
     * @return T
     * @Autor: Jason
     */
    public T find(T entity);

    /**
     * @Description: 通过Id查询
     * @param id
     * @return T
     * @Autor: Jason
     */
    public T findById(PK id);

    /**
     * @Description: 根据ID集合来查询
     * @param ids
     * @return List<T>
     * @Autor: Jason
     */
    public List<T> findByIds(List<PK> ids);

    /**
     * @Description:  查询列表
     * @param entity
     * @return List<T>
     * @Autor: Jason
     */
    public List<T> findList(T entity);

    /**
     * @Description: 查询列表
     * @param params
     * @return List<T>
     * @Autor: Jason
     */
    public List<T> findListByParams(Map<String, Object> params);

    /**
     * @Description: 查询列表
     * @param parms
     * @param page
     * @return PageInfo<T>
     * @Autor: Jason
     */
    public PageInfo<T> findListByPage(Map<String, Object> parms, Page<T> page);

    /**
     * @Description: 查询所有记录
     * @return List<T>
     * @Autor: Jason
     */
    public List<T> findAll();

    /**
     * @Description: 查询总记录数
     * @return Long
     * @Autor: Jason
     */
    public Long count();

    /**
     * @Description: 查询总记录数
     * @param entity
     * @return Long
     * @Autor: Jason
     */
    public Long count(T entity);

    /**
     * @Description: 添加
     * @param entity
     * @return PK
     * @Autor: Jason
     */
    public PK insert(T entity);

    /**
     * @Description: 删除
     * @param entity
     * @Autor: Jason
     */
    public void delete(T entity);

    /**
     * @Description: 根据Id删除
     * @param id
     * @Autor: Jason
     */
    public void deleteById(PK id);

    /**
     * @Description: 根据ID集合删除
     * @param ids
     * @Autor: Jason
     */
    public void deleteByIds(List<PK> ids);

    /**
     * @Description: 删除所有记录
     * @Autor: Jason
     */
    public void deleteAll();

    /**
     * @Description: 更新
     * @param entity
     * @Autor: Jason
     */
    public void update(T entity);

    /**
     * @Description: 更新
     * @param params
     * @Autor: Jason
     */
    public void updateByParams(Map<String, Object> params);

    /**
     * @Description: 检查数据是否已经存在
     * @param params
     * @return boolean
     * @Autor: Jason
     */
    public boolean check(Map<String, Serializable> params);

    /**
     * @Description: 根据ID集合批量删除
     * @param ids
     * @Autor: Jason
     */
    public void batchDelete(List<PK> ids);

    /**
     * @Description: batchLogicalDelete  根据ID集合批量逻辑删除 <br/> 执行了update xxx set IS_DELETE = true操作
     * @param map  map, map.list为List类型，包含ids
     * @Autor: Jason
     */
    public void batchLogicalDelete(Map<String, Object> map);

    /**
     * @Description: 批量插入
     * @param entitys
     * @Autor: Jason
     */
    public void batchInsert(List<T> entitys);

    /**
     * @Description: 按分表批量插入
     * @param paramsMap 包含表信息
     * @Autor: Jason
     */
    public void batchInsertByMap(Map<String, Object> paramsMap);

    /**
     * @Description: 批量更新
     * @param entitys
     * @Autor: Jason
     */
    public void batchUpdate(List<T> entitys);
}

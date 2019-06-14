/*******************************************************************************
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
package cn.ucaner.skeleton.codegen.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @ClassName：Query
 * @Description： <p> Query </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:17
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 * @version V1.0
*/
public class Query<T> extends Page<T> {

    /**
     * PAGE
     */
    private static final String PAGE = "page";

    /**
     * LIMIT
     */
    private static final String LIMIT = "limit";

    /**
     * ORDER_BY_FIELD
     */
    private static final String ORDER_BY_FIELD = "orderByField";

    /**
     * IS_ASC
     */
    private static final String IS_ASC = "isAsc";

    /**
     * Query
     * @param params
     */
    public Query(Map<String, Object> params) {
        super(Integer.parseInt(params.getOrDefault(PAGE, 1).toString()),
                Integer.parseInt(params.getOrDefault(LIMIT, 10).toString()));

        String orderByField = params.getOrDefault(ORDER_BY_FIELD, "").toString();
        if (StringUtils.isNotEmpty(orderByField)) {
            Boolean isAsc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
            if(isAsc){
                this.setAsc(orderByField);
            }else{
                this.setDesc(orderByField);
            }
        }
        params.remove(PAGE);
        params.remove(LIMIT);
        params.remove(ORDER_BY_FIELD);
        params.remove(IS_ASC);
    }
}


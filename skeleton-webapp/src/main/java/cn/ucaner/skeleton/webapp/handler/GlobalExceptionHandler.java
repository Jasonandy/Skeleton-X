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
package cn.ucaner.skeleton.webapp.handler;

import cn.ucaner.skeleton.webapp.exception.GlobalException;
import cn.ucaner.skeleton.webapp.utils.R;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.handler
 * @Description： <p> GlobalExceptionHandler  </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:42
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public R exception(Exception e) {
        e.printStackTrace();
        return new R(500, "系统异常");
    }

    @ExceptionHandler(value = GlobalException.class)
    public R globalException(GlobalException e) {
        e.printStackTrace();
        return new R(500, e.getMsg());
    }
}

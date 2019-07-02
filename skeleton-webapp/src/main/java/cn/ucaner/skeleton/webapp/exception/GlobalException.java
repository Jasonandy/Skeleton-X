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
package cn.ucaner.skeleton.webapp.exception;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.webapp.exception
 * @Description： <p> GlobalException </p>
 * @Author： - Jason
 * @CreatTime：2019/7/2 - 15:41
 * @Modify By：
 * @ModifyTime： 2019/7/2
 * @Modify marker：
 */
public class GlobalException extends RuntimeException {

    /**
     * 异常信息
     */
    private String msg;

    public GlobalException(String message) {
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

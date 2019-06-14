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
package cn.ucaner.skeleton.codegen.exception;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.codegen.exception
 * @Description： <p> CodegenException  </p>
 * @Author： - Jason
 * @CreatTime：2019/6/14 - 14:36
 * @Modify By：
 * @ModifyTime： 2019/6/14
 * @Modify marker：
 */
public class CodegenException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * status
     */
    private int status = 500;

    private String message;

    public CodegenException(String message) {
        super(message);
        this.message = message;
    }

    public CodegenException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public CodegenException(String message, Throwable cause) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public CodegenException(int status,String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

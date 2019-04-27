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
package cn.ucaner.skeleton.gateway.jwt.token;

import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.jwt.token
 * @Description： <p> JwtToken  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 10:07
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class JwtToken implements AuthenticationToken,Serializable {

    private static final long serialVersionUID = -1929842923741950361L;


    /**
     * jwt token
     */
    private String token;


    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

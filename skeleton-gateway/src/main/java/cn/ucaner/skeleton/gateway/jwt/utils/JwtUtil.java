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
package cn.ucaner.skeleton.gateway.jwt.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;


/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.gateway.jwt.utils
 * @Description： <p> JwtUtil  </p>
 * @Author： - Jason
 * @CreatTime：2019/4/27 - 9:26
 * @Modify By：
 * @ModifyTime： 2019/4/27
 * @Modify marker：
 */
public class JwtUtil {

    /**
     * jwt的登录凭证字段 - username
     */
    private static final String AUTHOR_FLAG = "Skeleton";

    /**
     * 过期时间 - 默认5分钟
     */
    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * @Description:   校验token是否正确
     * @param token    密钥
     * @param username
     * @param secret   用户的密码
     * @return boolean 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT
                    .require(algorithm)
                    .withClaim(AUTHOR_FLAG, username)
                    .build();
            verifier.verify(token);
            /**
             * 校验token是否正确
             */
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * @Description: 获得token中的信息无需secret解密也能获得
     * @param token  token中包含的用户名
     * @return String
     * @Autor: 可以从JWT加密的信息里拿出AUTHOR_FLAG
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(AUTHOR_FLAG).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @Description:    生成签名 5min后过期
     * @param username  用户名
     * @param secret    用户的密码
     * @return String   加密的token
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            /**
             * 将登录信息添加都到Claim中,username信息带过去.
             */
            return JWT.create().withClaim(AUTHOR_FLAG, username).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}

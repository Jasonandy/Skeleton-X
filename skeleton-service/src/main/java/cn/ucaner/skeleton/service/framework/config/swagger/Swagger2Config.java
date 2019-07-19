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
package cn.ucaner.skeleton.service.framework.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @ClassName：Swagger2Config
 * @Description： <p> Swagger - @Url{http://localhost:9095/swagger-ui.html} </p>
 * @Author： - Jason
 * @CreatTime：2019/4/26 - 14:09
 * @Modify By：
 * @ModifyTime： 2019/4/26
 * @Modify marker：
 * @version V1.0
*/
@EnableSwagger2
@Configuration
public class Swagger2Config {

    /**
     * 需要扫描的路径
     */
    private static  final  String SWAGGER_PACKAGE_PATH = "cn.ucaner.skeleton";

    /**
     * 是否开启swagger
     */
    @Value(value = "${swagger.enabled:true}")
    Boolean swaggerEnabled;

    /**
     * createRestApi
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(swaggerEnabled)
                .select()
                /**
                 * api 选择
                 */
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_PACKAGE_PATH))
                /**
                 * 路径选择
                 */
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }

    /**
     * 构建api信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("--- Skeleton ---")
                .description("Jason | 勿忘初心 方得始终")
                .contact(new Contact("Jason", "https://github.com/Jasonandy/Skeleton-X", "jasonandy@hotmail.com"))
                .version("V1.0.1")
                .description("*** Skeleton Api Document ***")
                .build();
    }
}
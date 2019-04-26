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
package cn.ucaner.skeleton.service.framework.config.ehcache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


/**
 * @ClassName：EhCacheConfig
 * @Description： <p> EhCacheConfig </p>
 * @Author： - Jason
 * @CreatTime：2019/4/26 - 14:56
 * @Modify By：
 * @ModifyTime： 2019/4/26
 * @Modify marker：
 * @version V1.0
*/
@Configuration
@ConditionalOnProperty(name = "enabled", havingValue = "true",prefix="ehcache")
public class EhCacheConfig {


    /**
     * 缓存配置的位置
     */
    public static final String EH_CACHE_CLASS_PATH = "config/ehcache/ehcache.xml";


    /**
     * @Description: ehcache 主要的管理器
     * @param bean   Spring管理
     * @return EhCacheCacheManager
     * @Autor: Jason
     */
    @Bean(name = "appEhCacheCacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager (bean.getObject ());
    }

    /**
     * @Description: Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地
     * @return EhCacheManagerFactoryBean
     * @Autor: Jason
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource(EH_CACHE_CLASS_PATH));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }

}

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
package cn.ucaner.skeleton.service.framework.config.es;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.net.InetAddress;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.service.framework.config.es
 * @Description： <p> ElasticsearchConfig elasticsearch spring-data 目前支持的最高版本为5.5 所以需要自己注入生成客户端
 * 三个接口可以使用  AbstractFactoryBean  通过继承重写方法代替 implements FactoryBean<RestHighLevelClient>, InitializingBean, DisposableBean  </p>
 * @Author： - Jason
 * @CreatTime：2019/3/26 - 10:30
 * @Modify By：
 * @ModifyTime： 2019/3/26
 * @Modify marker：
 */
@Configuration
@PropertySources(value = {@PropertySource("classpath:/es/es.properties")})
public class ElasticsearchConfig {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    private static final String CLUSTER_NODES_SPLIT_SYMBOL = ",";

    private static final String HOST_PORT_SPLIT_SYMBOL = ":";

    /**
     * cluster.name
     */
    private static final String ES_CLUSTER_NAME = "cluster.name";

    /**
     * client.transport.sniff
     */
    private static final String ES_TRANSPORT_SNIFF = "client.transport.sniff";

    @Value("${elasticsearch.clusterNodes}")
    private String clusterNodes;


    /**
     * 集群名称
     */
    @Value("${elasticsearch.clusterName}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.poolsize:5}")
    private String poolSize;


    @Bean
    public TransportClient getTransportClient() {
        logger.info("--- elasticsearch init. 初始化开始 ----");
        if (StringUtils.isEmpty(clusterName)) {
            throw new RuntimeException("请检查配置文件：elasticsearch.clusterName is empty.");
        }
        if (StringUtils.isEmpty(clusterNodes)) {
            throw new RuntimeException("请检查配置文件：elasticsearch.clusterNodes is empty.");
        }
        try {
            Settings settings = Settings.builder()
                    .put(ES_CLUSTER_NAME, clusterName.trim())
                    .put(ES_TRANSPORT_SNIFF, true).build();

            TransportClient transportClient = new PreBuiltTransportClient(settings);
            String[] clusterNodeArray = clusterNodes.trim().split(CLUSTER_NODES_SPLIT_SYMBOL);

            for (String clusterNode : clusterNodeArray) {
                String[] clusterNodeInfoArray = clusterNode.trim().split(HOST_PORT_SPLIT_SYMBOL);
                TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(clusterNodeInfoArray[0]),
                        Integer.parseInt(clusterNodeInfoArray[1]));
                transportClient.addTransportAddress(transportAddress);
            }
            logger.info("--- elasticsearch init success. 初始化成功 ---{}",transportClient.listedNodes());
            return transportClient;
        } catch (Exception e) {
            throw new RuntimeException("elasticsearch init fail.");
        }
    }

}

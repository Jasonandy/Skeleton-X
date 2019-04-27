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
//package cn.ucaner.skeleton.gateway.config.https;

//import org.apache.catalina.Context;
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.Ssl;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @projectName：Skeleton-X
// * @Package：cn.ucaner.skeleton.gateway.config
// * @Description： <p> HttpsConfiguration  </p>
// * @Author： - Jason
// * @CreatTime：2019/4/27 - 9:37
// * @Modify By：
// * @ModifyTime： 2019/4/27
// * @Modify marker：
// */
//@Configuration
//@ConditionalOnProperty(name = "enable", havingValue = "true",prefix="https",matchIfMissing=false)
//public class HttpsConfiguration {
//
//	/**
//	 * http 端口
//	 */
//	@Value("${https.httpPort}")
//    private Integer httpPort;
//
//	/**
//	 * https端口
//	 */
//	@Value("${https.httpsPort}")
//	private Integer httpsPort;
//
//	/**
//	 * 证书密码配置
//	 */
//	@Value("${https.cerPwd}")
//	private String cerPwd;
//
//	/**
//	 * 证书路径配置 /data/cer/server.jks
//	 */
//	@Value("${https.cerPath}")
//	private String cerPath;
//
//	/**
//	 * @Description: containerCustomizer
//	 * @return EmbeddedServletContainerCustomizer
//	 * @Autor: Jason
//	 */
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//			Ssl ssl = new Ssl();
//			// Server.jks中包含服务器私钥和证书
//			ssl.setKeyStore(cerPath);
//			ssl.setKeyStorePassword(cerPwd);
//			container.setSsl(ssl);
//			container.setPort(8443);
//			}
//		};
//	}
//
//	/**
//	 * @Description: servletContainerFactory
//	 * @return EmbeddedServletContainerFactory
//	 * @Autor: Jason
//	 */
//	@Bean
//	public EmbeddedServletContainerFactory servletContainerFactory() {
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
//		  @Override
//		  protected void postProcessContext(Context context) {
//		    SecurityConstraint securityConstraint = new SecurityConstraint();
//		    //Confidential 保密
//		    securityConstraint.setUserConstraint("CONFIDENTIAL");
//		    SecurityCollection collection = new SecurityCollection();
//		    collection.addPattern("/*");
//		    securityConstraint.addCollection(collection);
//		    context.addConstraint(securityConstraint);
//		  }
//		};
//		factory.addAdditionalTomcatConnectors(createHttpConnector());
//		return factory;
//	}
//
//	/**
//	 * @Description: createHttpConnector
//	 * @return Connector
//	 * @Autor: Jason
//	 */
//	private Connector createHttpConnector() {
//		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//		connector.setScheme("http");
//		connector.setSecure(false);
//		connector.setPort(httpPort);
//		connector.setRedirectPort(httpsPort);
//		return connector;
//	}
//}

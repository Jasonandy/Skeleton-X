<p align=center>
  <a href="https://github.com/Jasonandy/Skeleton-X.git">
    <img src="http://upload-images.jianshu.io/upload_images/7802425-9eb1bcd006e34aa6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" alt="wx-apps" >
  </a>
</p>

<p align="center">
	<a href="#"><img src="https://img.shields.io/badge/Bulid-Pass-blue.svg?style=for-the-badge" alt="Skeleton-X"></a>
	<a href="#"><img src="https://img.shields.io/badge/Author-Jason-orange.svg?style=for-the-badge" alt="Skeleton-X"></a>
</p>


# Skeleton
> You are what you want to be. - w.b

## 一、Skeleton-Common

``` text
└── java
    └── cn
        └── ucaner
            └── skeleton
                └── common
                    ├── base
                    │   ├── dao
                    │   │   ├── BaseDao.java
                    │   │   └── impl
                    │   │       └── BaseDaoImpl.java
                    │   ├── entity
                    │   │   ├── BaseEntity.java
                    │   │   └── BasePlatformEntity.java
                    │   └── service
                    │       ├── BaseService.java
                    │       └── impl
                    │           └── BaseServiceImpl.java
                    ├── constant
                    │   └── GlobalConstant.java
                    ├── enums
                    │   ├── PublicEnum.java
                    │   └── PublicStatusEnum.java
                    ├── exception
                    │   └── SystemException.java
                    ├── utils
                    │   ├── base
                    │   │   ├── ConvertUtils.java
                    │   │   ├── IdcardValidator.java
                    │   │   ├── MoneyUtils.java
                    │   │   ├── RegexUtils.java
                    │   │   ├── StringHelper.java
                    │   │   └── ValidateHelper.java
                    │   ├── bcrypt
                    │   │   ├── BCrypt.java
                    │   │   └── BCryptPasswordEncoder.java
                    │   ├── bean
                    │   │   └── BeanUtils.java
                    │   ├── captcha
                    │   │   └── CaptchaUtil.java
                    │   ├── cookie
                    │   │   └── CookieUtil.java
                    │   ├── date
                    │   │   └── DateHelper.java
                    │   ├── encode
                    │   │   └── EncodeUtils.java
                    │   ├── encrypt
                    │   │   ├── AESUtils.java
                    │   │   ├── Base64Utils.java
                    │   │   ├── CryptAES.java
                    │   │   ├── EncryptUtil.java
                    │   │   └── MD5Utils.java
                    │   ├── key
                    │   │   ├── SnowflakeIdWorker.java
                    │   │   ├── SystemClock.java
                    │   │   └── VestaKey.java
                    │   └── pk
                    │       └── PKGenerator.java
                    └── vo
                        └── RespBody.java
```

## 二、Skeleton-Service
```text
├── java
│   └── cn
│       └── ucaner
│           └── skeleton
│               └── service
│                   ├── biz
│                   │   └── task
│                   │       └── SkeletonTask.java
│                   ├── controller
│                   │   ├── EasyPoiController.java
│                   │   ├── ElasticSearchController.java
│                   │   ├── KafkaProducerController.java
│                   │   └── UserController.java
│                   ├── framework
│                   │   ├── common
│                   │   │   ├── base
│                   │   │   │   └── dao
│                   │   │   │       ├── ElasticSearchBaseDao.java
│                   │   │   │       └── impl
│                   │   │   │           └── ElasticSearchBaseDaoImpl.java
│                   │   │   └── utils
│                   │   │       └── excel
│                   │   │           └── EasyPoiExcelUtils.java
│                   │   ├── config
│                   │   │   ├── ehcache
│                   │   │   │   ├── constants
│                   │   │   │   │   └── EhcacheConstant.java
│                   │   │   │   └── EhCacheConfig.java
│                   │   │   ├── es
│                   │   │   │   └── ElasticsearchConfig.java
│                   │   │   ├── kafka
│                   │   │   │   └── KafkaConfig.java
│                   │   │   ├── listen
│                   │   │   │   └── SkeletionServiceContextListener.java
│                   │   │   ├── mp
│                   │   │   │   └── MybatisConfig.java
│                   │   │   ├── swagger
│                   │   │   │   └── Swagger2Config.java
│                   │   │   ├── webmvc
│                   │   │   │   └── WebConfig.java
│                   │   │   └── xxljob
│                   │   │       └── XxlJobConfig.java
│                   │   └── mp
│                   │       ├── SkeletonCommentGenerator.java
│                   │       └── SkeletonMyBatis3Impl.java
│                   ├── kafka
│                   │   ├── constants
│                   │   │   └── KafkaConstant.java
│                   │   ├── consumer
│                   │   │   └── ChatConsumer.java
│                   │   └── producer
│                   │       └── ChatProducer.java
│                   ├── ServiceApplication.java
│                   └── user
│                       ├── dao
│                       │   ├── impl
│                       │   │   └── UserMapperImpl.java
│                       │   └── UserMapper.java
│                       ├── entity
│                       │   └── User.java
│                       ├── repository
│                       │   └── UserEsRepository.java
│                       └── service
│                           ├── impl
│                           │   └── UserServiceImpl.java
│                           └── UserService.java
└── resources
    ├── application-dev.yaml
    ├── application-prod.yaml
    ├── application.yaml
    ├── banner.txt
    ├── config
    │   ├── ehcache
    │   │   └── ehcache.xml
    │   ├── es
    │   │   └── es.properties
    │   ├── kafka
    │   │   └── kafka.properties
    │   ├── logback
    │   │   ├── logback-dev.xml
    │   │   └── logback-prod.xml
    │   └── xxl-job
    │       └── xxljob.properties
    ├── mapper
    │   └── user
    │       └── UserMapper.xml
    ├── mybatis-generator
    │   ├── generatorConfig.xml
    │   └── generator.properties
    ├── sql
    │   ├── app_data.sql
    │   ├── app_schema.sql
    │   ├── app_table.sql
    │   └── xxl-job
    │       └── xxl-job.sql
    └── static
        └── favicon.ico
```

## 三、Skeleton-Webapp
```text
├── java
│   └── cn
│       └── ucaner
│           └── skeleton
│               └── webapp
│                   └── WebAppApplication.java
└── resources
    ├── application-dev.yaml
    ├── application-prod.yaml
    ├── application.yaml
    ├── banner.txt
    ├── config
    │   ├── ehcache
    │   │   └── ehcache.xml
    │   ├── es
    │   │   └── es.properties
    │   ├── logback
    │   │   ├── logback-dev.xml
    │   │   └── logback-prod.xml
    │   └── xxl-job
    │       └── xxljob.properties
    └── static
        └── favicon.ico
```

## 四、Skeleton-Gateway
```text
├── java
│   └── cn
│       └── ucaner
│           └── skeleton
│               └── gateway
│                   ├── annotation
│                   │   ├── LoginUser.java
│                   │   └── support
│                   │       └── LoginUserHandlerMethodArgumentResolver.java
│                   ├── config
│                   │   ├── filters
│                   │   │   └── CustomFilterRegistration.java
│                   │   ├── https
│                   │   │   └── HttpsConfiguration.java
│                   │   ├── interceptors
│                   │   │   └── JwtAuthInterceptor.java
│                   │   └── webmvc
│                   │       └── CoustomMvcConfigurerAdapter.java
│                   ├── controller
│                   │   ├── api
│                   │   │   └── ApiController.java
│                   │   ├── common
│                   │   │   └── CommonController.java
│                   │   └── test
│                   │       └── TestController.java
│                   ├── GateWayApplication.java
│                   └── jwt
│                       ├── filter
│                       │   └── JwtFilter.java
│                       ├── token
│                       │   └── JwtToken.java
│                       └── utils
│                           └── JwtUtil.java
└── resources
    ├── application-dev.yaml
    ├── application-prod.yaml
    ├── application.yaml
    ├── banner.txt
    ├── config
    │   ├── ehcache
    │   │   └── ehcache.xml
    │   ├── es
    │   │   └── es.properties
    │   ├── logback
    │   │   ├── logback-dev.xml
    │   │   └── logback-prod.xml
    │   └── xxl-job
    │       └── xxljob.properties
    └── static
        └── favicon.ico
```

## 五、Skeleton-Crawler
```text
├── java
│   └── cn
│       └── ucaner
│           └── skeleton
│               └── crawler
│                   ├── config
│                   │   └── xml
│                   │       └── XmlConfig.java
│                   ├── controller
│                   │   └── IndexController.java
│                   ├── CrawlerApplication.java
│                   ├── crawlers
│                   │   ├── Basic.java
│                   │   ├── CnblogCrawler.java
│                   │   └── DefaultRedisQueueEG.java
│                   ├── vo
│                   │   ├── BaseCrawlerVo.java
│                   │   ├── cnblog
│                   │   │   └── CnblogVo.java
│                   │   ├── github
│                   │   │   └── GithubRepoVo.java
│                   │   ├── oschina
│                   │   │   └── OsChinaVo.java
│                   │   └── zhifu
│                   │       └── ZhifuVo.java
│                   └── webmagic
│                       ├── controller
│                       │   └── HupuController.java
│                       ├── downloader
│                       │   └── HupuProxyProvider.java
│                       ├── kafka
│                       │   ├── consumer
│                       │   │   └── HupuKafkaConsumer.java
│                       │   └── producer
│                       │       └── HupuKafkaProducer.java
│                       ├── pipeline
│                       │   └── HupuSpiderPipeline.java
│                       ├── process
│                       │   ├── CnblogPagePageProcessor.java
│                       │   ├── GithubRepoPageProcessor.java
│                       │   └── HupuPageProcessor.java
│                       ├── scheduler
│                       │   └── HupuScheduler.java
│                       ├── task
│                       │   └── HupuCrawlerTask.java
│                       └── utils
│                           ├── IPCheckUtil.java
│                           ├── ProxyGeneratedUtil.java
│                           ├── URLGeneratedUtil.java
│                           └── UserAgentUtil.java
└── resources
    ├── application-dev.yaml
    ├── application-prod.yaml
    ├── application.properties
    ├── application.yaml
    ├── banner.txt
    ├── config
    │   ├── ehcache
    │   │   └── ehcache.xml
    │   ├── es
    │   │   └── es.properties
    │   ├── logback
    │   │   ├── logback-dev.xml
    │   │   └── logback-prod.xml
    │   ├── redisson
    │   │   └── redisson.xml
    │   └── xxl-job
    │       └── xxljob.properties
    └── static
        └── favicon.ico
```

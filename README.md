# bzShop



Spring Boot + Spring Cloud Netflix Eureka + Spring Cloud Netflix Zuul + Spring Cloud Netflix Hystrix + Spring Cloud OpenFeign + Spring Cloud Config + Spring Data Redis + Spring Data Solr + tx-lcn + logback + fastdfs 



后台：Vue + VueRouter + axios + element-ui

前台：Vue + VueRouter + axios + swiper





## 模块介绍

![在这里插入图片描述](https://img-blog.csdnimg.cn/92c728ba1f4b418fa0066832e11b4dcf.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAX-WHjOaZqOS4pOeCueWNil8=,size_12,color_FFFFFF,t_70,g_se,x_16)

- backend_content:CMS内容管理模块 服务调用方
- backend_item:CMS商品管理模块 服务调用方
- common_config: Spring Cloud config 配置中心
- common_content: CMS内容管理模块 服务提供方
- common_eureka：Spring Cloud eureka注册中心
- common_mapper：逆向工程生成的mapper
- common_pojo:逆向工程生成的pojo
- common_redis: 服务缓存模块
- common_tx_manager_client：tx_lcn客户端，分布式事务管理
- common_utils：工具模块
- common_zuul: Spring Cloud zuul 服务网关
- frontend_cart: 购物车模块
- frontend_order:订单模块
- frontend_portal: 商城门户模块
- frontend_search: 商品搜索模块
- frontend_sso: 注册与单点登录模块



## 环境部署

![在这里插入图片描述](https://img-blog.csdnimg.cn/6acfdd682c4d477c8dc69a320468c8d4.png)

ELK环境：es（上图中ELK虚拟机，开始准备都建在这个虚拟机上，后来发现虚拟机内存不够） + logstash + kibana

solr：搜索

txlcn：分布式事务管理，txlcn依赖Redis做分布式事务管理

bzRedis：商品缓存

rabbitmq：配置中心的消息中间件，从gitee仓库中取出配置文件

fastdfs: 图片存储
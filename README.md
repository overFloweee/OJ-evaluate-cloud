# OJ - 判题评测云（微服务）

#### 介绍
OJ - 判题评测云（微服务项目改造）

#### 软件架构
软件架构说明


#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1. 将本微服务项目部署在服务器上，需要先安装 docker compose V2.0

参考官方文档：
> Docker Compose Linux 安装：
> https://docs.docker.com/compose/install/linux/#install-using-the-repository


2. 安装完docker compose之后

安装 maven 进行项目打包

```shell
sudo apt install maven
```

然后在**项目根目录**进行项目打包 (如果打包速度过慢，就在setting.xml中切换国内镜像)

```shell
sudo mvn package
```

3. 执行命令安装项目环境：mysql、redis、nacos、rabbitMQ

```shell
sudo docker compose -f docker-compose.env.yaml up -d
```

4. 再将项目制作成镜像 并发布：

   由于进程在前台启动会影响我们的操作，所以加上 -d 参数让容器在后台启动：

```shell
docker compose -f docker-compose.service.yaml up -d
```

5. 试着查看下 docker 容器的状态，能够查看到所有容器的资源占用情况：
```shell
sudo docker stats
```



#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

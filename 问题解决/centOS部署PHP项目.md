# centOS部署PHP项目

## 安装宝塔Linux

```bash
yum install -y wget && wget -O install.sh http://download.bt.cn/install/install_6.0.sh && sh install.sh
```

---

### 宝塔安装成功

![image-20210620013543888](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620013543888.png)

---

### 安全组放行8888端口

---

### 使用外网面板地址访问宝塔面板

> 外网面板地址

此处是:**http://112.74.61.117:8888/864ef49a**

> 显示登陆界面

![image-20210620013919845](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620013919845.png)

> 输入默认随机账号和密码

此处是:

<u>**username: kz5xtj8n**</u>

<u>**password: a2631833**</u>

> 登陆成功后需要阅读一份协议，拉到底再同意

![image-20210620014104957](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620014104957.png)

> 进入面板

![image-20210620014159194](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620014159194.png)

> 使用LAMP的编译一键安装

![image-20210620014717926](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620014717926.png)

***关闭消息盒子即可***

> 注册绑定一个官方的宝塔账号

![image-20210620014406683](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620014406683.png)

## 还原mysql

> 点击`数据库`->`修改root密码`->`添加数据库`

### 点击数据库

![image-20210620031159326](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620031159326.png)

### 修改root密码

![image-20210620031327071](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620031327071.png)

### 添加数据库

![image-20210620031354400](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620031354400.png)

> 填入`数据库名`和`密码`,这里是terminal和123

### 导入sql文件

> 从本地上传，上传成功后点击导入，成功后关闭

![image-20210620031545995](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620031545995.png)

### 使用命令行查看数据库是否导入成功

```bash
mysql -u root -p                         # 键入登陆密码
use terminal;                            # 进入该数据库的工作区
show tables;                             # 查看表结构
```

![image-20210620031939116](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620031939116.png)

**可见，导入成功**

## 部署PHP项目

### 添加站点

![image-20210620033515241](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620033515241.png)

### 填入站点信息

> 注意：域名是域名加端口号，如果没有域名则使用（<u>IP加端口号</u>）
>
> 选中数据库，填入自己数据库信息

![image-20210620033646532](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620033646532.png)

### 添加成功

![image-20210620033826713](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620033826713.png)

### 在文件目录文件中传入源代码

即在`/www/wwwroot/home`目录下传入自己的源代码，操作在左侧`文件`中

---

### 在软件商店找到Apache重启

> 即可访问到自己的页面，比如：http://112.74.61.117/denglu.html

![image-20210620034604738](/Users/xingyuchen/OneDrive/learning-note/问题解决/centOS部署PHP项目.assets/image-20210620034604738.png)


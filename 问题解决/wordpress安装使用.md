# wordpress安装使用

## 登陆云服务器

```bash
ssh root@xxx.xxx.xxx.xxx         #ssh连接公网ip
```



## 部署环境

Apache作为后端服务器+MySQL数据库用来存储数据

### 安装Apache服务及其扩展包

```bash
yum -y install httpd httpd-manual mod_ssl mod_perl mod_auth_mysql
```

---

#### 安装过程

![image-20210619015005284](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619015005284.png)

#### 安装完毕

![image-20210619015107767](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619015107767.png)

### 启动Apache服务

```bash
systemctl start httpd.service
```

---

### 放开安全组限制

#### 在ECS实例列表页,单击本实例安全组

![image-20210619015314989](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619015314989.png)

#### 进入实例放开安全组

![image-20210619015426869](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619015426869.png)

#### 手动添加/快速添加开放的端口号

> Apache默认端口80，Mysql默认端口3306，**必须开放**

---

### 查看是否配置成功

> 打开浏览器输入ECS服务器的公网IP，如果显示如下图的测试页面表示Apache服务安装成功。

![image-20210619015856778](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619015856778.png)

---

### 搭建MySQL数据库

#### 下载并安装MySQL

```bash
wget http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql57-community-release-el7-10.noarch.rpm
yum -y install mysql-community-server
```

---

#### 启动MySQL数据库

```bash
systemctl start mysqld.service
```

成功表示

![image-20210619020235456](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619020235456.png)

---

#### 查看MySQL运行状态

```bash
systemctl status mysqld.service
```

![image-20210619020325812](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619020325812.png)

---

#### 查看MySQL初始密码

```bash
grep "password" /var/log/mysqld.log
```

![image-20210619020430817](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619020430817.png)

> localist:后面的即为随机生成的密码

#### 使用初始密码登陆

```bash
mysql -uroot -p
```

![image-20210619020644159](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619020644159.png)

> 已经成功登陆到mysql数据库中

#### 修改密码

> 新密码设置的时候如果设置的过于简单会报错，必须同时包含<u>大小写英文字母</u>、<u>数字</u>和<u>特殊符号</u>中的三类字符。

```bash
 ALTER USER 'root'@'localhost' IDENTIFIED BY '1234567Ly!';
```

> 修改成功

![image-20210619021146841](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619021146841.png)

---

#### 使用新密码登陆

- 先使用`exit;`退出之前登陆的数据库
- 再使用`mysql -uroot -p`输入新密码登陆

```bash
mysql -u root -p
```

**成功登陆**

![image-20210619021352944](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619021352944.png)

#### 创建一个存储博客网站内容的数据库

```bash
create database wordpress; 
```

**创建成功**

![image-20210619021440790](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619021440790.png)

##### 查看是否创建成功

```bash
show databases;
```

**发现存在这个数据库**

![image-20210619021532560](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619021532560.png)

```bash
exit;          #退出即可
```

---

### 配置PHP

#### 安装PHP

```bash
yum -y install php php-mysql gd php-gd gd-devel php-xml php-common php-mbstring php-ldap php-pear php-xmlrpc php-imap
```

**安装成功**

![image-20210619021755555](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619021755555.png)

#### 创建PHP测试页面

```bash
echo "<?php phpinfo(); ?>" > /var/www/html/phpinfo.php
```



#### 重启Apache服务

```bash
systemctl restart httpd
```



### 访问http://公网ip/phpinfo.php

> PHP安装成功

![image-20210619022003746](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619022003746.png)

## 安装和配置WordPress

### 安装WordPress

```bash
yum -y install wordpress
```

**安装成功**

![image-20210619022131221](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619022131221.png)

---

### 修改WordPress配置文件

#### 进入wordpress目录

> 进入/usr/share/wordpress目录

```bash
cd /usr/share/wordpress
```

---

#### 修改路径

> 修改路径

```bash
ln -snf /etc/wordpress/wp-config.php wp-config.php
```

---

#### 查看修改后的目录结构

> 查看修改后的目录结构

```bash
ll
```

---

#### 实现

![image-20210619022458874](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619022458874.png)

### 移动WordPress到Apache根目录   

> 在Apache的根目录/var/www/html下，创建一个wp-blog文件夹

```bash
mkdir /var/www/html/wp-blog
```

> 使用mv指令把wordpress移动过去

```bash
mv * /var/www/html/wp-blog/
```

---

### 修改wp-config.php配置文件

> - database_name_here为之前步骤中创建的数据库名称，本示例为wordpress。
> - username_here为MySQL数据库的用户名，本示例为root。
> - password_here为MySQL数据库的登录密码，本示例为1234567Ly!

```bash
sed -i 's/database_name_here/wordpress/' /var/www/html/wp-blog/wp-config.php
sed -i 's/username_here/root/' /var/www/html/wp-blog/wp-config.php
sed -i 's/password_here/1234567Ly!/' /var/www/html/wp-blog/wp-config.php
```

---

### 查看配置文件是否修改成功

```bash
cat -n /var/www/html/wp-blog/wp-config.php
```

### 重启Apache服务

```bash
systemctl restart httpd
```



## 创建个人站点并发布内容

> 访问http://<ECS公网IP>/wp-blog/wp-admin/install.php

### 设置信息

>设置您的站点名称、管理员用户名和密码，然后单击Install WordPress完成WordPress初始化。

![image-20210619023417623](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023417623.png)

### 登陆

> 点击Login in跳转

![image-20210619023456381](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023456381.png)

> 使用注册的账号密码登陆 我这里是root和123456

![image-20210619023548146](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023548146.png)

### 登陆成功进入后台

> 登录后，您就可以根据需要创建内容进行发布了。至此您已完成WordPress的搭建。

![image-20210619023627400](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023627400.png)

> 您可以单击Write your first blog post，开始编写您的第一篇博客。

![image-20210619023720243](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023720243.png)

> 编写完成您的第一篇博客后，单击publish发布。

![image-20210619023853363](/Users/xingyuchen/OneDrive/learning-note/问题解决/wordpress安装使用.assets/image-20210619023853363.png)

***搭建完毕***


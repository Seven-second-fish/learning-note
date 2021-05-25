# Git

## 配置

> 配置全局变量参数

```bash
git config --global user.name "xxxx"
git config --global user.email "xxx@163.com"
git config --global color.ui auto  # 让命令输出更可读
```



>设置SSH Key

```bash
ssh-keygen -t rsa -C "xxx@163.com"
=>三次回车
```



> 将私钥加到本机

``` bash
cd ~/.ssh
ssh-add id_rsa  
```



> 添加公钥

```bash
cat ~/.ssh/id_rsa.pub
添加到github的settings中去
```



> 检测认证和通信

```bash
ssh -T git@github.com
```



## 实战

### 创建仓库

> 全部通过点击实现，无需多言



### 使用仓库

> 国内镜像：加快下载速度

```bash
git clone git@github.com.cnpmjs.org/helloworld.git:
```



### 将代码提交至仓库

> 将文件存入暂存区

```bash
git add .
```



> 查看仓库状况

```bash
git status
```



> 提交到本地仓库

```bash
git commit -m "提交备注信息"
```



>查看日志

```bash
git log
```



> 更新远程仓库

``` bash
git push
```



## 实际操作学习git



### git init --初始化仓库

> 要使用Git进行版本管理，就必须要初始化仓库

```bash
mkdir git-respository
cd git-respository
git init
```

目录下会生成**.git**目录，**.git**目录存储着管理当前目录内容所需的仓库数据（<u>附属于该仓库的**工作树**</u>）。



### git status --查看仓库状态

> 工作树和仓库在被操作过程中，状态会不断发生变化

```bash
git status
```

只要对Git工作树或仓库进行操作，git status命令显示的结果就会发生变化。



### git add --向暂存区添加文件

>如果只是用Git仓库的工作树创建了文件，那么该文件不会被记入Git仓库的版本管理对象中。
>
>=>git status ->查看REDME.md的时候，他会显示在***Untracked files***（无路径的文件）里。
>
>所以，想让文件成为Git仓库的管理对象，就需要使用**git add**命令将其加入暂存区（Stage）

*什么是暂存区？*

==暂存区是提交前的一个临时区域。==

``` bash
git add REDME.md                    # 添加仓库下所有文件到暂存区使用git add .

git status

On branch master
Your branch is up to date with 'origin/master'.

Changes to be committed:  # 被提交到暂存区的文件会出现在这里
  (use "git restore --staged <file>..." to unstage)
	new file:   "git/git\347\256\200\345\215\225\345\256\236\347\224\250.md"
```

=>README.md会显示在***Changes to be commited***中



### git commit --保存仓库历史记录

> 可以将暂存区的文件实际保存到仓库的**历史记录**中。通过这些记录，我们就可以在工作树中复原文件。

#### 记述一行提交信息

```bash
git commit -m "first commit" # 格式：git commit -m "提交备注信息"

[master 479dd41] first commit
 1 file changed, 179 insertions(+)
 create mode 100644 "git/git\347\256\200\345\215\225\345\256\236\347\224\250.md"
```



#### 查看提交后的仓库状态

```bash
git status

On branch master
Your branch is ahead of 'origin/master' by 2 commits.
  (use "git push" to publish your local commits)
# 工作目录很干净，提交到本地仓库完成
nothing to commit, working tree clean
```

显示：**nothing to commit, working directory clean**(工作目录很干净）



### git log --查看提交日志

> 可以查看什么人在什么时候进行提交或者合并，以及前后操作有什么差别。

``` bash
git log

# 查看三次提交的人和时间
commit 96cfa45643c36ac76fbbd8bfd3t786906971f701 (HEAD -> master)
Author: xxx <xxx@163.cn>
Date:   Wed May 26 01:33:30 2021 +0800

    third commit

commit 47345476888d9508b768911a3487712c8ae8506d
Author: xxx <xxx@sina.cn>
Date:   Wed May 26 01:32:25 2021 +0800

    second commit

commit 8a379d83a54564421d5f47821234945172044ea2 (origin/master)
Author: xxx <xxx@sina.cn>
Date:   Tue May 25 23:56:31 2021 +0800

    first commit
```

在**commit**旁边一串"96cfa45643c..."这样的是指向提交的Hash(哈希)值，其他命令会用到这个Hash值



#### 只显示信息的第一行

> git log命令后加上-- pretty=short



#### 只显示指定目录、文件的日志

> git log命令后加上目录名，便会只显示该目录下的日志；如果加文件名，只会显示与该文件相关的日志。

``` bash
git log git

# 第一次没创git目录也没有放文件
commit 96cfa45643c36ac76fbbd8bfd3t786906971f701 (HEAD -> master)
Author: xxx <xxx@163.cn>
Date:   Wed May 26 01:33:30 2021 +0800

    third commit

commit 47345476888d9508b768911a3487712c8ae8506d
Author: xxx <xxx@sina.cn>
Date:   Wed May 26 01:32:25 2021 +0800

    second commit
```



#### 显示文件的改动

> git log命令后加上-p，文件的前后差别就会显示在提交信息之后

``` bash
git log -p

commit  96cfa45643c36ac76fbbd8bfd3t786906971f701(HEAD -> master)
Author: xxx <xxx@sina.cn>
Date:   Wed May 26 01:33:30 2021 +0800

    third commit

diff --git "a/git/git\347\256\200\345\215\225\345\256\236\347\224\250.md" "b/git
/git\347\256\200\345\215\225\345\256\236\347\224\250.md"
index 1230655..98135b8 100644
--- "a/git/git\347\256\200\345\215\225\345\256\236\347\224\250.md"
+++ "b/git/git\347\256\200\345\215\225\345\256\236\347\224\250.md"
@@ -151,7 +151,15 @@ git status
 
 ``` bash
 git add REDME.md                    # 添加仓库下所有文件到暂存区使用git add .
+
 git status
+
+On branch master
+Your branch is up to date with 'origin/master'.
+
+Changes to be committed:  #被提交到暂存区的文件会出现在这里
+  (use "git restore --staged <file>..." to unstage)
.....
```



#### 结合指定目录、文件显示改动

> git log 命令后加-p 文件名/文件夹，组合使用的到具体的修改

```bash
git log -p git


```


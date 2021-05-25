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

Changes to be committed:  #被提交到暂存区的文件会出现在这里
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


```

显示：**nothing to commit, working directory clean**(工作目录很干净）=>结果并未更改
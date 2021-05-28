# mysql5.7运行8.0版本的sql文件失败

## 显示记录

> 0行受到影响导致还原失败



## 原因

> Mysql8.0的**字符集**和**排序规则**较5.7有改动

- 表编码为 utf8mb4_0900_ai_ci
- 排序规则为utf8mb4
- utf8mb4替换为utf8



>Mysql8.0支持Json字段类型而5.7-等不能

可以将`Json`字段类型变成varchar或者text，后台暂时没有报错信息



## 解决方案

> 打开.sql文件 使用notpad++等软件去全局替换,这里我使用HBuilder

- utf8mb4_0900_ai_ci 替换为 utf8_general_ci

![image-20210528094648027](image-20210528094648027.png)

- utf8_croatian_ci替换为utf8_general_ci

![image-20210528094722417](image-20210528094722417.png)

- utf8mb4_general_ci替换为utf8_general_ci

![image-20210528094758929](image-20210528094758929.png)

- utf8mb4 替换为 utf8

![image-20210528095013082](image-20210528095013082.png)



***多数情况，只需要对最后一步<u>utf8mb4 替换为 utf8</u>操作即可***


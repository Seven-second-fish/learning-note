# JWT学习

## 什么是JWT

JSON Web Token，通过数字签名的方式，以JSON对象为载体，在不同服务终端之间安全的传输信息。

---

## JWT有什么用

JWT最常见的场景就是授权认证，一旦用户登陆，后续每个请求都将包含JWT，系统在每次处理用户请求的之前，都要进行JWT安全校验，通过之后再进行处理。

---

## JWT的组成

> JWT由3部分组成，用`.`拼接

![image-20210627130510056](15分钟学会JWT.assets/image-20210627130510056.png)

- Header(Token类型和算法名称)

  ![image-20210627130544237](15分钟学会JWT.assets/image-20210627130544237.png)

- Payload(存放有效信息的地方)

  ![image-20210627130637340](15分钟学会JWT.assets/image-20210627130637340.png)

- Signature

  ```js
  var encodedString = base64UrlEncode(header)+'.'+base64UrlEncode(payload);
  
  var signature=HMACSHA256(encodedString,'secret');
  ```

## 实战

### pom.xml

```xml
<dependencies>
        <!-- 单元测试-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!--jwt-->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>
        <!--1.8+jdk需要的额外配置-->
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-impl</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
            <artifactId>jaxb-core</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1</version>
        </dependency>
    </dependencies>
```

---

### 使用单元测试

> 加密

```java
 @org.junit.Test
    public void jwt(){
        //获取JWT builder，用于构建JWT对象
        JwtBuilder jwtBuilder= Jwts.builder();
        //创建一个JWT Token字符串
        String jwtToken=jwtBuilder
                //设置jwt对象的header头信息
                //设置header的类型
                .setHeaderParam("typ","JWT")
                //设置header的算法
                .setHeaderParam("alg","HS256")
                //payload载荷
                .claim("username","tom")
                //角色
                .claim("role","admin")
                //jwt主题,可以自定义
                .setSubject("admin-test")
                //设置有效时间
                .setExpiration(new Date(System.currentTimeMillis()+60*60*24))
                //设置jwt的ID
                .setId(UUID.randomUUID().toString())
                //signature admin是自定义用于解密的签名
                .signWith(SignatureAlgorithm.HS256,"admin")
                //三部分拼接
                .compact();
        System.out.println(jwtToken);
    }
```

```bash
# 运行结果
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2MjQ3OTEwNjYsImp0aSI6IjA2NmE1ZmNmLTNhNDQtNGRmNi1iYWQyLTNhMzFiNjM3ZmFiMiJ9.aVoB1zdgGofuW_v9bLGdDVBrz-Z-e8l7xWay6EUDlSs
```

> 解密

```java
@org.junit.Test
    public void parse(){
        String token=
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2MjQ3OTMyMzQsImp0aSI6IjgzNTUxZGVmLTE4OTctNDM0My05NjMwLTE0OTZjMjU1ZDA3NCJ9.WH83K2sRiU_tpeIxKz3HjJARmABukQkQjsJ5YCjSywM";
        //获取jwt解密对象
        JwtParser jwtParser=Jwts.parser();
        //加入解密的key
        Jws<Claims> admin = jwtParser.setSigningKey("admin").parseClaimsJws(token);
        Claims body = admin.getBody();
        System.out.println(body.get("username"));
        System.out.println(body.get("role"));
        System.out.println(body.getId());
        System.out.println(body.getSubject());
    }
```

```bash
#解密结果
tom
admin
83551def-1897-4343-9630-1496c255d074
admin-test
```




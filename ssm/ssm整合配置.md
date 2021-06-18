# SSM整合配置

## 开发规划

### 环境要求

Idea,Mysql5.7,Tomcat 9,Maven 3.8,Lombok

---



### 配置流程

项目搭建->MySQL->Mybatis->Spring->SpringMVC->web.xml

---



### 搭建项目目录

- com.cxy.pojo 实体类(映射数据表)
- com.cxy.mapper 数据操作层(数据库CRUD)
- com.cxy.service 业务层
- com.cxy.controller 控制层

---

### 配置文件

#### 项目搭建

- pom.xml中导入jar包
- pom.xml中设置Maven资源过滤

---



#### MySQL

- 搭建数据库和数据表

---



#### Mybatis

##### mybatis-config.xml

> 配置、连接、绑定、操作数据库

- 用于配置数据源（交给Spring管理）
- 日志输出
- 数据库相关配置
- 数据关联：数据表与pojo关联
- 数据操作：与mapper（Dao）层的mapper.xml关联CRUD操作

---



##### database.properties

> 用于配置数据库连接的用户名和密码

---

#### Spring

##### applicationContext.xml

> Spring 整合配置文件

- 用于整合spring-dao.xml,spring-service.xml,spring-mvc.xml

---



##### spring-dao.xml

> 与dao层关联

- 关联数据库配置文件:database.properties
- 配置数据库连接池
- 配置SqlSessionFactory对象，绑定mybatis-config.xml文件
- 配置扫描dao层包，动态实现dao接入注入spring容器

---



##### spring-service.xml

> 与service层关联

- 配置扫描service层包
- 将service层注入实现类注入IOC容器
- 配置事务管理器
- 配置AOP事务支持

---



#### SpringMVC

#### spring-mvc.xml

> 与controller关联

- 开启注解扫描，解决json乱码问题
- 配置静态资源默认servlet处理
- 配置视图解析器
- 配置扫描controller层包

---



#### web.xml

- 配置SpringMVC分发器：DispatcherServlet
- 配置乱码过滤
- 配置Session过期时间

---

## 配置数据库

![image-20210610191644474](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210610191644474.png)

### database.properties

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/student_manager?useSSL=true&useUnicode=true&characterEncoding=utf8
jdbc.username=root
jdbc.password=123456
```



## 搭建项目目录

![image-20210610191811911](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210610191811911.png)



## 用maven导入jar包

```xml
<dependencies>
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.18</version>
        </dependency>
        <!--Junit白盒测试-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <!--数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
        </dependency>
        <!-- 数据库连接池 -->
        <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>

        <!--Servlet - JSP -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <!--Mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>

        <!--Spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.3.4</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.3.4</version>
        </dependency>
        <!--AOP-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.9</version>
        </dependency>
        <!--fastjson-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.62</version>
        </dependency>
<!--        log4j-->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
    </dependencies>
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

## Mybatis

### mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true" />
        <setting name="logImpl" value="LOG4J" />
    </settings>
    <typeAliases>
        <package name="com.cxy.pojo"/>
    </typeAliases>
    <mappers>
        <mapper resource="com/cxy/mapper/mapperXml/UsersMapper.xml"/>
        <mapper resource="com/cxy/mapper/mapperXml/InstituteMapper.xml"/>
        <mapper resource="com/cxy/mapper/mapperXml/DomainsMapper.xml"/>
    </mappers>
</configuration>
```



## Spring

### spring-dao.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

        <!-- 配置整合mybatis -->
        <!-- 1.关联数据库文件 -->
        <context:property-placeholder location="classpath:database.properties"/>

        <!-- 2.数据库连接池 -->
        <!--数据库连接池
        dbcp 半自动化操作 不能自动连接
        c3p0 自动化操作（自动的加载配置文件 并且设置到对象里面）
-->
        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <!-- 配置连接池属性 -->
            <property name="driverClass" value="${jdbc.driver}"/>
            <property name="jdbcUrl" value="${jdbc.url}"/>
            <property name="user" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>

            <!-- c3p0连接池的私有属性 -->
            <property name="maxPoolSize" value="30"/>
            <property name="minPoolSize" value="10"/>
            <!-- 关闭连接后不自动commit -->
            <property name="autoCommitOnClose" value="false"/>
            <!-- 获取连接超时时间 -->
            <property name="checkoutTimeout" value="10000"/>
            <!-- 当获取连接失败重试次数 -->
            <property name="acquireRetryAttempts" value="2"/>
        </bean>

        <!-- 3.配置SqlSessionFactory对象 -->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="dataSource"/>
            <!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
            <property name="configLocation" value="classpath:mybatis-config.xml"/>

        </bean>

        <!-- 4.配置扫描Dao接口包，动态实现Dao接口注入到spring容器中 -->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!-- 注入sqlSessionFactory -->
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
            <!-- 给出需要扫描Dao接口包 -->
            <property name="basePackage" value="com.cxy.mapper"/>
        </bean>
</beans>
```

### spring-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd

">

        <!-- 扫描service相关的bean -->
        <context:component-scan base-package="com.cxy.service" />

        <!--把ServiceImpl注入到IOC容器中-->
        <bean id="usersServiceImpl" class="com.cxy.service.impl.UsersServiceImpl">
            <property name="usersMapper" ref="usersMapper"/>
        </bean>
        <bean id="domainsServiceImpl" class="com.cxy.service.impl.DomainsServiceImpl">
            <property name="domainsMapper" ref="domainsMapper"/>
        </bean>
        <bean id="instituteServiceImpl" class="com.cxy.service.impl.InstituteServiceImpl">
            <property name="instituteMapper" ref="instituteMapper"/>
        </bean>

        <!-- 配置事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <!-- 注入数据库连接池 -->
            <property name="dataSource" ref="dataSource" />
        </bean>

</beans>
```

### spring-mvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:mvc="http://www.springframework.org/schema/mvc"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc
        https://www.springframework.org/schema/mvc/spring-mvc.xsd">

        <!-- 配置SpringMVC -->
        <!-- 1.开启SpringMVC注解驱动 -->
        <mvc:annotation-driven >
            <mvc:message-converters>
                <bean id="fastJsonHttpMessageConverter" class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                    <property name="supportedMediaTypes">
                        <list>
                            <value>text/html;charset=UTF-8</value>
                            <value>application/json;charset=UTF-8</value>
                        </list>
                    </property>
                </bean>
            </mvc:message-converters>
        </mvc:annotation-driven>

        <!-- 2.静态资源默认servlet配置-->
        <mvc:default-servlet-handler/>

        <!-- 3.配置jsp 显示ViewResolver视图解析器 -->
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
         <property name="prefix" value="/WEB-INF/jsp/"/>
         <property name="suffix" value=".jsp"/>
        </bean>

        <!-- 4.扫描web相关的bean -->
        <context:component-scan base-package="com.cxy.controller" />
        <mvc:resources location="/static/" mapping="/static/**"/>

</beans>
```

### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="spring-dao.xml"/>
    <import resource="spring-service.xml"/>
    <import resource="spring-mvc.xml"/>
</beans>
```

## web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--1.配置SpringMVC分发器：DispatcherServlet-->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!--2.乱码过滤encodingFilter-->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!--3.配置Session过期时间，15分钟-->
    <session-config>
        <session-timeout>15</session-timeout>
    </session-config>
</web-app>
```

## log4j.properties

```properties
#//注意这里不要用INFO，DEBUG等 改个名字
log4j.rootLogger=infoA,errorA,stdout,DEBUGA

#过滤掉spring框架下的额外日志
log4j.category.org.springframework = WARN

#输出到控制台
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.Threshold = debug
log4j.appender.stdout.layout.ConversionPattern=%d %-5p %c{1}:%L - %m%n
#c3p0
log4j.logger.com.mchange.v2.async.ThreadPoolAsynchronousRunner = ERROR  
#打印sql语句
log4j.logger.com.ibatis=DEBUG
log4j.logger.java.sql.ResultSet=INFO
log4j.logger.com.ibatis.common.jdbc.SimpleDataSource=DEBUG
log4j.logger.com.ibatis.common.jdbc.ScriptRunner=DEBUG
log4j.logger.com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate=DEBUG
log4j.logger.java.sql.Connection=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

## 项目结构

![image-20210616002430862](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210616002430862.png)

### 实体类User

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private int uId;
    private String uName;
    private int uAge;
    private int uStatus;
    private int uRole;
    private String uPassword;
    private int dId;
    private int iId;
    private String iName;
    private String dName;
}
```

### Mapper(Dao层)

```java
@Mapper
public interface UsersMapper {
    //用户管理的时候需要查看所有用户
    List<Users> selectAllUsers();

    //用户的登陆的验证
    Users selectUsersLogin(@Param("uName") String uName, @Param("uPassword") String uPassword);

    //验证用户是否存在
    int selectUserIsExit(@Param("uName") String uName);

    //注册/新增一个新用户
    int insertNewUser(@Param("users") Users users);

    //连表查询的查询所有用户
    List<Users> selectAllUsers1();

    //删除某一个用户
    int deleteOneUser(@Param("uId") int uId);

    //获取未审核的所有用户
    List<Users> selectNoPass();

    //审核某一用户
    int updateUser(@Param("uId") int uId);

    //查询某一个用户
    Users selectUserById(@Param("uId")int uId);

    //修改用户信息
    int upadateUserMsg(@Param("users")Users users);
}
```

### mapper.xml

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.cxy.mapper.UsersMapper">
  <!--换成Users类的全限定名-->
    <select id="selectAllUsers" resultType="com.cxy.pojo.Users">
        select * from users
    </select>
    <select id="selectUsersLogin" resultType="Users" parameterType="java.lang.String">
        select u_id,u_name,u_role,u_password,u_status,u_age,d_id,i_id from users where u_name=#{uName} and u_password=#{uPassword} and u_status=1;
    </select>
    <select id="selectUserIsExit" resultType="int" parameterType="Users">
        select count(*) from users where u_name=#{uName};
    </select>
    <insert id="insertNewUser" parameterType="Users">
        insert into users(u_name,u_age,u_password,d_id,i_id) values(#{users.uName},#{users.uAge},#{users.uPassword},#{users.dId},#{users.iId});
    </insert>
    <select id="selectAllUsers1" resultType="Users" >
        select * from users as u,institute as i,domains as d
        where u.i_id=i.i_id and u.d_id=d.d_id
    </select>
    <delete id="deleteOneUser" parameterType="int">
        delete from users where u_id=#{uId}
    </delete>
    <select id="selectNoPass" resultType="Users">
        select * from users where u_status=0;
    </select>
    <update id="updateUser" parameterType="int">
        UPDATE users
        SET u_status=1
        WHERE u_id=#{uId};
    </update>
    <select id="selectUserById" parameterType="int" resultType="Users">
        select * from users as u,institute as i,domains as d
        where u.i_id=i.i_id and u.d_id=d.d_id and u.u_id=#{uId}
    </select>
    <update id="upadateUserMsg" parameterType="Users" >
        update users set u_name=#{users.uName},u_age=#{users.uAge},u_status=#{users.uStatus},d_id=#{users.dId},i_id=#{users.iId} where u_id=#{users.uId}
    </update>
</mapper>
```

#### 注意

![image-20210616005213540](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210616005213540.png)

![image-20210616005227080](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210616005227080.png)

- ResultType要使用**全限定类名**
- 查询不能使用***** 去查询
- **int**类型的数据要是用**Integer** 

### userService(Service层)

```java
public interface UsersService {
    //用户管理的时候需要查看所有用户
    List<Users> selectAllUsers();

    //用户的登陆的验证
    Users selectUsersLogin(String uName,String uPassword);

    //验证用户是否存在
    int selectUserIsExit(String uName);
    //注册/新增一个新用户
    int insertNewUser(Users users);

    //连表查询的查询所有用户
    List<Users> selectAllUsers1();

    //删除某一个用户
    int deleteOneUser(int uId);

    //获取未审核的所有用户
    List<Users> selectNoPass();

    //审核某一用户
    int updateUser(int uId);

    //查询某一个用户
    Users selectUserById(int uId);

    //修改用户信息
    int upadateUserMsg(Users users);
}
```

### userServiceImpl

```java
@Service
public class UsersServiceImpl implements UsersService {

    private UsersMapper usersMapper;

    public void setUsersMapper(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }


    @Override
    public List<Users> selectAllUsers() {
        return usersMapper.selectAllUsers();
    }

    @Override
    public Users selectUsersLogin(String uName, String uPassword) {
        return usersMapper.selectUsersLogin(uName,uPassword);
    }

    @Override
    public int selectUserIsExit(String uName) {
        return usersMapper.selectUserIsExit(uName);
    }

    @Override
    public int insertNewUser(Users users) {
        return usersMapper.insertNewUser(users);
    }

    @Override
    public List<Users> selectAllUsers1() {
        return usersMapper.selectAllUsers1();
    }

    @Override
    public int deleteOneUser(int uId) {
        return usersMapper.deleteOneUser(uId);
    }

    @Override
    public List<Users> selectNoPass() {
        return usersMapper.selectNoPass();
    }

    @Override
    public int updateUser(int uId) {
        return usersMapper.updateUser(uId);
    }

    @Override
    public Users selectUserById(int uId) {
        return usersMapper.selectUserById(uId);
    }

    @Override
    public int upadateUserMsg(Users users) {
        return usersMapper.upadateUserMsg(users);
    }
}
```

## 测试

![image-20210616022405371](/Users/xingyuchen/OneDrive/learning-note/ssm/ssm整合配置.assets/image-20210616022405371.png)

```java
public class TestNew {
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UsersServiceImpl usersService=(UsersServiceImpl) ctx.getBean("usersServiceImpl");
        //DomainsServiceImpl domainsService=(DomainsServiceImpl) ctx.getBean("domainsServiceImpl");
        Users users = usersService.selectUserById(2);
        System.out.println(users);
    }
}
```


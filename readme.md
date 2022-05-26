##### 0. web.xml

+ 하나씩 주석 해제하며 사용

```
<servlet>
    <servlet-name>appServlet</servlet-name>
    <servlet-class> org.springframework.web.servlet.DispatcherServlet </servlet-class>
    <init-param>
        <param-name> contextConfigLocation </param-name>
        <param-value>
            /WEB-INF/spring/appServlet/servlet-context.xml
            <!-- sqlsessiontemplate -->
            <!-- /WEB-INF/spring/mybatis-context1.xml -->
            <!-- mybatis-spring:scan -->
            <!-- /WEB-INF/spring/mybatis-context2.xml -->
            <!-- sqlsessiontemplate + MapperScannerConfigurer -->
            <!-- /WEB-INF/spring/mybatis-context3.xml -->
        </param-value>
    </init-param>
<load-on-startup> 1 </load-on-startup>
</servlet>
```
##### 1. mybatis-context1.xml
+ sqlSessionTemplate 사용
```
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/demodb?characterEncoding=UTF-8" />
    <property name="username" value="root" />
    <property name="password" value="1234" />
</bean>

<!-- use sqlSessionTemplate -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="configLocation" value="classpath:/mybatis-config.xml"></property>
    <property name="mapperLocations" value="classpath:/mybatis/**/*.xml"></property>
</bean>

<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
    <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>
```
##### 2. mybatis-context2.xml
+ mybatis-spring:scan 사용
```
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/demodb?characterEncoding=UTF-8" />
    <property name="username" value="root" />
    <property name="password" value="1234" />
</bean>

<!-- use mybatis-spring:scan -->

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
<!-- mapper.xml을 사용할 경우에는 VO의 alias를 정의할 config도 포함 시켜야하고 @Select와 같은 어노테이션으로 
작성할 경우 아래 구문들은 필요가 없다 -->
    <property name="configLocation" value="classpath:/mybatis-config.xml"></property>
    <property name="mapperLocations" value="classpath:/mybatis/**/*.xml"></property>
</bean>

<mybatis-spring:scan base-package="com.myspring.myspring"/>
```

##### 3. mabatis-context3.xml
+ MapperScannerConfigurer 사용
```
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://localhost:3306/demodb?characterEncoding=UTF-8" />
    <property name="username" value="root" />
    <property name="password" value="1234" />
</bean>

<!-- use MapperScannerConfigurer -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="configLocation" value="classpath:/mybatis-config.xml"></property>
    <property name="mapperLocations" value="classpath:/mybatis/**/*.xml"></property>
</bean>

<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
    <qualifier value="sqlSessionTemplate"></qualifier>
    <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>

<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.myspring.myspring" />
    <!-- <property name="annotationClass" value="org.apache.ibatis.annotations.Mapper"/> -->
</bean>
```
etc.<br>dataSource 여러 개 사용에 대한 @Autowired @Qaulifier 사용이 익숙해지고 dataSoruce, sqlSessionFactory, sqlSessionTemplate
name? ref? 변경이 익숙해지면 하나도 합쳐볼 예정
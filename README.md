## Intro
Green-forest Framework is the simple IoC Container with 
<a href='http://htmlpreview.github.io/?https://github.com/edolganov/green-forest/blob/master/reference-guide/single-page.html#action-handler'>Action-Handler architecture</a>. 
It's not a competitor for Spring Framework or JEE but it's a powerful addition for these frameworks. 
You can use Green-forest for a single class implementation and for a complex business logic.


## Motivation
What is the motivation for using Green-forest Framework? 
The main task is to simplify the code: transfer of one big class 
(some service implementation) to a set of small classes (handlers).

## Code Example
```java
//create Green-forest Engine
Engine engine = new Engine();
 
//register some handler
engine.putHandler(SomeActonHandler.class);
 
//invoke some action and get result
String result = engine.invoke(new SomeAction("some data"));


//implement a business logic
@Mapping(SomeAction.class)
public class SomeActonHandler extends Handler<SomeAction>{
 
    @Inject
    SomeService service;
     
    public void invoke(SomeAction action) throws Exception {
     
        String input = action.getInput();
        String result = service.someWork(input);
         
        action.setOutput(result);
    }
}
```


## Reference Guide
[See Reference Guide](http://htmlpreview.github.io/?https://github.com/edolganov/green-forest/blob/master/reference-guide/single-page.html)

## Articles

en: [Introduction to the Green-forest Framework](http://www.codeproject.com/Articles/537867/Introduction-to-the-Green-forest-Framework)

рус: [Знакомство с Green-forest Framework](http://habrahabr.ru/post/168855/)



## JEE Integration
```java
@Singleton
public class ActionServiceImpl implements ActionService {
     
    @PersistenceContext
    EntityManager entityManager;
     
    Engine engine;
     
    @PostConstruct
    public void createEngine(){
        engine = new Engine();
        engine.addToContext(entityManager);
        engine.scanAndPut("foo.blah");
    }
    
    //Single API method
    public <I, O> O invoke(Action<I, O> action) {
        return (O) engine.invoke(action);
    }
     
}
```

[More details](http://htmlpreview.github.io/?https://github.com/edolganov/green-forest/blob/master/reference-guide/single-page.html#p7)

## Spring Integration
```xml
<bean id="engineBean" class="com.gf.core.Engine">
  <property name="scanAndPut">
      <list>
          <value>foo.blah</value>
      </list>
  </property>
  <property name="contextObjects">
      <list>
          <ref bean="someBean"/>
          <ref bean="otherBean"/>
      </list>
  </property>
  <property name="filterTypes">
      <list>
          <value>bar.baz.SomeFilter</value>
      </list>
  </property>
</bean>
```

[More details](http://htmlpreview.github.io/?https://github.com/edolganov/green-forest/blob/master/reference-guide/single-page.html#p8)


## Android
Check out [Easy-Android project](https://github.com/edolganov/easy-android)


## Install
### Download from site
Get the latest version of Green-forest Framework from download page. 
The zip archive with name like "green-forest-X.X-pack.zip" 
contains binaries, javadocs and sources. Green-forest has no dependencies for itself. 
Add the library to your Project. 

### For Maven project
Add to your pom.xml this dependency: 
```xml
<dependencies>
    <dependency>
        <groupId>com.gf</groupId>
        <artifactId>green-forest</artifactId>
        <version>0.9</version>
    </dependency>
</dependencies>
  
<repositories>
    <repository>
        <id>green-forest-repo</id>
        <url>http://green-forest.googlecode.com/svn/repo/</url>
    </repository>
</repositories>
```

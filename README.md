# Kotlin spring-boot nested config props [![CI](https://github.com/daggerok/kotlin-nested-config-props/actions/workflows/ci.yaml/badge.svg)](https://github.com/daggerok/kotlin-nested-config-props/actions/workflows/ci.yaml)
This repository demonstrates how Kotlin can simplify Spring Boot configuration properties file mapping 

```kotlin
@ConstructorBinding
@ConfigurationProperties("my.nested.config.props")
data class MyNestedConfigProps(
    val parentField1: String = "undefined",
    val parentField2: String = "undefined",
    val child: Child = Child(),
)

data class Child(
    val childField1: String = "undefined",
    val childField2: String = "undefined",
)

@SpringBootApplication
@EnableConfigurationProperties(MyNestedConfigProps::class)
class MainApp

fun main(args: Array<String>) {
    runApplication<MainApp>(*args)
}
```

map to next zapplication.properties` file:

```properties
my.nested.config.props.parent-filed-1=parent-field-1-value
my.nested.config.props.parent-filed-2=parent-field-2-value
my.nested.config.props.child.child-filed-1=child-field-1-value
my.nested.config.props.child.child-filed-2=child-field-2-value
```

or to next `application.yaml` file:

```yaml
my.nested.config.props:
  parent-field-1: parent-field-1-value
  parent-field-2: parent-field-2-value
  child:
    child-field-1: child-field-1-value
    child-field-2: child-field-2-value
```

<!--

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.5/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#configuration-metadata-annotation-processor)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

-->

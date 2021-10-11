package com.github.daggerok.kotlinnestedconfigprops

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ConstructorBinding
@ConfigurationProperties("my.nested.config.props")
data class MyNestedConfigProps(
    val parentField1: String = "undefined",
    val parentField2: String = "undefined",
    val child: Child = Child(),
)/* {
    init {
        listOf(parentField1, parentField1).forEach {
            require(it == "undefined") { "value should not be undefined" }
        }
    }
}*/

data class Child(
    val childField1: String = "undefined",
    val childField2: String = "undefined",
)/* {
    init {
        listOf(childField1, childField1).forEach {
            require(it == "undefined") { "value should not be undefined" }
        }
    }
}*/

@RestController
@SpringBootApplication
@EnableConfigurationProperties(MyNestedConfigProps::class)
class KotlinNestedConfigPropsApplication(private val myNestedConfigProps: MyNestedConfigProps) {

    @GetMapping
    @ResponseStatus(OK)
    fun getConfigProps() = myNestedConfigProps
}

fun main(args: Array<String>) {
    runApplication<KotlinNestedConfigPropsApplication>(*args)
}

package com.github.daggerok.kotlinnestedconfigprops

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpStatus

@TestInstance(PER_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayNameGeneration(ReplaceUnderscores::class)
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
class KotlinNestedConfigPropsApplicationTests @Autowired constructor(
    val myNestedConfigProps: MyNestedConfigProps,
    val restTemplate: TestRestTemplate,
    @LocalServerPort val port: Int,
) {

    @Test
    fun test_inject() {
        // then
        assertThat(myNestedConfigProps.parentField1).isEqualTo("test-1")
        assertThat(myNestedConfigProps.parentField2).isEqualTo("test-2")
        assertThat(myNestedConfigProps.child.childField1).isEqualTo("test-3")
        assertThat(myNestedConfigProps.child.childField2).isEqualTo("test-4")
    }

    @Test
    fun test_rest_api() {
        // when
        val response = restTemplate.exchange<MyNestedConfigProps>("http://127.0.0.1:$port", GET)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)

        // and
        val props = response.body ?: fail("body is null")
        assertThat(props.parentField1).isEqualTo("test-1")
        assertThat(props.parentField2).isEqualTo("test-2")
        assertThat(props.child.childField1).isEqualTo("test-3")
        assertThat(props.child.childField2).isEqualTo("test-4")
    }
}

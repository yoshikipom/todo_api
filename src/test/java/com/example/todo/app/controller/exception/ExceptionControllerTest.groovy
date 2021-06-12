package com.example.todo.app.controller.exception

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "BadRequest_success"() {
        def expected = '{"code":400000,"message":"error"}'

        when:
        def actual = mockMvc.perform(MockMvcRequestBuilders.get("/error/badrequest")).andReturn().getResponse()
        then:
        actual.getStatus() == HttpStatus.BAD_REQUEST.value
        actual.getContentAsString() == expected
    }

    def "Error_success"() {
        def expected = '{"code":500000,"message":"Internal Server Error"}'
        expect:
        def actual = mockMvc.perform(MockMvcRequestBuilders.get("/error/error")).andReturn().getResponse()
        actual.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()
        actual.getContentAsString() == expected
    }
}

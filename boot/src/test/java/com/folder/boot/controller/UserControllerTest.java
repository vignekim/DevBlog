package com.folder.boot.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;
import com.folder.boot.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired MockMvc mockMvc;
  @MockBean UserService userService;
  @Autowired ObjectMapper objectMapper;

  /****************************************************
   * 1단계 : given  - 시나리오 진행에 필요한 조건 설정
   * 2단계 : when   - 시나리오를 진행 시 필요한 변화를 명시
   * 3단계 : then   - 시나리오를 완료했을 때 예상되는 결과를 명시
   ****************************************************/

  int no = 1;
  String name = "folder";
  String email = "folder@email.com";
  String pwd = "1234";

  @Test @DisplayName("Login Test")
  void test1() throws Exception {
    // 1단계 (given)
    User user = User.builder().email(email).pwd(pwd).build();
    ResponseResult responseResult = ResponseResult.builder()
        .state(false).result(null).message(null).build();
    given(userService.login(user)).willReturn(responseResult);

    // 2단계 (when)
    String content = objectMapper.writeValueAsString(user);
    ResultActions resultActions = mockMvc.perform(
        post("/User/Login")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
    );

    // 3단계 (then)
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.state", notNullValue()))
      .andExpect(jsonPath("$.result", nullValue()))
      .andExpect(jsonPath("$.message", nullValue()));
    verify(userService).login(user);
  }

  @Test @DisplayName("findById Test")
  void test2() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).build();
    ResponseResult responseResult = ResponseResult.builder()
        .state(false).result(null).message(null).build();
    given(userService.findById(user)).willReturn(responseResult);

    // 2단계 (when)
    String content = objectMapper.writeValueAsString(user);
    ResultActions resultActions = mockMvc.perform(
        post("/User/findById")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
    );

    // 3단계 (then)
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.state", notNullValue()))
      .andExpect(jsonPath("$.result", nullValue()))
      .andExpect(jsonPath("$.message", nullValue()));
    verify(userService).findById(user);
  }

  @Test @DisplayName("editById Test")
  void test3() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).name(name).email(email).pwd(pwd).build();
    ResponseResult responseResult = ResponseResult.builder()
        .state(false).result(null).message(null).build();
    given(userService.editById(user)).willReturn(responseResult);

    // 2단계 (when)
    String content = objectMapper.writeValueAsString(user);
    ResultActions resultActions = mockMvc.perform(
        post("/User/editById")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
    );

    // 3단계 (then)
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.state", notNullValue()))
      .andExpect(jsonPath("$.result", nullValue()))
      .andExpect(jsonPath("$.message", nullValue()));
    verify(userService).editById(user);
  }

  @Test @DisplayName("deleteById Test")
  void test4() throws Exception {
    // 1단계 (given)
    ResponseResult responseResult = ResponseResult.builder()
        .state(false).result(null).message(null).build();
    given(userService.deleteById(no)).willReturn(responseResult);

    // 2단계 (when)
    ResultActions resultActions = mockMvc.perform(
      delete("/User/deleteById").param("no", no + "")
    );

    // 3단계 (then)
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.state", notNullValue()))
      .andExpect(jsonPath("$.result", nullValue()))
      .andExpect(jsonPath("$.message", nullValue()));
    verify(userService).deleteById(no);
  }

  @Test @DisplayName("save Test")
  void test5() throws Exception {
    // 1단계 (given)
    User user = User.builder().name(name).email(email).pwd(pwd).build();
    User resultUser = User.builder().no(no).name(name).email(email).pwd(pwd).build();
    ResponseResult responseResult = ResponseResult.builder()
          .state(false).result(resultUser).message(null).build();
    given(userService.save(user)).willReturn(responseResult);

    // 2단계 (when)
    String content = objectMapper.writeValueAsString(user);
    ResultActions resultActions = mockMvc.perform(
        put("/User/save")
          .content(content)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
    );

    // 3단계 (then)
    resultActions.andExpect(status().isOk())
      .andExpect(jsonPath("$.state", notNullValue()))
      .andExpect(jsonPath("$.result", notNullValue()))
      .andExpect(jsonPath("$.message", nullValue()));
    verify(userService).save(user);
  }

}

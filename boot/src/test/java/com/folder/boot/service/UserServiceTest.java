package com.folder.boot.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.folder.boot.dao.UserDao;
import com.folder.boot.dto.ResponseResult;
import com.folder.boot.dto.User;

@ExtendWith(SpringExtension.class)
@Import({UserService.class, UserDao.class})
public class UserServiceTest {

  @MockBean UserDao userDao;
  @Autowired UserService userService;

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
    User resultUser = User.builder().no(no).name(name).build();
    Mockito.when(userDao.login(user)).thenReturn(resultUser);

    // 2단계 (when)
    ResponseResult responseResult = userService.login(user);
    User result = (User) responseResult.getResult();

    // 3단계 (then)
    Assertions.assertEquals(responseResult.isState(), true);
    Assertions.assertEquals(result.getNo(), no);
    Assertions.assertEquals(result.getName(), name);
    verify(userDao, atLeastOnce()).login(user);
  }

  @Test @DisplayName("findById Test")
  void test2() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).build();
    User resultUser = User.builder().no(no).name(name).email(email).pwd(pwd).build();
    Mockito.when(userDao.findById(user)).thenReturn(resultUser);

    // 2단계 (when)
    ResponseResult responseResult = userService.findById(user);
    User result = (User) responseResult.getResult();

    // 3단계 (then)
    Assertions.assertEquals(responseResult.isState(), true);
    Assertions.assertEquals(result.getNo(), no);
    Assertions.assertEquals(result.getName(), name);
    Assertions.assertEquals(result.getEmail(), email);
    Assertions.assertEquals(result.getPwd(), pwd);
    verify(userDao, atLeastOnce()).findById(user);
  }

  @Test @DisplayName("editById Test")
  void test3() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).name(name).email(email).pwd(pwd).build();
    Mockito.when(userDao.editById(user)).thenReturn(1);

    // 2단계 (when)
    ResponseResult responseResult = userService.editById(user);

    // 3단계 (then)
    Assertions.assertEquals(responseResult.isState(), true);
    Assertions.assertEquals(responseResult.getMessage(), "사용자 수정이 성공 하였습니다.");
    verify(userDao, atLeastOnce()).editById(user);
  }

  @Test @DisplayName("deleteById Test")
  void test4() throws Exception {
    // 1단계 (given)
    Mockito.when(userDao.deleteById(no)).thenReturn(1);

    // 2단계 (when)
    ResponseResult responseResult = userService.deleteById(no);

    // 3단계 (then)
    Assertions.assertEquals(responseResult.isState(), true);
    Assertions.assertEquals(responseResult.getMessage(), "사용자 삭제가 성공 하였습니다.");
    verify(userDao, atLeastOnce()).deleteById(no);
  }

  @Test @DisplayName("save Test")
  void test5() throws Exception {
    // 1단계 (given)
    User user = User.builder().name(name).email(email).pwd(pwd).build();
    User resultUser = User.builder().no(no).name(name).email(email).pwd(pwd).build();

    Mockito.when(userDao.save(user)).thenReturn(resultUser);

    // 2단계 (when)
    ResponseResult responseResult = userService.save(user);
    User result = (User) responseResult.getResult();

    // 3단계 (then)
    Assertions.assertEquals(responseResult.isState(), true);
    Assertions.assertEquals(result.getNo(), no);
    Assertions.assertEquals(result.getName(), name);
    Assertions.assertEquals(result.getEmail(), email);
    Assertions.assertEquals(result.getPwd(), pwd);
    verify(userDao, atLeastOnce()).save(user);
  }

}

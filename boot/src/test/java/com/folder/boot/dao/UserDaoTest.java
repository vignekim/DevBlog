package com.folder.boot.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.folder.boot.dto.User;

@SpringBootTest
public class UserDaoTest {

  @Autowired UserDao userDao;

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

    // 2단계 (when)
    User result = userDao.login(user);

    // 3단계 (then)
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getNo(), no);
    Assertions.assertEquals(result.getName(), name);
  }

  @Test @DisplayName("findById Test")
  void test2() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).build();

    // 2단계 (when)
    User result = userDao.findById(user);

    // 3단계 (then)
    //Assertions.assertNull(result);
    Assertions.assertNotNull(result);
  }

  @Test @DisplayName("editById Test")
  void test3() throws Exception {
    // 1단계 (given)
    User user = User.builder().no(no).name(name).email(email).pwd(pwd).build();

    // 2단계 (when)
    int result = userDao.editById(user);

    // 3단계 (then)
    Assertions.assertEquals(result, 1);
  }

  @Test @DisplayName("deleteById Test")
  void test4() throws Exception {
    // 1단계 (given)
    no = 0;

    // 2단계 (when)
    int result = userDao.deleteById(no);

    // 3단계 (then)
    Assertions.assertEquals(result, 0);
  }

  @Test @DisplayName("save Test")
  void test5() throws Exception {
    // 1단계 (given)
    User user = User.builder().name(name).email(email).pwd(pwd).build();

    // 2단계 (when)
    User result = userDao.save(user);

    // 3단계 (then)
    System.out.println("No : " + result.getNo());
    Assertions.assertEquals(result.getName(), name);
    Assertions.assertEquals(result.getEmail(), email);
    Assertions.assertEquals(result.getPwd(), pwd);
  }

}

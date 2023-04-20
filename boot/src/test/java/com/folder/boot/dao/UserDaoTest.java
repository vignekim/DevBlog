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

  @Test @DisplayName("Login Test")
  void test1() throws Exception {
    // 1단계 (given)
    // 2단계 (when)
    // 3단계 (then)
  }

  @Test @DisplayName("findById Test")
  void test2() throws Exception {
    // 1단계 (given)
    // 2단계 (when)
    // 3단계 (then)
  }

  @Test @DisplayName("editById Test")
  void test3() throws Exception {
    // 1단계 (given)
    // 2단계 (when)
    // 3단계 (then)
  }

  @Test @DisplayName("deleteById Test")
  void test4() throws Exception {
    // 1단계 (given)
    // 2단계 (when)
    // 3단계 (then)
  }

  @Test @DisplayName("save Test")
  void test5() throws Exception {
    // 1단계 (given)
    // 2단계 (when)
    // 3단계 (then)
  }

}

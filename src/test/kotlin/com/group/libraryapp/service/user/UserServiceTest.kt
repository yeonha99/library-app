package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
)
{
    @Test
    fun saveUserTest(){
        val request = UserCreateRequest("최태현",null);

        userService.saveUser(request)

        val results=userRepository.findAll()
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("최태현")
        assertThat(results[0].age).isNull()
    }

    @Test
    fun getUsersTest(){

        userRepository.saveAll(listOf(
            User("A",20),
            User("B",null),
        ))

        val results=userService.getUsers()

        assertThat(results).hasSize(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A","B")
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20,null)

    }

    @AfterEach
    fun clean(){
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 네임 업데이트")
    fun updateUserNameTest(){

        val savedUser=userRepository.save(User("A",null))
        val request=UserUpdateRequest(savedUser.id!!,"B")

        userService.updateUserName(request)

        val result=userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")

    }

    @Test
    fun deleteUserTest(){

        userRepository.save(User("A",null))

        userService.deleteUser("A")

        assertThat(userRepository.findAll()).isEmpty()
    }


    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다.")
    fun getUserLoanHistoriesTest1(){

        userRepository.save(User("A",null))

        val result=userService.getUserLoanHistories()

        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("A")
        assertThat(result[0].books).isEmpty()


    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작한다.")
    fun getUserLoanHistoriesTest2(){

        val savedUser=userRepository.save(User("A",null))

        userLoanHistoryRepository.saveAll(
            listOf(
                UserLoanHistory.fixtrue(savedUser,"책1", UserLoanStatus.LOANED),
                UserLoanHistory.fixtrue(savedUser,"책2", UserLoanStatus.LOANED),
                UserLoanHistory.fixtrue(savedUser,"책3", UserLoanStatus.RETURNED),
                )
        )

        val result=userService.getUserLoanHistories()

        assertThat(result).hasSize(1)
        assertThat(result[0].name).isEqualTo("A")
        assertThat(result[0].books).hasSize(3)
        assertThat(result[0].books).extracting("name").containsExactlyInAnyOrder("책1","책2","책3")
        assertThat(result[0].books).extracting("isReturn").containsExactlyInAnyOrder(false,false,true)


    }

}
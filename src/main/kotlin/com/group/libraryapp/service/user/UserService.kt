package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.BookHistoryResponse
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.enums.user.UserLoanStatus
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
  private val userRepository: UserRepository,
) {

  @Transactional
  fun saveUser(request: UserCreateRequest) {
    val newser = User(request.name, request.age)
    userRepository.save(newser)
  }

  @Transactional(readOnly = true)
  fun getUsers(): List<UserResponse> {
    return userRepository.findAll()
      .map { user -> UserResponse.of(user) }
  }

  @Transactional
  fun updateUserName(request: UserUpdateRequest) {
    val user = userRepository.findByIdOrThrow(request.id)
    user.updateName(request.name)
  }

  @Transactional
  fun deleteUser(name: String) {
    val user = userRepository.findByName(name) ?: fail()
    userRepository.delete(user)
  }

  @Transactional(readOnly = true)
  fun getUserLoanBookHistories(): List<UserLoanHistoryResponse> {
    return userRepository.findAll().map { user ->
      UserLoanHistoryResponse(
        name = user.name,
        books = user.userLoanHistories.map { history -> // 이건 무조건 N + 1 문제 발생할듯 ?
          BookHistoryResponse(
            name = history.bookName,
            isReturn = history.status == UserLoanStatus.RETURNED
          )
        }
      )
    }
  }

}
package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.enums.user.UserLoanStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

}
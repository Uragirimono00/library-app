package com.group.libraryapp.dto.book.request

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.enums.book.BookEnums
import com.group.libraryapp.validation.isName

data class BookRequest(
    val name: String,
    val type: BookEnums,
    ) {
    fun nameValid(): Boolean {
        require(name.isName()) { "이름은 10글자 이상이어야 합니다." } // 신기하네요 정말 재밌습니다 ^^;
        return true
    }
}


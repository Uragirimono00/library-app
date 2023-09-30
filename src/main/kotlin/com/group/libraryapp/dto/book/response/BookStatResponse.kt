package com.group.libraryapp.dto.book.response

import com.group.libraryapp.enums.book.BookEnums

data class BookStatResponse(
  val type: BookEnums,
  val count: Long,
)
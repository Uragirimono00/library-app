package com.group.libraryapp.domain.book

import com.group.libraryapp.enums.book.BookEnums
import javax.persistence.*

@Entity
class Book(
    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BookEnums,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    companion object {
         fun fixture(
             name: String = "책 이름",
             type: BookEnums = BookEnums.COMPUTER,
             id: Long? = null,
         ): Book{
             return Book(
                 name = name,
                 type = type,
                 id = id,
             )
         }
    }
}
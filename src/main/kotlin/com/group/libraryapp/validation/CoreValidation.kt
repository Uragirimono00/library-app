package com.group.libraryapp.validation

fun String.isName(): Boolean {
  return this.length >= 10
}

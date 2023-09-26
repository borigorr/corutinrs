package org.example.dto

data class Attachment(
    val url: String,
    val description: String,
    val type: AttachmentType,
)

enum class AttachmentType{
    STRING
}
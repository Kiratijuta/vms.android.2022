package com.example.studentlist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Gadget( @PrimaryKey var id: UUID = UUID.randomUUID(),
                               var name: String = "")

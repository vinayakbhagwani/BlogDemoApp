package com.demo.data.room

import androidx.room.TypeConverters
import com.demo.domain.model.Reactions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//class RoomTypeConverter {
//
//    @TypeConverters
//    fun reactionToString(reactions: Reactions): String {
//        return Gson().toJson(reactions)
//    }
//
//    @TypeConverters
//    fun stringToReactions(str: String): Reactions {
//        return Gson().fromJson(str, Reactions::class.java)
//    }
//}
//
//class ListOfStringToStringTypeConverter {
//
//    @TypeConverters
//    fun listOfStringToString(str: List<String>): String {
//        return Gson().toJson(str)
//    }
//
//    @TypeConverters
//    fun strToListString(str: String): List<String> {
//        return Gson().fromJson(str, object : TypeToken<List<String>>() {}.type)
//    }
//
//}
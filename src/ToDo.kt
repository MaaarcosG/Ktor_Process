package com.example02

import kotlinx.html.InputType
import java.util.*
import kotlin.collections.ArrayList

data class Item(var task: String?, var id: Int?, var complete: Boolean?)

object ToDo {
    var todos = HashMap<String, Item>();

    /*get all tasks*/
    fun getAll(): ArrayList<Item>{
        return java.util.ArrayList(todos.values)
    }

    fun get(url: String): Item{
        return todos[url]!!
    }

    /*delete all task*/
    fun delete(url: String): Item{
        return todos.remove(url)!!
    }

    fun deleteAll(): ArrayList<Item>{
        todos.clear()
        return java.util.ArrayList(todos.values)
    }

    /*pull task*/
    fun pullTask(url: String, item: Item): Item{
        todos.put(url, item)
        return item;
    }
}
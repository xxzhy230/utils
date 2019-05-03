package com.yqjr.superviseapp.utils.ext

import com.google.gson.Gson


/**
 * @author Nixo
 * Gson拓展函数，通过协变获取泛型，省去再次填写Class
 */
inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)
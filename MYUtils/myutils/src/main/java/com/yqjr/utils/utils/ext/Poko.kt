package com.yqjr.superviseapp.utils.ext



/**
 * @author Nixo
 * Poko 用于替换属性(注解处理器)
 */
@Retention(AnnotationRetention.BINARY) //-> AnnotationRetention.BRINARY PoKo只存在二进制代码中，不会被反射所见
@Target(AnnotationTarget.CLASS)   //-> Poko只用于类
annotation class  PoKo
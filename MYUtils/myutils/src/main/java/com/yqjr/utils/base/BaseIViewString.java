package com.yqjr.utils.base;

public interface BaseIViewString {
     /**
      *
      * @param state  请求状态
      * @param json   返回的json串
      * @param position  区分请求 某个界面的多个接口请求
      */
     void result(boolean state, String json,int position);


}

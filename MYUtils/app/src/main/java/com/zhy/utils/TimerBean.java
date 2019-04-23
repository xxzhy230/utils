package com.zhy.utils;

import java.util.List;

public class TimerBean {

    /**
     * code : 0
     * data : {"list":[{"id":539,"ordertype":"su","time":1553330760,"create_time":1553323585,"status":201,"nickname":null,"head":null,"price":null,"birthday":null,"to_user_id":null,"apply_count":"0","match_count":"3","earliest_applytime":null,"apply_heads":null,"addressname":"天通苑(地铁站)","services":"下午茶;看电影","timestr":"2019-03-23 16:46","duration":3600,"mainorder":20},{"id":538,"ordertype":"su","time":1553329680,"create_time":1553322530,"status":201,"nickname":null,"head":null,"price":null,"birthday":null,"to_user_id":null,"apply_count":"0","match_count":"3","earliest_applytime":null,"apply_heads":null,"addressname":"望京(地铁站)","services":"看电影;其他","timestr":"2019-03-23 16:28","duration":14400,"mainorder":20}],"totalCount":2}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":539,"ordertype":"su","time":1553330760,"create_time":1553323585,"status":201,"nickname":null,"head":null,"price":null,"birthday":null,"to_user_id":null,"apply_count":"0","match_count":"3","earliest_applytime":null,"apply_heads":null,"addressname":"天通苑(地铁站)","services":"下午茶;看电影","timestr":"2019-03-23 16:46","duration":3600,"mainorder":20},{"id":538,"ordertype":"su","time":1553329680,"create_time":1553322530,"status":201,"nickname":null,"head":null,"price":null,"birthday":null,"to_user_id":null,"apply_count":"0","match_count":"3","earliest_applytime":null,"apply_heads":null,"addressname":"望京(地铁站)","services":"看电影;其他","timestr":"2019-03-23 16:28","duration":14400,"mainorder":20}]
         * totalCount : 2
         */

        private int totalCount;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 539
             * ordertype : su
             * time : 1553330760
             * create_time : 1553323585
             * status : 201
             * nickname : null
             * head : null
             * price : null
             * birthday : null
             * to_user_id : null
             * apply_count : 0
             * match_count : 3
             * earliest_applytime : null
             * apply_heads : null
             * addressname : 天通苑(地铁站)
             * services : 下午茶;看电影
             * timestr : 2019-03-23 16:46
             * duration : 3600
             * mainorder : 20
             */

            private int id;
            private String ordertype;
            private long time;
            private long create_time;
            private int status;
            private Object nickname;
            private Object head;
            private Object price;
            private Object birthday;
            private Object to_user_id;
            private String apply_count;
            private String match_count;
            private Object earliest_applytime;
            private Object apply_heads;
            private String addressname;
            private String services;
            private String timestr;
            private int duration;
            private int mainorder;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrdertype() {
                return ordertype;
            }

            public void setOrdertype(String ordertype) {
                this.ordertype = ordertype;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getHead() {
                return head;
            }

            public void setHead(Object head) {
                this.head = head;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public Object getTo_user_id() {
                return to_user_id;
            }

            public void setTo_user_id(Object to_user_id) {
                this.to_user_id = to_user_id;
            }

            public String getApply_count() {
                return apply_count;
            }

            public void setApply_count(String apply_count) {
                this.apply_count = apply_count;
            }

            public String getMatch_count() {
                return match_count;
            }

            public void setMatch_count(String match_count) {
                this.match_count = match_count;
            }

            public Object getEarliest_applytime() {
                return earliest_applytime;
            }

            public void setEarliest_applytime(Object earliest_applytime) {
                this.earliest_applytime = earliest_applytime;
            }

            public Object getApply_heads() {
                return apply_heads;
            }

            public void setApply_heads(Object apply_heads) {
                this.apply_heads = apply_heads;
            }

            public String getAddressname() {
                return addressname;
            }

            public void setAddressname(String addressname) {
                this.addressname = addressname;
            }

            public String getServices() {
                return services;
            }

            public void setServices(String services) {
                this.services = services;
            }

            public String getTimestr() {
                return timestr;
            }

            public void setTimestr(String timestr) {
                this.timestr = timestr;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public int getMainorder() {
                return mainorder;
            }

            public void setMainorder(int mainorder) {
                this.mainorder = mainorder;
            }
        }
    }
}

package com.xq.onclick;

import java.util.ArrayList;

/**
 * Created by zhq on 2018/1/17.
 */

public class DataBean {
    public ArrayList<ParentDataBean> parent;

    public DataBean(ArrayList<ParentDataBean> parent) {
        this.parent = parent;
    }

    public ArrayList<ParentDataBean> getParent() {
        return parent;
    }

    public void setParent(ArrayList<ParentDataBean> parent) {
        this.parent = parent;
    }

    static class ParentDataBean implements interfaceType {
        public String name;
        public ArrayList<ChildDataBean> childs;
        /**
         * 我们自己增加的字段，表示是否展开
         */
        public boolean isOpen;

        public ParentDataBean(String name, ArrayList<ChildDataBean> childs) {
            this.name = name;
            this.childs = childs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<ChildDataBean> getChilds() {
            return childs;
        }

        public void setChilds(ArrayList<ChildDataBean> childs) {
            this.childs = childs;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        /**
         * 0表示父类 1表示子类
         *
         * @return
         */
        @Override
        public int itemType() {
            return 0;
        }

        static class ChildDataBean implements interfaceType {
            public String childName;
            public int parentId;

            public ChildDataBean(String childName) {
                this.childName = childName;
            }

            public String getChildName() {
                return childName;
            }

            public void setChildName(String childName) {
                this.childName = childName;
            }

            @Override
            public int itemType() {
                return 1;
            }
        }
    }
}

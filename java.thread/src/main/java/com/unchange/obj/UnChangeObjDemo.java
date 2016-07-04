package com.unchange.obj;

/**
 * Created by frinder_liu on 2016/4/19.
 */
public class UnChangeObjDemo {

    private final String name;
    private final String remark;

    public UnChangeObjDemo(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public UnChangeObjDemo(Builder builder) {
        name = builder.name;
        remark = builder.remark;
    }

    static class Builder implements ObjBuilder<UnChangeObjDemo> {

        private String name;
        private String remark;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRemark(String remark) {
            this.remark = remark;
            return this;
        }

        @Override
        public UnChangeObjDemo build() {
            return new UnChangeObjDemo(this);
        }
    }

    @Override
    public String toString() {
        return "name : ".concat(this.name).concat(", remark : ".concat(this.remark));
    }

    public static void main(String[] args) {
        UnChangeObjDemo.Builder ub = new UnChangeObjDemo.Builder();
        UnChangeObjDemo demo = ub.setName("Jack").setRemark("hello...").build();
        System.out.println(demo.toString());
    }
}


interface ObjBuilder<T> {
    T build();
}


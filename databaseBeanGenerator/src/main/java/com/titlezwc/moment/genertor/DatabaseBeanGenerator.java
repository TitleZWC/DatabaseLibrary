package com.titlezwc.moment.genertor;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DatabaseBeanGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(14, "com.titlezwc.moment");//数据库的版本和包名

        addNote(schema);//添加bean类
//        addCustomerOrder(schema);
        new DaoGenerator().generateAll(schema, System.getProperty("user.dir") + "/database/src/main/java-gen");
    }

    private static void addNote(Schema schema) {
        Entity AppInfo = schema.addEntity("AppInfo");
        AppInfo.addStringProperty("PackageName");
        AppInfo.addIdProperty();
        AppInfo.addStringProperty("AppName");
        AppInfo.addStringProperty("versionName");
        AppInfo.addIntProperty("versionCode");
        AppInfo.addIntProperty("versionCodeUpdate");
        AppInfo.addIntProperty("VisitTimes");//访问次数
        AppInfo.addLongProperty("UseTime");//使用时长
        AppInfo.addDateProperty("date");
    }

    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();

        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }
}

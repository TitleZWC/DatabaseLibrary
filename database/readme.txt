   本module是一个第三方数据库类库的封装，目前使用的是GreenDao
   具体的第三方类库的方法调用在impl下

   在使用数据库时，需要现在程序启动时初始化：
        DataBaseManager.getInstance().init();

   在具体的使用时：
        DataBaseOperator dbo = new DataBaseOperator(Sticker.class, "path");

        dbo.insert("需要插入的对象");//进行数据库操作


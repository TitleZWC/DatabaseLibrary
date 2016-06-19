package com.xiaoenai.app.data.database;


import com.enai.app.database.DataBaseManager;
import com.enai.app.database.DataBaseOperator;
import com.xiaoenai.app.database.bean.Sticker;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by hongzhu on 16/5/31.
 */
public class DatabaseTest extends ApplicationTestCase {

    @Before
    public void setUp() throws Exception {

        DataBaseManager.getInstance().init(RuntimeEnvironment.application);
        ShadowLog.stream = System.out;
    }

    @Test
    public void testLogger() {
        DataBaseOperator dbo = new DataBaseOperator(Sticker.class, "path");
        Sticker sticker = new Sticker();
        sticker.setFree(0);
        sticker.setCover_url("title");
        dbo.insert(sticker);
    }
}

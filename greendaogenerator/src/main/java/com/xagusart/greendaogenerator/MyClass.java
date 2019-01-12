package com.xagusart.greendaogenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.zulfakarelzaf.authenticjogja.database");
        schema.enableKeepSectionsByDefault();
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addSavedEntities(schema);
    }


    private static void addSavedEntities(final Schema schema) {
        Entity saved = schema.addEntity("Saved");
        saved.addIdProperty().primaryKey().autoincrement();
        saved.addIntProperty("item_id").notNull();
        saved.addIntProperty("jenis").notNull();
    }
}

package com.artecinnovaciones.aquarius.GreendaoDb;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Greendaodb {

        public static void main(String args[]) throws Exception {
            Schema schema = new Schema(1000, "com.artecinnovaciones.aquarius.modeldao");

            Entity person = schema.addEntity("Login");
            person.addIdProperty();
            person.addStringProperty("name");
            person.addStringProperty("password");


            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        }
    }

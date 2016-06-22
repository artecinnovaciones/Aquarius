package com.artecinnovaciones.aquarius.GreendaoDb;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Greendaodb {

        public static void main(String args[]) throws Exception {
            Schema schema = new Schema(1000, "com.artecinnovaciones.aquarius.modeldao");

            Entity PecesAguaDulce = schema.addEntity("PecesDulce");
            PecesAguaDulce.addIdProperty();
            PecesAguaDulce.addStringProperty("Nombre");
            PecesAguaDulce.addStringProperty("Informacion");
            PecesAguaDulce.addStringProperty("Enfermedades");
            PecesAguaDulce.addStringProperty("Cuidados");
            PecesAguaDulce.addStringProperty("Alimentacion");
            PecesAguaDulce.addIntProperty("MasBuscado");
            PecesAguaDulce.addByteArrayProperty("Imagen");
            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        }
    }

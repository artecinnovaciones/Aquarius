package com.artecinnovaciones.aquarius.GreendaoDb;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Greendaodb {

        public static void main(String args[]) throws Exception {
            Schema schema = new Schema(1000, "com.artecinnovaciones.aquarius.modelodao");

            Entity PecesAguaDulce = schema.addEntity("PecesDulce");
            PecesAguaDulce.addIdProperty();
            PecesAguaDulce.addStringProperty("NombreCientifico");
            PecesAguaDulce.addStringProperty("NombreComun");
            PecesAguaDulce.addStringProperty("Informacion");
            PecesAguaDulce.addStringProperty("Cuidados");
            PecesAguaDulce.addStringProperty("Alimentacion");
            PecesAguaDulce.addIntProperty("MasBuscado");
            PecesAguaDulce.addStringProperty("Imagen");
            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        }
    }

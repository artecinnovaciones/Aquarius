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
            PecesAguaDulce.addIntProperty("Tipo");
            PecesAguaDulce.addStringProperty("Imagen");
            PecesAguaDulce.addBooleanProperty("isSearch");



            Entity PecesEnfermedades = schema.addEntity("PecesEnfermedades");
            PecesEnfermedades.addIdProperty();
            PecesEnfermedades.addStringProperty("Nombre");
            PecesEnfermedades.addStringProperty("Sintomas");
            PecesEnfermedades.addStringProperty("Causas");
            PecesEnfermedades.addStringProperty("Tratamiento");
            PecesEnfermedades.addStringProperty("img");
            PecesEnfermedades.addBooleanProperty("isSearch");


            new DaoGenerator().generateAll(schema, "../app/src/main/java");
        }
    }

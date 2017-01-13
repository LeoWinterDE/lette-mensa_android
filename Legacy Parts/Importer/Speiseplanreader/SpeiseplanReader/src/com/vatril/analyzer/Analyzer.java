package com.vatril.analyzer;

import com.vatril.speise.Speise;
import com.vatril.util.Util;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by vatril on 21.09.16.
 */
public class Analyzer {

    private Speise[][] speisen = new Speise[5][5];

    private Date date;
    private Speise.Art art = Speise.Art.VORSPEISE;

    private StringBuilder builder = new StringBuilder();

    private int[][][] startpos = {
            {{3, 1}, {3, 7}, {3, 13}, {3, 19}, {3, 25}},
            {{11, 1}, {11, 7}, {11, 13}, {11, 19}, {11, 25}},
            {{19, 1}, {19, 7}, {19, 13}, {19, 19}, {19, 25}},
            {{27, 1}, {27, 7}, {27, 13}, {27, 19}, {27, 25}},
            {{35, 1}, {35, 7}, {35, 13}, {35, 19}, {35, 25}}
            };


    public Speise[][] getSpeisen(String sheetURL) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(sheetURL)));
        HSSFSheet sheet = workbook.getSheet("Speiseplan_SW");
        date = Util.getDateFromString(sheet.getRow(0).getCell(6).toString());
        for (int i = 0; i < speisen.length; i++) {
            for (int j = 0; j < speisen.length; j++) {
                speisen[i][j] = analyze(startpos[i][j][0],startpos[i][j][1],sheet);
            }
        }

        return speisen;
    }

    private Speise analyze(int xoff, int yoff, HSSFSheet sheet) {
        builder.setLength(0);
        double preis = 0;
        String beachte = "";
        String zusatz = "";
        for (int i = 0; i < 5 ; i++) {
            String tempname = sheet.getRow(xoff + i).getCell(yoff).toString();
            if(tempname != null && !tempname.equals(""))
                builder.append(" " +tempname);
        }

        try {
        preis = Double.parseDouble(sheet.getRow(xoff + 5).getCell(yoff).toString());
        }catch (Exception e){
            System.err.println("Could not read " + (xoff + 5) + " " + yoff);
        }

        beachte = sheet.getRow(xoff + 7).getCell(yoff).toString();

        zusatz = sheet.getRow(xoff + 7).getCell(yoff + 4).toString();

        return new Speise(builder.toString(),beachte,preis,art,Util.getIntFromString(zusatz),date);
    }
}

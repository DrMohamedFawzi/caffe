package com.msnit.localcafe.cafe;

import com.msnit.localcafe.utils.Utils;

public class CafeRequest {

    private int tableNum;
    private int cafeCupsNum;
    private int nescafe;
    private int capochino;
    private int choco;
    private String note;

    public CafeRequest(int tableNum) {
        this.tableNum = tableNum;
    }

    public CafeRequest(int tableNum, int cafeCupsNum) {
        this.tableNum = tableNum;
        this.cafeCupsNum = cafeCupsNum;
    }

    public CafeRequest(int tableNum, int cafeCupsNum, int nescafe, int capochino, int choco) {
        this.tableNum = tableNum;
        this.cafeCupsNum = cafeCupsNum;
        this.nescafe = nescafe;
        this.capochino = capochino;
        this.choco = choco;
    }

    public CafeRequest(int tableNum, int cafeCupsNum, int nescafe, int capochino, int choco, String note) {
        this.tableNum = tableNum;
        this.cafeCupsNum = cafeCupsNum;
        this.nescafe = nescafe;
        this.capochino = capochino;
        this.choco = choco;
        this.note = note;
    }

    public String[] getCups() {
        String[] items  = new String[2];
        StringBuilder builder = new StringBuilder();
        StringBuilder builderValues = new StringBuilder();
        if (cafeCupsNum > 0) {
            builder.append("قهوة:").append("\n");
            builderValues.append(cafeCupsNum).append("\n");
        }
        if (nescafe > 0) {
            builder.append("نسكافيه:").append("\n");
            builderValues.append(nescafe).append("\n");
        }
        if (capochino > 0) {
            builder.append("كابوتشينو:").append("\n");
            builderValues.append(capochino).append("\n");
        }
        if (choco > 0) {
            builder.append("شوكو:").append("\n");
            builderValues.append(choco).append("\n");
        }
        if (note.length() > 0) {
            builder.append("ملاحظة:").append("\n");
            builderValues.append(note).append("\n");
        }
       items[0] = builder.substring(0, builder.length() - 1);
       items[1] = builderValues.substring(0, builderValues.length() - 1);
        return items;
    }

    public int getNescafe() {
        return nescafe;
    }

    public void setNescafe(int nescafe) {
        this.nescafe = nescafe;
    }

    public int getCapochino() {
        return capochino;
    }

    public void setCapochino(int capochino) {
        this.capochino = capochino;
    }

    public int getChoco() {
        return choco;
    }

    public void setChoco(int choco) {
        this.choco = choco;
    }

    public void setCafeNum(String cafeNum) {

        cafeCupsNum = Utils.getInt(cafeNum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CafeRequest request = (CafeRequest) o;

        return tableNum == request.tableNum;
    }

    @Override
    public int hashCode() {
        return tableNum;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public int getCafeCupsNum() {
        return cafeCupsNum;
    }

    public void setCafeCupsNum(int cafeCupsNum) {
        this.cafeCupsNum = cafeCupsNum;
    }
}

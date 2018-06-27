package com.example.onurs.donanmhaber;

public class ItemData {

    private String imageValue;
    private String dataTitle;
    private int totalRead;
    private String textColor;
    private String colorAvarage;
    private String dataUrl;

    public ItemData() {
    }

    public ItemData(String imageValue, String dataTitle, int totalRead, String textColor, String colorAvarage, String dataUrl) {
        this.imageValue = imageValue;
        this.dataTitle = dataTitle;
        this.totalRead = totalRead;
        this.textColor = textColor;
        this.colorAvarage = colorAvarage;
        this.dataUrl = dataUrl;
    }

    public String getImageValue() {
        return imageValue;
    }

    public void setImageValue(String imageValue) {
        this.imageValue = imageValue;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public int getTotalRead() {
        return totalRead;
    }

    public void setTotalRead(int totalRead) {
        this.totalRead = totalRead;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getColorAvarage() {
        return colorAvarage;
    }

    public void setColorAvarage(String colorAvarage) {
        this.colorAvarage = colorAvarage;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}

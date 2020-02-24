package com.kaltinril.vampiric.core.util.helpers;

public class Constants {

    public enum Color{
        DARK_RED("\u00A74", 11141120, "0xAA0000"),
        RED("\u00A7c", 16733525, "0xFF5555"),
        GOLD("\u00A76", 16755200, "0xFFAA00"),
        YELLOW("\u00A7e", 16777045, "0xFFFF55"),
        DARK_GREEN("\u00A72", 43520, "0x00AA00"),
        GREEN("\u00A7a", 5635925, "0x55FF55"),
        AQUA("\u00A7b", 5636095, "0x55FFFF"),
        DARK_AQUA("\u00A73", 43690, "0x00AAAA"),
        DARK_BLUE("\u00A71", 170, "0x0000AA"),
        BLUE("\u00A79", 5592575, "0x5555FF"),
        LIGHT_PURPLE("\u00A7d", 16733695, "0xFF55FF"),
        DARK_PURPLE("\u00A75", 11141290, "0xAA00AA"),
        WHITE("\u00A7f", 16777215, "0xFFFFFF"),
        GRAY("\u00A77", 11184810, "0xAAAAAA"),
        DARK_GRAY("\u00A78", 5592405, "0x555555"),
        BLACK("\u00A70", 0, "0x0");


        // declaring private variable for getting values
        private String stringValue;
        private int intValue;
        private String hexValue;

        public String getStringValue()
        {
            return this.stringValue;
        }

        public int getIntValue()
        {
            return this.intValue;
        }

        public String getHexValue()
        {
            return this.hexValue;
        }

        private Color(String stringValueIn, int intValueIn, String hexValueIn)
        {
            this.stringValue = stringValueIn;
            this.intValue = intValueIn;
            this.hexValue = hexValueIn;
        }
    }



}

package com.kaltinril.vampiric.core.util.helpers;

public class Constants {

    public enum TextColor{
        RED("\u00A74"),
        PEACH("\u00A7c"),
        BLUE("\u00A71"),
        LIGHT_BLUE("\u00A79"),
        ORANGE("\u00A76"),
        YELLOW("\u00A7e"),
        PINK("\u00A7d"),
        MAGENTA("\u00A75"),
        GREEN("\u00A72"),
        LIGHT_GREEN("\u00A7a"),
        WHITE("\u00A7f"),
        GREY("\u00A77"),
        DARK_GREY("\u00A78"),
        BLACK("\u00A70"),
        TEAL("\u00A7b"),
        DARK_TEAL("\u00A73");

        // declaring private variable for getting values
        private String colorValue;

        public String getAction()
        {
            return this.colorValue;
        }

        private TextColor(String colorValue)
        {
            this.colorValue = colorValue;
        }
    }



}

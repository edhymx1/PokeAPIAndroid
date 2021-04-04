package com.example.pokeapi.common;

public class Common {
    public static String getColorByType(String type) {
        switch(type)
        {
            case "normal":
                return "#A4A27A";

            case "dragon":
                return"#743BFB";

            case "psychic":
                return "#F15B85";

            case "electric":
                return "#E9CA3C";

            case "ground":
                return "#D9BF6C";

            case "grass":
                return "#81C85B";

            case "poison":
                return "#A441A3";

            case "steel":
                return "#BAB7D2";

            case "fairy":
                return "#DDA2DF";

            case "fire":
                return "#F48130";

            case "fight":
                return "#BE3027";

            case "bug":
                return "#A8B822";

            case "ghost":
                return "#705693";

            case "dark":
                return "#745945";

            case "ice":
                return "#9BD8D8";

            case "water":
                return "#658FF1";
            default:
                return "#658FA0";
        }
    }
}

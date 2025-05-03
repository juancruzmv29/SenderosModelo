package model;

import java.awt.Color;

public enum ImpactoAmbiental {
	
	
	BAJO(Color.YELLOW, 1, 3),
    MEDIO(Color.ORANGE, 4, 6),
    ALTO(Color.RED, 7, 10);

    private Color color;
    private int min, max;

    ImpactoAmbiental(Color color, int min, int max) {
        this.color = color;
        this.min = min;
        this.max = max;
    }

    public static Color getColorPorImpacto(int impacto) {
        for (ImpactoAmbiental i : values()) {
            if (impacto >= i.min && impacto <= i.max) return i.color;
        }
        return Color.GRAY;
    }
}

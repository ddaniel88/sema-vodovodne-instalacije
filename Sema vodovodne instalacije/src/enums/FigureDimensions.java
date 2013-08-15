package enums;

public enum FigureDimensions {
	SMALL(33),
	MEDIUM_SMALL(40),
	MEDIUM(50),
	LARGE(66),
	X_LARGE(100),
	XX_LARGE(132);

	private int code;

	private FigureDimensions(int c) {
		code = c;
	}

	public int getDimension() {
		return code;
	}
}

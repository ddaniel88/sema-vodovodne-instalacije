package enums;

public enum FigureDimensions {
	SMALL(33),
	MEDIUM_SMALL(40),
	MEDIUM(50),
	LARGE(66),
	X_LARGE(100),
	XX_LARGE(132);

	private int dimension;

	private FigureDimensions(int d) {
		dimension = d;
	}

	public int getDimension() {
		return dimension;
	}
}

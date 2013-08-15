package enums;

public enum FigureDimensions {
	ZOOM_IN(1.1f),
	ZOOM_OUT(0.9f);

	private float code;

	private FigureDimensions(float c) {
		code = c;
	}

	public float getCode() {
		return code;
	}
}

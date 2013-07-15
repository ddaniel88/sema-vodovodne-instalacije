package enums;

public enum ScaleEnum {
	ZOOM_IN(1.1f),
	ZOOM_OUT(0.9f);

	private float code;

	private ScaleEnum(float c) {
		code = c;
	}

	public float getCode() {
		return code;
	}
}

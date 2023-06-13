package program;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
	private static final String COLOR_PROPERTIES_FILE = "/color_palette.properties";
	private Properties props = new Properties();

	public PropertiesConfig() throws IOException {
		InputStream input = getClass().getResourceAsStream(COLOR_PROPERTIES_FILE);
		props.load(input);
	}

	public Color getColor(String color) {
		color = props.getProperty(color);
		return Color.decode(color);
	}
}
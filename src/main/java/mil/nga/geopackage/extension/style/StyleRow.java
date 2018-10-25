package mil.nga.geopackage.extension.style;

import java.util.regex.Pattern;

import mil.nga.geopackage.GeoPackageException;
import mil.nga.geopackage.extension.related.simple.SimpleAttributesRow;
import mil.nga.geopackage.user.custom.UserCustomColumn;
import mil.nga.geopackage.user.custom.UserCustomRow;

/**
 * Style Row containing the values from a single result set row
 * 
 * @author osbornb
 * @since 3.1.1
 */
public class StyleRow extends SimpleAttributesRow {

	/**
	 * Color hex pattern
	 */
	private Pattern colorPattern = Pattern.compile("^#([0-9a-fA-F]{3}){1,2}$");

	/**
	 * Constructor to create an empty row
	 * 
	 * @param table
	 *            style table
	 */
	protected StyleRow(StyleTable table) {
		super(table);
	}

	/**
	 * Constructor
	 * 
	 * @param userCustomRow
	 *            user custom row
	 */
	public StyleRow(UserCustomRow userCustomRow) {
		super(userCustomRow);
	}

	/**
	 * Copy Constructor
	 * 
	 * @param styleRow
	 *            style row to copy
	 */
	public StyleRow(StyleRow styleRow) {
		super(styleRow);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StyleTable getTable() {
		return (StyleTable) super.getTable();
	}

	/**
	 * Get the name column index
	 * 
	 * @return name column index
	 */
	public int getNameColumnIndex() {
		return getTable().getNameColumnIndex();
	}

	/**
	 * Get the name column
	 * 
	 * @return name column
	 */
	public UserCustomColumn getNameColumn() {
		return getTable().getNameColumn();
	}

	/**
	 * Get the name
	 * 
	 * @return name
	 */
	public String getName() {
		return getValue(getNameColumnIndex()).toString();
	}

	/**
	 * Set the name
	 * 
	 * @param name
	 *            Feature Style name
	 */
	public void setName(String name) {
		setValue(getNameColumnIndex(), name);
	}

	/**
	 * Get the description column index
	 * 
	 * @return description column index
	 */
	public int getDescriptionColumnIndex() {
		return getTable().getDescriptionColumnIndex();
	}

	/**
	 * Get the description column
	 * 
	 * @return description column
	 */
	public UserCustomColumn getDescriptionColumn() {
		return getTable().getDescriptionColumn();
	}

	/**
	 * Get the description
	 * 
	 * @return description
	 */
	public String getDescription() {
		return getValue(getDescriptionColumnIndex()).toString();
	}

	/**
	 * Set the description
	 * 
	 * @param description
	 *            Feature Style description
	 */
	public void setDescription(String description) {
		setValue(getDescriptionColumnIndex(), description);
	}

	/**
	 * Get the color column index
	 * 
	 * @return color column index
	 */
	public int getColorColumnIndex() {
		return getTable().getColorColumnIndex();
	}

	/**
	 * Get the color column
	 * 
	 * @return color column
	 */
	public UserCustomColumn getColorColumn() {
		return getTable().getColorColumn();
	}

	/**
	 * Get the color
	 * 
	 * @return color
	 */
	public String getColor() {
		return getValue(getColorColumnIndex()).toString();
	}

	/**
	 * Set the color
	 * 
	 * @param color
	 *            Geometry color in hex format #RRGGBB or #RGB
	 */
	public void setColor(String color) {
		color = validateColor(color);
		setValue(getColorColumnIndex(), color);
	}

	/**
	 * Get the opacity column index
	 * 
	 * @return opacity column index
	 */
	public int getOpacityColumnIndex() {
		return getTable().getOpacityColumnIndex();
	}

	/**
	 * Get the opacity column
	 * 
	 * @return opacity column
	 */
	public UserCustomColumn getOpacityColumn() {
		return getTable().getOpacityColumn();
	}

	/**
	 * Get the opacity
	 * 
	 * @return opacity
	 */
	public Double getOpacity() {
		return (Double) getValue(getOpacityColumnIndex());
	}

	/**
	 * Set the opacity
	 * 
	 * @param opacity
	 *            Geometry color opacity inclusively between 0.0 and 1.0
	 */
	public void setOpacity(Double opacity) {
		validateOpacity(opacity);
		setValue(getOpacityColumnIndex(), opacity);
	}

	/**
	 * Get the width column index
	 * 
	 * @return width column index
	 */
	public int getWidthColumnIndex() {
		return getTable().getWidthColumnIndex();
	}

	/**
	 * Get the width column
	 * 
	 * @return width column
	 */
	public UserCustomColumn getWidthColumn() {
		return getTable().getWidthColumn();
	}

	/**
	 * Get the width
	 * 
	 * @return width
	 */
	public Double getWidth() {
		return (Double) getValue(getWidthColumnIndex());
	}

	/**
	 * Set the width
	 * 
	 * @param width
	 *            Geometry line stroke or point width >= 0.0
	 */
	public void setWidth(Double width) {
		if (width != null && width < 0.0) {
			throw new GeoPackageException(
					"Width must be greater than or equal to 0.0, invalid value: "
							+ width);
		}
		setValue(getWidthColumnIndex(), width);
	}

	/**
	 * Get the fill color column index
	 * 
	 * @return fill color column index
	 */
	public int getFillColorColumnIndex() {
		return getTable().getFillColorColumnIndex();
	}

	/**
	 * Get the fill color column
	 * 
	 * @return fill color column
	 */
	public UserCustomColumn getFillColorColumn() {
		return getTable().getFillColorColumn();
	}

	/**
	 * Get the fill color
	 * 
	 * @return fill color
	 */
	public String getFillColor() {
		return getValue(getFillColorColumnIndex()).toString();
	}

	/**
	 * Set the fill color
	 * 
	 * @param fillColor
	 *            Closed geometry fill color in hex format #RRGGBB or #RGB
	 */
	public void setFillColor(String fillColor) {
		fillColor = validateColor(fillColor);
		setValue(getFillColorColumnIndex(), fillColor);
	}

	/**
	 * Get the fill opacity column index
	 * 
	 * @return fill opacity column index
	 */
	public int getFillOpacityColumnIndex() {
		return getTable().getFillOpacityColumnIndex();
	}

	/**
	 * Get the fill opacity column
	 * 
	 * @return fill opacity column
	 */
	public UserCustomColumn getFillOpacityColumn() {
		return getTable().getFillOpacityColumn();
	}

	/**
	 * Get the fill opacity
	 * 
	 * @return fill opacity
	 */
	public Double getFillOpacity() {
		return (Double) getValue(getFillOpacityColumnIndex());
	}

	/**
	 * Set the fill opacity
	 * 
	 * @param fillOpacity
	 *            Closed geometry fill color opacity inclusively between 0.0 and
	 *            1.0
	 */
	public void setFillOpacity(Double fillOpacity) {
		validateOpacity(fillOpacity);
		setValue(getFillOpacityColumnIndex(), fillOpacity);
	}

	/**
	 * Validate and adjust the color value
	 * 
	 * @param color
	 *            color
	 */
	private String validateColor(String color) {
		String validated = color;
		if (color != null) {
			if (!color.startsWith("#")) {
				validated = "#" + color;
			}
			if (!colorPattern.matcher(validated).matches()) {
				throw new GeoPackageException(
						"Color must be in hex format #RRGGBB or #RGB, invalid value: "
								+ color);
			}
			validated = validated.toUpperCase();
		}
		return validated;
	}

	/**
	 * Validate the opacity value
	 * 
	 * @param opacity
	 *            opacity
	 */
	private void validateOpacity(Double opacity) {
		if (opacity != null && (opacity < 0.0 || opacity > 1.0)) {
			throw new GeoPackageException(
					"Opacity must be set inclusively between 0.0 and 1.0, invalid value: "
							+ opacity);
		}
	}

	/**
	 * Copy the row
	 * 
	 * @return row copy
	 */
	public StyleRow copy() {
		return new StyleRow(this);
	}

}
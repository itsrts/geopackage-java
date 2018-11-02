package mil.nga.geopackage.extension.style;

import mil.nga.geopackage.GeoPackage;
import mil.nga.geopackage.GeoPackageException;
import mil.nga.geopackage.core.contents.Contents;
import mil.nga.geopackage.features.columns.GeometryColumns;
import mil.nga.geopackage.features.user.FeatureRow;
import mil.nga.geopackage.features.user.FeatureTable;
import mil.nga.sf.GeometryType;

/**
 * Feature Table Styles, styles and icons for an individual feature table
 * 
 * @author osbornb
 * @since 3.1.1
 */
public class FeatureTableStyles {

	/**
	 * Feature Styles
	 */
	private final FeatureStyleExtension featureStyleExtension;

	/**
	 * Feature Table name
	 */
	private final String tableName;

	/**
	 * Cached table feature styles
	 */
	private final FeatureStyles cachedTableFeatureStyles = new FeatureStyles();

	/**
	 * Constructor
	 * 
	 * @param geoPackage
	 *            GeoPackage
	 * @param featureTable
	 *            feature table
	 */
	public FeatureTableStyles(GeoPackage geoPackage, FeatureTable featureTable) {
		this(geoPackage, featureTable.getTableName());
	}

	/**
	 * Constructor
	 * 
	 * @param geoPackage
	 *            GeoPackage
	 * @param geometryColumns
	 *            geometry columns
	 */
	public FeatureTableStyles(GeoPackage geoPackage,
			GeometryColumns geometryColumns) {
		this(geoPackage, geometryColumns.getTableName());
	}

	/**
	 * Constructor
	 * 
	 * @param geoPackage
	 *            GeoPackage
	 * @param contents
	 *            feature contents
	 */
	public FeatureTableStyles(GeoPackage geoPackage, Contents contents) {
		this(geoPackage, contents.getTableName());
	}

	/**
	 * Constructor
	 * 
	 * @param geoPackage
	 *            GeoPackage
	 * @param featureTable
	 *            feature table
	 */
	public FeatureTableStyles(GeoPackage geoPackage, String featureTable) {
		featureStyleExtension = new FeatureStyleExtension(geoPackage);
		tableName = featureTable;
		if (!geoPackage.isFeatureTable(featureTable)) {
			throw new GeoPackageException(
					"Table must be a feature table. Table: " + featureTable
							+ ", Actual Type: "
							+ geoPackage.getTableType(featureTable));
		}
	}

	/**
	 * Get the feature style extension
	 * 
	 * @return feature style extension
	 */
	public FeatureStyleExtension getFeatureStyleExtension() {
		return featureStyleExtension;
	}

	/**
	 * Get the feature table name
	 * 
	 * @return feature table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Create a style relationship for the feature table
	 */
	public void createStyleRelationship() {
		featureStyleExtension.createStyleRelationship(tableName);
	}

	/**
	 * Determine if a style relationship exists for the feature table
	 * 
	 * @return true if relationship exists
	 */
	public boolean hasStyleRelationship() {
		return featureStyleExtension.hasStyleRelationship(tableName);
	}

	/**
	 * Create a feature table style relationship
	 */
	public void createTableStyleRelationship() {
		featureStyleExtension.createTableStyleRelationship(tableName);
	}

	/**
	 * Determine if feature table style relationship exists
	 * 
	 * @return true if relationship exists
	 */
	public boolean hasTableStyleRelationship() {
		return featureStyleExtension.hasTableStyleRelationship(tableName);
	}

	/**
	 * Create an icon relationship for the feature table
	 */
	public void createIconRelationship() {
		featureStyleExtension.createIconRelationship(tableName);
	}

	/**
	 * Determine if an icon relationship exists for the feature table
	 * 
	 * @return true if relationship exists
	 */
	public boolean hasIconRelationship() {
		return featureStyleExtension.hasIconRelationship(tableName);
	}

	/**
	 * Create a feature table icon relationship
	 */
	public void createTableIconRelationship() {
		featureStyleExtension.createTableIconRelationship(tableName);
	}

	/**
	 * Determine if feature table icon relationship exists
	 * 
	 * @return true if relationship exists
	 */
	public boolean hasTableIconRelationship() {
		return featureStyleExtension.hasTableIconRelationship(tableName);
	}

	/**
	 * Get a Style Mapping DAO
	 * 
	 * @return style mapping DAO
	 */
	public StyleMappingDao getStyleMappingDao() {
		return featureStyleExtension.getStyleMappingDao(tableName);
	}

	/**
	 * Get a Table Style Mapping DAO
	 * 
	 * @return table style mapping DAO
	 */
	public StyleMappingDao getTableStyleMappingDao() {
		return featureStyleExtension.getTableStyleMappingDao(tableName);
	}

	/**
	 * Get a Icon Mapping DAO
	 * 
	 * @return icon mapping DAO
	 */
	public StyleMappingDao getIconMappingDao() {
		return featureStyleExtension.getIconMappingDao(tableName);
	}

	/**
	 * Get a Table Icon Mapping DAO
	 * 
	 * @return table icon mapping DAO
	 */
	public StyleMappingDao getTableIconMappingDao() {
		return featureStyleExtension.getTableIconMappingDao(tableName);
	}

	/**
	 * Get a style DAO
	 * 
	 * @return style DAO
	 */
	public StyleDao getStyleDao() {
		return featureStyleExtension.getStyleDao();
	}

	/**
	 * Get a icon DAO
	 * 
	 * @return icon DAO
	 */
	public IconDao getIconDao() {
		return featureStyleExtension.getIconDao();
	}

	/**
	 * Get the table feature styles
	 * 
	 * @return table feature styles or null
	 */
	public FeatureStyles getTableFeatureStyles() {
		return featureStyleExtension.getTableFeatureStyles(tableName);
	}

	/**
	 * Get the table styles
	 * 
	 * @return table styles or null
	 */
	public Styles getTableStyles() {
		return featureStyleExtension.getTableStyles(tableName);
	}

	/**
	 * Get the cached table styles, querying and caching if needed
	 * 
	 * @return cached table styles
	 */
	public Styles getCachedTableStyles() {

		Styles styles = cachedTableFeatureStyles.getStyles();

		if (styles == null) {
			synchronized (cachedTableFeatureStyles) {
				styles = cachedTableFeatureStyles.getStyles();
				if (styles == null) {
					styles = getTableStyles();
					if (styles == null) {
						styles = new Styles();
					}
					cachedTableFeatureStyles.setStyles(styles);
				}
			}
		}

		if (styles.isEmpty()) {
			styles = null;
		}

		return styles;
	}

	/**
	 * Get the table style of the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 * @return style row
	 */
	public StyleRow getTableStyle(GeometryType geometryType) {
		return featureStyleExtension.getTableStyle(tableName, geometryType);
	}

	/**
	 * Get the table style default
	 * 
	 * @return style row
	 */
	public StyleRow getTableStyleDefault() {
		return featureStyleExtension.getTableStyleDefault(tableName);
	}

	/**
	 * Get the table icons
	 * 
	 * @return table icons or null
	 */
	public Icons getTableIcons() {
		return featureStyleExtension.getTableIcons(tableName);
	}

	/**
	 * Get the cached table icons, querying and caching if needed
	 * 
	 * @return cached table icons
	 */
	public Icons getCachedTableIcons() {

		Icons icons = cachedTableFeatureStyles.getIcons();

		if (icons == null) {
			synchronized (cachedTableFeatureStyles) {
				icons = cachedTableFeatureStyles.getIcons();
				if (icons == null) {
					icons = getTableIcons();
					if (icons == null) {
						icons = new Icons();
					}
					cachedTableFeatureStyles.setIcons(icons);
				}
			}
		}

		if (icons.isEmpty()) {
			icons = null;
		}

		return icons;
	}

	/**
	 * Get the table icon of the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 * @return icon row
	 */
	public IconRow getTableIcon(GeometryType geometryType) {
		return featureStyleExtension.getTableIcon(tableName, geometryType);
	}

	/**
	 * Get the table icon default
	 * 
	 * @return icon row
	 */
	public IconRow getTableIconDefault() {
		return featureStyleExtension.getTableIconDefault(tableName);
	}

	/**
	 * Get the feature styles for the feature row
	 * 
	 * @param featureRow
	 *            feature row
	 * @return feature styles or null
	 */
	public FeatureStyles getFeatureStyles(FeatureRow featureRow) {
		return featureStyleExtension.getFeatureStyles(featureRow);
	}

	/**
	 * Get the feature styles for the feature id
	 * 
	 * @param featureId
	 *            feature id
	 * @return feature styles or null
	 */
	public FeatureStyles getFeatureStyles(long featureId) {
		return featureStyleExtension.getFeatureStyles(tableName, featureId);
	}

	/**
	 * Get the feature style (style and icon) of the feature row, searching in
	 * order: feature geometry type style or icon, feature default style or
	 * icon, table geometry type style or icon, table default style or icon
	 * 
	 * @param featureRow
	 *            feature row
	 * @return feature style
	 */
	public FeatureStyle getFeatureStyle(FeatureRow featureRow) {
		return getFeatureStyle(featureRow.getId(), featureRow.getGeometryType());
	}

	/**
	 * Get the feature style default (style and icon) of the feature row,
	 * searching in order: feature default style or icon, table default style or
	 * icon
	 * 
	 * @param featureRow
	 *            feature row
	 * @return feature style
	 */
	public FeatureStyle getFeatureStyleDefault(FeatureRow featureRow) {
		return getFeatureStyle(featureRow.getId(), null);
	}

	/**
	 * Get the feature style (style and icon) of the feature, searching in
	 * order: feature geometry type style or icon, feature default style or
	 * icon, table geometry type style or icon, table default style or icon
	 * 
	 * @param featureId
	 *            feature id
	 * @param geometryType
	 *            geometry type
	 * @return feature style
	 */
	public FeatureStyle getFeatureStyle(long featureId,
			GeometryType geometryType) {

		FeatureStyle featureStyle = null;

		StyleRow style = getStyle(featureId, geometryType);
		IconRow icon = getIcon(featureId, geometryType);

		if (style != null || icon != null) {
			featureStyle = new FeatureStyle(style, icon);
		}

		return featureStyle;
	}

	/**
	 * Get the feature style (style and icon) of the feature, searching in
	 * order: feature geometry type style or icon, feature default style or
	 * icon, table geometry type style or icon, table default style or icon
	 * 
	 * @param featureId
	 *            feature id
	 * @return feature style
	 */
	public FeatureStyle getFeatureStyleDefault(long featureId) {
		return getFeatureStyle(featureId, null);
	}

	/**
	 * Get the styles for the feature row
	 * 
	 * @param featureRow
	 *            feature row
	 * @return styles or null
	 */
	public Styles getStyles(FeatureRow featureRow) {
		return featureStyleExtension.getStyles(featureRow);
	}

	/**
	 * Get the styles for the feature id
	 * 
	 * @param featureId
	 *            feature id
	 * @return styles or null
	 */
	public Styles getStyles(long featureId) {
		return featureStyleExtension.getStyles(tableName, featureId);
	}

	/**
	 * Get the style of the feature row, searching in order: feature geometry
	 * type style, feature default style, table geometry type style, table
	 * default style
	 * 
	 * @param featureRow
	 *            feature row
	 * @return style row
	 */
	public StyleRow getStyle(FeatureRow featureRow) {
		return getStyle(featureRow.getId(), featureRow.getGeometryType());
	}

	/**
	 * Get the default style of the feature row, searching in order: feature
	 * default style, table default style
	 * 
	 * @param featureRow
	 *            feature row
	 * @return style row
	 */
	public StyleRow getStyleDefault(FeatureRow featureRow) {
		return getStyle(featureRow.getId(), null);
	}

	/**
	 * Get the style of the feature, searching in order: feature geometry type
	 * style, feature default style, table geometry type style, table default
	 * style
	 * 
	 * @param featureId
	 *            feature id
	 * @param geometryType
	 *            geometry type
	 * @return style row
	 */
	public StyleRow getStyle(long featureId, GeometryType geometryType) {

		StyleRow styleRow = featureStyleExtension.getStyle(tableName,
				featureId, geometryType, false);

		if (styleRow == null) {

			// Table Style
			Styles styles = getCachedTableStyles();
			if (styles != null) {
				styleRow = styles.getStyle(geometryType);
			}

		}

		return styleRow;
	}

	/**
	 * Get the default style of the feature, searching in order: feature default
	 * style, table default style
	 * 
	 * @param featureId
	 *            feature id
	 * @return style row
	 */
	public StyleRow getStyleDefault(long featureId) {
		return getStyle(featureId, null);
	}

	/**
	 * Get the icons for the feature row
	 * 
	 * @param featureRow
	 *            feature row
	 * @return icons or null
	 */
	public Icons getIcons(FeatureRow featureRow) {
		return featureStyleExtension.getIcons(featureRow);
	}

	/**
	 * Get the icons for the feature id
	 * 
	 * @param featureId
	 *            feature id
	 * @return icons or null
	 */
	public Icons getIcons(long featureId) {
		return featureStyleExtension.getIcons(tableName, featureId);
	}

	/**
	 * Get the icon of the feature row, searching in order: feature geometry
	 * type icon, feature default icon, table geometry type icon, table default
	 * icon
	 * 
	 * @param featureRow
	 *            feature row
	 * @return icon row
	 */
	public IconRow getIcon(FeatureRow featureRow) {
		return getIcon(featureRow.getId(), featureRow.getGeometryType());
	}

	/**
	 * Get the default icon of the feature row, searching in order: feature
	 * default icon, table default icon
	 * 
	 * @param featureRow
	 *            feature row
	 * @return icon row
	 */
	public IconRow getIconDefault(FeatureRow featureRow) {
		return getIcon(featureRow.getId(), null);
	}

	/**
	 * Get the icon of the feature, searching in order: feature geometry type
	 * icon, feature default icon, table geometry type icon, table default icon
	 * 
	 * @param featureId
	 *            feature id
	 * @param geometryType
	 *            geometry type
	 * @return icon row
	 */
	public IconRow getIcon(long featureId, GeometryType geometryType) {

		IconRow iconRow = featureStyleExtension.getIcon(tableName, featureId,
				geometryType, false);

		if (iconRow == null) {

			// Table Icon
			Icons icons = getCachedTableIcons();
			if (icons != null) {
				iconRow = icons.getIcon(geometryType);
			}

		}

		return iconRow;
	}

	/**
	 * Get the default icon of the feature, searching in order: feature default
	 * icon, table default icon
	 * 
	 * @param featureId
	 *            feature id
	 * @return icon row
	 */
	public IconRow getIconDefault(long featureId) {
		return getIcon(featureId, null);
	}

	/**
	 * Set the feature table default feature styles
	 * 
	 * @param featureStyles
	 *            default feature styles
	 */
	public void setTableFeatureStyles(FeatureStyles featureStyles) {
		featureStyleExtension.setTableFeatureStyles(tableName, featureStyles);
		clearCachedTableFeatureStyles();
	}

	/**
	 * Set the feature table default styles
	 * 
	 * @param styles
	 *            default styles
	 */
	public void setTableStyles(Styles styles) {
		featureStyleExtension.setTableStyles(tableName, styles);
		clearCachedTableStyles();
	}

	/**
	 * Set the feature table style default
	 * 
	 * @param style
	 *            style row
	 */
	public void setTableStyleDefault(StyleRow style) {
		featureStyleExtension.setTableStyleDefault(tableName, style);
		clearCachedTableStyles();
	}

	/**
	 * Set the feature table style for the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 * @param style
	 *            style row
	 */
	public void setTableStyle(GeometryType geometryType, StyleRow style) {
		featureStyleExtension.setTableStyle(tableName, geometryType, style);
		clearCachedTableStyles();
	}

	/**
	 * Set the feature table default icons
	 * 
	 * @param icons
	 *            default icons
	 */
	public void setTableIcons(Icons icons) {
		featureStyleExtension.setTableIcons(tableName, icons);
		clearCachedTableIcons();
	}

	/**
	 * Set the feature table icon default
	 * 
	 * @param icon
	 *            icon row
	 */
	public void setTableIconDefault(IconRow icon) {
		featureStyleExtension.setTableIconDefault(tableName, icon);
		clearCachedTableIcons();
	}

	/**
	 * Set the feature table icon for the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 * @param icon
	 *            icon row
	 */
	public void setTableIcon(GeometryType geometryType, IconRow icon) {
		featureStyleExtension.setTableIcon(tableName, geometryType, icon);
		clearCachedTableIcons();
	}

	/**
	 * Delete the feature table feature styles
	 */
	public void deleteTableFeatureStyles() {
		featureStyleExtension.deleteTableFeatureStyles(tableName);
		clearCachedTableFeatureStyles();
	}

	/**
	 * Delete the feature table styles
	 */
	public void deleteTableStyles() {
		featureStyleExtension.deleteTableStyles(tableName);
		clearCachedTableStyles();
	}

	/**
	 * Delete the feature table default style
	 */
	public void deleteTableStyleDefault() {
		featureStyleExtension.deleteTableStyleDefault(tableName);
		clearCachedTableStyles();
	}

	/**
	 * Delete the feature table style for the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 */
	public void deleteTableStyle(GeometryType geometryType) {
		featureStyleExtension.deleteTableStyle(tableName, geometryType);
		clearCachedTableStyles();
	}

	/**
	 * Delete the feature table icons
	 */
	public void deleteTableIcons() {
		featureStyleExtension.deleteTableIcons(tableName);
		clearCachedTableIcons();
	}

	/**
	 * Delete the feature table default icon
	 */
	public void deleteTableIconDefault() {
		featureStyleExtension.deleteTableIconDefault(tableName);
		clearCachedTableIcons();
	}

	/**
	 * Delete the feature table icon for the geometry type
	 * 
	 * @param geometryType
	 *            geometry type
	 */
	public void deleteTableIcon(GeometryType geometryType) {
		featureStyleExtension.deleteTableIcon(tableName, geometryType);
		clearCachedTableIcons();
	}

	/**
	 * Clear the cached table feature styles
	 */
	public void clearCachedTableFeatureStyles() {
		synchronized (cachedTableFeatureStyles) {
			cachedTableFeatureStyles.setStyles(null);
			cachedTableFeatureStyles.setIcons(null);
		}
	}

	/**
	 * Clear the cached table styles
	 */
	public void clearCachedTableStyles() {
		synchronized (cachedTableFeatureStyles) {
			cachedTableFeatureStyles.setStyles(null);
		}
	}

	/**
	 * Clear the cached table icons
	 */
	public void clearCachedTableIcons() {
		synchronized (cachedTableFeatureStyles) {
			cachedTableFeatureStyles.setIcons(null);
		}
	}

}

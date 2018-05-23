package mil.nga.geopackage.extension.related;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import mil.nga.geopackage.GeoPackage;
import mil.nga.geopackage.GeoPackageException;
import mil.nga.geopackage.db.CoreSQLUtils;
import mil.nga.geopackage.db.GeoPackageConnection;

/**
 * Related Tables extension
 * 
 * @author jyutzler
 */
public class RelatedTablesExtension extends RelatedTablesCoreExtension {

	/**
	 * GeoPackage connection
	 */
	private GeoPackageConnection connection;
	
	/**
	 * Constructor
	 * 
	 * @param geoPackage
	 *            GeoPackage
	 * 
	 */
	public RelatedTablesExtension(GeoPackage geoPackage) {
		super(geoPackage);
		connection = geoPackage.getConnection();
	}
	
	/**
	 * 
	 * @param extendedRelation
	 * @param baseId
	 * @return an array of IDs representing the matching related IDs
	 */
	public long[] getMappingsForBase(ExtendedRelation extendedRelation,
			long baseId) {
		Collection<Long> relatedIds = new HashSet<Long>();

		String sql = "select "
				+ CoreSQLUtils.quoteWrap(UserMappingTable.COLUMN_RELATED_ID)
				+ " from "
				+ CoreSQLUtils
						.quoteWrap(extendedRelation.getMappingTableName())
				+ " where "
				+ CoreSQLUtils.quoteWrap(UserMappingTable.COLUMN_BASE_ID)
				+ " = ?";

		ResultSet resultSet = connection.query(sql,
				new String[] { Long.toString(baseId) });
		try {
			while (resultSet.next()) {
				relatedIds.add(Long.valueOf(resultSet.getLong(extendedRelation
						.getMappingTable().getRelatedIdIndex())));
			}
			resultSet.close();
		} catch (SQLException e) {
			throw new GeoPackageException(
					"Failed to get mappings for relationship '"
							+ extendedRelation.getMappingTableName()
							+ "' between "
							+ extendedRelation.getBaseTableName() + " and "
							+ extendedRelation.getRelatedTableName(), e);
		}

		long[] result = new long[relatedIds.size()];
		Iterator<Long> iter = relatedIds.iterator();
		int inx = 0;
		while (iter.hasNext()) {
			result[inx++] = iter.next().longValue();
		}
		return result;
	}

	public long[] getMappingsForRelated(ExtendedRelation extendedRelation,
			long relatedId) {
		Collection<Long> baseIds = new HashSet<Long>();

		String sql = "select "
				+ CoreSQLUtils.quoteWrap(UserMappingTable.COLUMN_BASE_ID)
				+ " from "
				+ CoreSQLUtils
						.quoteWrap(extendedRelation.getMappingTableName())
				+ " where "
				+ CoreSQLUtils.quoteWrap(UserMappingTable.COLUMN_RELATED_ID)
				+ " = ?";

		ResultSet resultSet = connection.query(sql,
				new String[] { Long.toString(relatedId) });
		try {
			while (resultSet.next()) {
				baseIds.add(Long.valueOf(resultSet.getLong(extendedRelation
						.getMappingTable().getRelatedIdIndex())));
			}
			resultSet.close();
		} catch (SQLException e) {
			throw new GeoPackageException(
					"Failed to get reverse mappings for relationship '"
							+ extendedRelation.getMappingTableName()
							+ "' between "
							+ extendedRelation.getBaseTableName() + " and "
							+ extendedRelation.getRelatedTableName(), e);
		}

		long[] result = new long[baseIds.size()];
		Iterator<Long> iter = baseIds.iterator();
		int inx = 0;
		while (iter.hasNext()) {
			result[inx++] = iter.next().longValue();
		}
		return result;
	}
	
}

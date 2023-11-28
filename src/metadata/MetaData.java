package metadata;

import connection.DBSCon;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaData extends DBSCon {

    public static void displayTableNamesAndColumns() throws SQLException {
        try (Connection connection = connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);
                displayColumns(tableName);
                System.out.println();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
    }

    private static void displayColumns(String tableName) throws SQLException {
        try (Connection connection = connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, null);

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                System.out.println("  Column: " + columnName);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void displayColumnDetails() throws SQLException {
        try (Connection connection = connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                ResultSet columns = metaData.getColumns(null, null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("  Column: " + columnName + " Type: " + columnType);
                }
                System.out.println();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void displayPrimaryKeys() throws SQLException {
        try (Connection connection = connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                while (primaryKeys.next()) {
                    String primaryKeyColumnName = primaryKeys.getString("COLUMN_NAME");
                    System.out.println("  Primary Key: " + primaryKeyColumnName);
                }
                System.out.println();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void displayForeignKeys() throws SQLException {
        try (Connection connection = connect()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Table: " + tableName);

                ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
                while (foreignKeys.next()) {
                    String foreignKeyColumn = foreignKeys.getString("FKCOLUMN_NAME");
                    String referencedTableName = foreignKeys.getString("PKTABLE_NAME");
                    String referencedColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                    System.out.println("  Foreign Key: " + foreignKeyColumn +
                            " references " + referencedTableName + "(" + referencedColumnName + ")");
                }
                System.out.println();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}

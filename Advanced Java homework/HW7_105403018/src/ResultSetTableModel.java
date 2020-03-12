 /*
 105403018
    康瀚中
    資管2A
 */

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel
{

    private Connection connection;
    private Statement statement;
    private static ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows, Rows;
    private final String DEFAULT_QUERY = "SELECT * FROM people";//預設的查詢指令
    private List<Person> AllPersonList;//查詢出來的所有Person類別
    private boolean connectedToDatabase = false;

    public ResultSetTableModel(String url, String username, String password, String query)
            throws SQLException, ClassNotFoundException {

        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//resultSet只能夠讀
        connectedToDatabase = true;
        setQuery(query);
    }//end constructor

    public void setQuery(String query) throws SQLException, IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it
        resultSet = statement.executeQuery(query);
        // obtain meta data for ResultSet
        metaData = resultSet.getMetaData();
        // determine number of rows in ResultSet
        resultSet.last();                   // move to last row
        numberOfRows = resultSet.getRow();
        //System.out.println(numberOfRows);// 取得目前的row數量  
        updateAllPersonList();
        // notify JTable that model has changed
        fireTableStructureChanged();
    } // end method setQuery

    /**
     * **********************取得Column的Class type*****************************************
     */
    public Class getColumnClass(int column) throws IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase)
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        try 
        {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return Object.class; // if problems occur above, assume type Object
    } // end method getColumnClass

    /**
     * **********************取得Column的數量*****************************************
     */
    public int getColumnCount() throws IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine number of columns
        try 
        {
            return metaData.getColumnCount();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return 0; // if problems occur above, return 0 for number of columns
    } // end method getColumnCount

    /**
     * *******************取得column的名稱************************************
     */
    public String getColumnName(int column) throws IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase)
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // determine column name
        try
        {
            return metaData.getColumnName(column + 1);
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string for column name
    } // end method getColumnName

    /**
     * *********************取得row的數量**************************************
     */
    public int getRowCount() throws IllegalStateException
    {
        // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    } // end method getRowCount

    /**
     * *********************取得resultSet的內容數值*****************************
     */
    public Object getValueAt(int row, int column) throws IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // obtain a value at specified ResultSet row and column
        try 
        {
            resultSet.absolute(row + 1);//將指標指向 row+1
            return resultSet.getObject(column + 1);//取得數值
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch

        return ""; // if problems, return empty string object
    } // end method getValueAt

    /**
     * ********************updateQuery*****************************************
     */
    public void setQueryUpdate(String query) throws SQLException, IllegalStateException 
    {
        // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it
        statement.executeUpdate(query);
        setQuery(DEFAULT_QUERY);
        updateAllPersonList();
    } // end method setQuery

    /**
     * ************************update所有人的list********************************
     */
    public void updateAllPersonList()
    {
        AllPersonList = new ArrayList<Person>();//宣告一arraylist
        AllPersonList.clear();

        try {
            for (int i = 1; i <= numberOfRows; i++)
            {
                resultSet.absolute(i);

                Person person = new Person(
                        (Integer) resultSet.getObject(1), //studentID
                        (String) resultSet.getObject(2), //name
                        (String) resultSet.getObject(3), //phone
                        (String) resultSet.getObject(4), //email
                        (String) resultSet.getObject(5) //sex
                );
                AllPersonList.add(person);//加入arraylist中
            }
        } catch (SQLException e) {
        }
    }//end method updatePersonList()

    /**
     * ***************傳回某一個特定的Person類別 (於JTable選取時使用)**********
     */
    public Person getPerson(int selectedRow)
    {
        return AllPersonList.get(selectedRow);
    }

    /**
     * ******************** 回傳所有人的List******************************
     */
    public List<Person> getAllPersonList() 
    {
        AllPersonList = new ArrayList<Person>();
        AllPersonList.clear();

        try {
            for (int i = 1; i <= numberOfRows; i++)
            {
                resultSet.absolute(i);
                Person person = new Person(
                        (Integer) resultSet.getObject(1), //MemberID
                        (String) resultSet.getObject(2), //name
                        (String) resultSet.getObject(3), //phone
                        (String) resultSet.getObject(4), //mail
                        (String) resultSet.getObject(5));//sex

                AllPersonList.add(person);
            }
        } catch (SQLException e) {
        }
        return AllPersonList;
    }//end method getAllPersonList()
    /**
     * ******************找最大ID*************************************
     */
    public int maxID() throws SQLException, IllegalStateException 
    {
    	int result;
    	 // ensure database connection is available
        if (!connectedToDatabase) 
        {
            throw new IllegalStateException("Not Connected to Database");
        }

        // specify query and execute it
        resultSet = statement.executeQuery("SELECT MAX(MemberID) FROM people");
        result = resultSet.getInt(getRowCount());
        setQuery(DEFAULT_QUERY);
        updateAllPersonList();
        return result;
        
    }
    /**
     * ******************結束與DB的連結*************************************
     */
    public void disconnectFromDatabase() 
    {
        if (!connectedToDatabase)
        {
            return;
        }

        // close Statement and Connection            
        try 
        {
            statement.close();
            connection.close();
        } // end try                                 
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
        } // end catch                               
        finally // update database connection status
        {
            connectedToDatabase = false;
        } // end finally                             
    } // end method disconnectFromDatabase

}//end ResultSetTableModel
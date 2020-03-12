/*
 105403018
    康瀚中
    資管2A
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.RowFilter;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;

public class DisplayTest extends JFrame implements ActionListener 
{

    static final String DATABASE_URL = "jdbc:mysql://localhost/member?useUnicode=yes&characterEncoding=UTF-8";//設定資料庫:資料庫ip/資料庫名稱，並設定編碼utf-8以防亂碼
    static final String USERNAME = "java";
    static final String PASSWORD = "java";
    static final String DEFAULT_QUERY = "SELECT * FROM people";
    ResultSetTableModel tableModel;
    private int selectedPersonID;
    private TitledBorder title = BorderFactory.createTitledBorder("Find a entry by a field");
    private JLabel SerByID = new JLabel("Fieled Name:");
    private String[] f = {"MemberID", "name", "phone", "e_mail", "sex"};
    private JComboBox Fieleds = new JComboBox<String>(f);
    private JLabel IDlab = new JLabel("Member ID:");
    private JLabel namelab = new JLabel("Name:");
    private JLabel phonelab = new JLabel("Phone Number:");
    private JLabel maillab = new JLabel("Email:");
    private JLabel sexlab = new JLabel("Sex:");    

    private JTextField searchfield = new JTextField();
    private JTextField IDfield = new JTextField();
    private JTextField namefield = new JTextField();
    private JTextField phonefield = new JTextField();
    private JTextField mailfield = new JTextField();
    private JTextField sexfield = new JTextField();

    private JButton findbutton = new JButton("Find");
    private int findskey = 0;
    private JButton showall = new JButton("Browse All Entry");
    private JButton insert = new JButton("Insert New Entry");
    private JButton update = new JButton("update");
    private JButton delete = new JButton("delete");
    //private JPanel Npanel = new JPanel();
    private JPanel Wpanel = new JPanel();
    private JPanel Spanel = new JPanel();
    private JTable resultTable;

    public DisplayTest() throws ClassNotFoundException
    {
        super("Member Database");
        try {
            tableModel = new ResultSetTableModel(DATABASE_URL,
                    USERNAME, PASSWORD, DEFAULT_QUERY);
            resultTable = new JTable(tableModel);
            setLayout(new BorderLayout());
            Box Npanel = Box.createHorizontalBox();
            Npanel.setBorder(title);
            add(Npanel, BorderLayout.NORTH);

            Npanel.add(SerByID);
            Npanel.add(Fieleds);
            Npanel.add(searchfield);
            Npanel.add(findbutton);
            add(Wpanel, BorderLayout.WEST);
            Wpanel.setLayout(new GridLayout(5, 2));
            Wpanel.add(IDlab);
            Wpanel.add(IDfield);
            Wpanel.add(namelab);
            Wpanel.add(namefield);
            Wpanel.add(phonelab);
            Wpanel.add(phonefield);
            Wpanel.add(maillab);
            Wpanel.add(mailfield);
            Wpanel.add(sexlab);
            Wpanel.add(sexfield);            
            IDfield.setEditable(false);
            add(new JScrollPane(resultTable), BorderLayout.CENTER);
            add(Spanel, BorderLayout.SOUTH);
            Spanel.setLayout(new GridLayout(1, 4, 5, 5));
            Spanel.add(showall);
            Spanel.add(insert);
            Spanel.add(update);
            Spanel.add(delete);

            //JTable使用RowSorter來排序
            RowSorter<ResultSetTableModel> sorter = new TableRowSorter<ResultSetTableModel>(tableModel);
            resultTable.setRowSorter(sorter);

            ListSelectionModel rowSM = resultTable.getSelectionModel();
            rowSM.addListSelectionListener
            (
                    new ListSelectionListener() 
                    {
                    	public void valueChanged(ListSelectionEvent e) 
                    	{
                    		// Ignore extra messages.
                    		if (e.getValueIsAdjusting()) 
                    		{
                    				return;
                    		}

                    		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

                    		if (lsm.isSelectionEmpty())
                    		{
                    		} 
                    		else
                    		{
                    			int selectedRow = lsm.getMinSelectionIndex();
                    			queryPerson(selectedRow);
                    		}
                    	}
                    }
            );
        } 
        catch (SQLException sqlException) 
        {
            JOptionPane.showMessageDialog(null, sqlException.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            // ensure database connection is closed
            //tableModel.disconnectFromDatabase();
            System.exit(1); // terminate application
        }
        insert.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        findbutton.addActionListener(this);
        showall.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == insert)
        {		//插入資料
            if (isInCorrect()) 
            {
                try 
                {	
                	
                	
                    tableModel.setQueryUpdate("INSERT INTO `people` (`MemberID`,`name`,`phone`,`e_mail`,`sex`) VALUES('"+tableModel.maxID()+"','" + namefield.getText() + "','" + phonefield.getText() + "','" + mailfield.getText() + "','" + sexfield.getText() + "')");
                    JOptionPane.showMessageDialog(null, "New Entry is inserted.");
                } 
                catch (SQLException sqlException) 
                {
                    JOptionPane.showMessageDialog(null,
                            sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                }
                clearTextfield();
            }
            

        }
        if (e.getSource() == delete) 
        {		//刪除資料
            try
            {
                tableModel.setQueryUpdate("DELETE FROM `member`.`people` WHERE `MemberID`='" + IDfield.getText() + "'");
                IDfield.setText("");
                JOptionPane.showMessageDialog(null, "People deleted.");
            } catch (SQLException sqlException) 
            {
                JOptionPane.showMessageDialog(null,
                        sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == update)
        {
            if (isInCorrect())
            {
                try 
                {
                    tableModel.setQueryUpdate("UPDATE `people` SET `name`='" + namefield.getText() + "',`phone`='" + phonefield.getText() + "',`e_mail`='" + mailfield.getText() + "',`sex`='" + sexfield.getText() + "' WHERE `MemberID`='" + IDfield.getText() + "'");
                    JOptionPane.showMessageDialog(null, "People updated.");
                } catch (SQLException sqlException)
                {
                    JOptionPane.showMessageDialog(null,
                            sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
                }
                clearTextfield();
            }
            

        }
        if (e.getSource() == findbutton)
        {
            findskey = Fieleds.getSelectedIndex();

            try 
            {
                if (searchfield.getText() == "") 
                {
                    tableModel.setQuery("SELECT * FROM `people`");
                }
                else 
                {
                    tableModel.setQuery("SELECT * FROM `people` WHERE `" + f[findskey] + "` LIKE '%" + searchfield.getText() + "%'");
                }
            }
            catch (SQLException sqlException) 
            {
                JOptionPane.showMessageDialog(null,
                        sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == showall)
        {
            try 
            {
                tableModel.setQuery("SELECT * FROM `people`");
            } 
            catch (SQLException sqlException) 
            {
                JOptionPane.showMessageDialog(null,
                        sqlException.getMessage(), "Database error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void clearTextfield(){
        namefield.setText("");
        phonefield.setText("");
        mailfield.setText("");
        sexfield.setText("");
        IDfield.setText("");
    }
    private boolean isInCorrect()
    {
        if(namefield.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                        "請輸入姓名!", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!phonefield.getText().matches("[0-9]{4}-[0-9]{6}"))
        {
            JOptionPane.showMessageDialog(null,
                        "手機格式錯誤!", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!mailfield.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
        {
            JOptionPane.showMessageDialog(null,
                        "信箱格式錯誤!", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(sexfield.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,
                        "請輸入性別!", "格式錯誤", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void queryPerson(int selectedRow) 
    {
        Person person = tableModel.getPerson(selectedRow);

        IDfield.setText(Integer.toString(person.getMemberID()));//將int轉為String
        namefield.setText(person.getName());
        phonefield.setText(person.getPhone());
        mailfield.setText(person.getMail());
        sexfield.setText(person.getSex());

        selectedPersonID = person.getMemberID();
    } // end method queryPerson

    public static void main(String args[]) throws ClassNotFoundException
    {
        DisplayTest display = new DisplayTest();
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(800, 450);
        display.setVisible(true);
    } // end main
}
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ViewDatabase {
	
	String name;
	String user;
	String pswrd;
	JFrame f;
	backB b = new backB();
//	clearRow cr = new clearRow();
//	clearTable ct = new clearTable();
	clearAll ca = new clearAll();
	insert in = new insert();
	JPopupMenu rightClick;
	changeVal cv = new changeVal();
	deleteVal dv = new deleteVal();
	refreshTable rt = new refreshTable();
	int column;
	Object obj, obj1, obj2;
	JTable jt, jt1, jt2;
	int tableNumber;
	String m;
	
	public ViewDatabase(String user_id, String name, String pswrd, String m) {
		
		user = user_id;
		this.name = name;
		this.pswrd = pswrd;
		this.m = m;
		
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = "root";
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("COURSE ID");
		model.addColumn("COURSE NAME");
		model.addColumn("COURSE MARK");
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("COURSE ID");
		model1.addColumn("UNIT ID");
		model1.addColumn("UNIT WEIGHT");
		model1.addColumn("UNIT FINAL");
		model1.addColumn("COURSE NAME");
		model1.addColumn("UNIT NAME");
		model1.addColumn("UNIT MARK");
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("COURSE ID");
		model2.addColumn("UNIT ID");
		model2.addColumn("ASSIGNMENT ID");
		model2.addColumn("ASSIGNMENT MARK");
		model2.addColumn("COURSE NAME");
		model2.addColumn("UNIT NAME");
		model2.addColumn("ASSIGNMENT NAME");
		
 	   try {
    	   Connection connection = DriverManager.getConnection(url, username, password);

 			f = new JFrame();
 			f.setBackground(Color.WHITE);
 			f.setTitle("YOUR GRADE HISTORY");
			GridBagLayout gbl = new GridBagLayout();
 			GridBagConstraints c = new GridBagConstraints();
 			f.setLayout(gbl);
 			
	 	   String sql = "SELECT course_id, course_name, course_mark FROM course WHERE user_id = ?";
	 	   PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user);
	 	   
	 	   ResultSet rs = statement.executeQuery();
	 	   
	 	   
	 	   while(rs.next()) {
	 		   String cID = rs.getString("course_id");
	 		   String cNAME = rs.getString("course_name");
	 		   String cMARK = rs.getString("course_mark");
	 		   
	            String[] data = {cID, cNAME, cMARK};
	            
	            model.addRow(data);
	 	   }
	 	   
		    jt = new JTable(model) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
		    };
		    jt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    
		    jt.getTableHeader().setReorderingAllowed(false);
		    
		    jt.addMouseListener(new MouseAdapter() {
		    	  public void mouseClicked(MouseEvent e) {
		    	      JTable target = (JTable)e.getSource();
		    	      int row = target.getSelectedRow();
		    	      column = target.getSelectedColumn();
		    	      obj = jt.getValueAt(row, 0);
		    	      System.out.println(obj);
		    	      System.out.println(row);
		    	      System.out.println(column);
		    	      System.out.println("left click!");
		    	      if (column == 1) {
			    	      if(e.getButton() == MouseEvent.BUTTON3) {
			    	     	System.out.println("Add menu!");
			    	     	displayPopup();
			    	     	tableNumber = 1;
			    	     	rightClick.show(e.getComponent(), e.getX(), e.getY());
			    	      }
		    	      }
		    	 }
		    });
		    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		    
		 	   String sql1 = "SELECT course_id, course_name, unit_w, unit_id, unit_final, unit_name, unit_mark FROM unit WHERE user_id = ? ORDER BY COURSE_ID";
		 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setString(1, user);
		 	   
		 	   ResultSet rs1 = statement1.executeQuery();
		 	   
		 	   
		 	   while(rs1.next()) {
		 		   String cID = rs1.getString("course_id");
		 		   String uID = rs1.getString("unit_id");
		 		   String uW = rs1.getString("unit_w");
		 		   String uFINAL = rs1.getString("unit_final");
		 		   String cNAME = rs1.getString("course_name");
		 		   String uNAME = rs1.getString("unit_name");
		 		   String uMARK = rs1.getString("unit_mark");
		 		   
		            String[] data = {cID, uID, uW, uFINAL, cNAME, uNAME, uMARK};
		            
		            model1.addRow(data);
		 	   }
		 	   
			    jt1 = new JTable(model1) {
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {                
		                return false;               
		        }
			    };
			    jt1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			    
			    jt1.getTableHeader().setReorderingAllowed(false);
			    
			    jt1.addMouseListener(new MouseAdapter() {
			    	  public void mouseClicked(MouseEvent e) {
			    	      JTable target = (JTable)e.getSource();
			    	      int row = target.getSelectedRow();
			    	      column = target.getSelectedColumn();
			    	      obj = jt1.getValueAt(row, 0);
			    	      obj1 = jt1.getValueAt(row, 1);
			    	      System.out.println(obj);
			    	      System.out.println(row);
			    	      System.out.println(column);
			    	      System.out.println("left click!");
			    	      if (column == 2 || column == 3 || column == 5) {
				    	      if(e.getButton() == MouseEvent.BUTTON3) {
				    	     	System.out.println("Add menu!");
				    	     	displayPopup();
				    	     	tableNumber = 2;
				    	     	rightClick.show(e.getComponent(), e.getX(), e.getY());
				    	      }
			    	      }
			    	 }
			    });
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    
			    
			 	   String sql2 = "SELECT course_id, assign_id, assign_mark, unit_id, course_name, unit_name, assign_name FROM assignments WHERE user_id = ? ORDER BY COURSE_ID, UNIT_ID";
			 	   PreparedStatement statement2 = connection.prepareStatement(sql2);
				   statement2.setString(1, user);
			 	   
			 	   ResultSet rs2 = statement2.executeQuery();
			 	   
			 	   while(rs2.next()) {
		                
			 		   String cID = rs2.getString("course_id");
			 		   String uID = rs2.getString("unit_id");
			 		   String aID = rs2.getString("assign_id");
			 		   String aMARK = rs2.getString("assign_mark");
			 		   String cNAME = rs2.getString("course_name");
			 		   String uNAME = rs2.getString("unit_name");
			 		   String aNAME = rs2.getString("assign_name");
			 		   
			            String[] data = {cID, uID, aID, aMARK, cNAME, uNAME, aNAME};
			            
			            model2.addRow(data);
			 	   }
			 	   
				    jt2 = new JTable(model2) {
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {                
			                return false;               
			        }
				    };
				    jt2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				    
				    jt2.getTableHeader().setReorderingAllowed(false);
				    
				    jt2.addMouseListener(new MouseAdapter() {
				    	  public void mouseClicked(MouseEvent e) {
				    	      JTable target = (JTable)e.getSource();
				    	      int row = target.getSelectedRow();
				    	      column = target.getSelectedColumn();
				    	      obj = jt2.getValueAt(row, 0);
				    	      obj1 = jt2.getValueAt(row, 1);
				    	      obj2 = jt2.getValueAt(row, 2);
				    	      System.out.println(obj);
				    	      System.out.println(row);
				    	      System.out.println(column);
				    	      System.out.println("left click!");
				    	      if (column == 3 || column == 6) {
					    	      if(e.getButton() == MouseEvent.BUTTON3) {
					    	     	System.out.println("Add menu!");
					    	     	displayPopup();
					    	     	tableNumber = 3;
					    	     	rightClick.show(e.getComponent(), e.getX(), e.getY());
					    	      }
				    	      }
				    	 }
				    });
			    
 		    JScrollPane sp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 		            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 		    JScrollPane sp1 = new JScrollPane(jt1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 		            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 		    JScrollPane sp2 = new JScrollPane(jt2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
 		            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 		    
 			JButton backButton = new JButton("BACK");
 			backButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			backButton.addActionListener(b);
 			backButton.setBackground(Color.WHITE);
 			
 			JButton clearAll = new JButton("CLEAR ALL");
 			clearAll.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			clearAll.addActionListener(ca);
 			clearAll.setBackground(Color.WHITE);
 			
 			JButton refreshButton = new JButton("REFRESH");
 			refreshButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			refreshButton.addActionListener(rt);
 			refreshButton.setBackground(Color.WHITE);
 			
/* 			JButton clearSelected = new JButton("CLEAR ROWS");
 			clearSelected.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			clearSelected.addActionListener(cr);
 			clearSelected.setBackground(Color.WHITE); */
 			
 /*			JButton clearTable = new JButton("CLEAR TABLE");
 			clearTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			clearTable.addActionListener(ct);
 			clearTable.setBackground(Color.WHITE); */
 			
 			JButton insertVal = new JButton("INSERT");
 			insertVal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
 			insertVal.addActionListener(in);
 			insertVal.setBackground(Color.WHITE);
 			
 			JPanel p = new JPanel();
 			
 	 	    JLabel l = new JLabel("COURSES");
 			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
 			l.setHorizontalAlignment(SwingConstants.CENTER);
 			JLabel l1 = new JLabel("UNITS");
 			l1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
 			l1.setHorizontalAlignment(SwingConstants.CENTER);
 			JLabel l2 = new JLabel("ASSIGNMENTS");
 			l2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
 			l2.setHorizontalAlignment(SwingConstants.CENTER);
	 	    JLabel message = new JLabel(m);
	 	    message.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	 	    message.setHorizontalAlignment(SwingConstants.CENTER);

			GridBagLayout g = new GridBagLayout();
 			GridBagConstraints gbc = new GridBagConstraints();
 			p.setLayout(g);
 			gbc.fill = GridBagConstraints.HORIZONTAL;
 			gbc.gridx = 0;
 			gbc.gridy = 0;
 			p.add(backButton, gbc);
/* 			gbc.gridx = 1;
 			gbc.gridy = 0;
 			p.add(clearSelected, gbc); */
 			gbc.gridx = 2;
 			gbc.gridy = 0;
 			p.add(clearAll, gbc);
/* 			gbc.gridx = 3;
 			gbc.gridy = 0;
 			p.add(clearTable, gbc); */
 			gbc.gridx = 4;
 			gbc.gridy = 0;
 			p.add(insertVal, gbc);
 			gbc.gridx = 5;
 			gbc.gridy = 0;
 			p.add(refreshButton, gbc);
 			
 			
 		    sp.getViewport().setBackground(Color.white);
 		    sp.setPreferredSize(new Dimension(900, 200));
		    
 		    sp1.getViewport().setBackground(Color.white);
 		    sp1.setPreferredSize(new Dimension(900, 200));
		    
		    sp2.getViewport().setBackground(Color.white);
 		    sp2.setPreferredSize(new Dimension(900, 200));

 			c.fill = GridBagConstraints.HORIZONTAL;
 			c.gridx = 0;
 			c.gridy = 0;
 			f.add(l, c);
 			c.gridx = 0;
 			c.gridy = 1;
 			f.add(sp, c);
 			c.gridx = 0;
 			c.gridy = 2;
 			f.add(l1, c); 
 			c.gridx = 0;
 			c.gridy = 3;
 			f.add(sp1, c);
 			c.gridx = 0;
 			c.gridy = 4;
 			f.add(l2, c);
 			c.gridx = 0;
 			c.gridy = 5;
 			f.add(sp2, c);
 			c.gridx = 0;
 			c.gridy = 6;
 			f.add(p, c);
 			c.gridx = 0;
 			c.gridy = 7;
 			f.add(message, c);

		    f.pack();
 		    f.setResizable(false);
			f.setLocationRelativeTo(null);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
 	   } catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	     }
	}
	
	public class backB implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			f.setVisible(false);
			new UserMain(user, name, pswrd, m);
			System.out.println("BACK CLICKED!");
			
		}
	}
	
/*	public class clearRow implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new ClearRow(user, name, pswrd, f);
		}
	} */
	
	public class clearTable implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new ClearTable(user, name, pswrd, f);
		}
	}
	
	public class clearAll implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new ClearAll(user, name, pswrd, f);
		}
	}
	
	public class insert implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object[] options = {"New Course",
		                "New unit",
		                "New assignment"
            };
			JLabel label = new JLabel("INSERT");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
				
			int n = JOptionPane.showOptionDialog(null, label, null,
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
			if (n == 0) {
				new InsertCourse(user, name, pswrd, f);
			} else if (n == 1) {
				new InsertUnit(user, name, pswrd, f);
			} else if (n == 2) {
				new InsertAssignment(user, name, pswrd, f);
			}
		}
	}
	
	public void displayPopup() {

	    rightClick = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("Change value");
        m1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        m1.setBackground(Color.WHITE);
		m1.addActionListener(cv);
        JMenuItem m2 = new JMenuItem("Delete");
        m2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        m2.setBackground(Color.WHITE);
		m2.addActionListener(dv);
        rightClick.add(m1);
        rightClick.add(m2);
        f.add(rightClick);
	}
	
	public class changeVal implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("ChangeVal clicked");
			new ChangeVal(column, obj, obj1, obj2, tableNumber, f, user, name, pswrd);
		}
	}
	
	public class deleteVal implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("DeleteVal clicked");
			new DeleteVal(column, obj, obj1, obj2, tableNumber, f, user, name, pswrd);
		}
	}
	
	public class refreshTable implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Refresh clicked");
			new FixCalculations();
			f.setVisible(false);
			new ViewDatabase(user, name, pswrd, null);
		}
	}
	
}

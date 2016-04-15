import item.Armor;
import item.HealthPotion;
import item.ItemExceptionAttackPts;
import item.ItemExceptionDefensePts;
import item.ItemExceptionName;
import item.ItemExceptionPrice;
import item.ItemExceptionRange;
import item.ItemExceptionRestorePts;
import item.ItemExceptionRevivePts;
import item.RevivePotion;
import item.Weapon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import main.Board;
import main.Colors;
import main.Team;
import character.Character;
import character.Fighter;
import character.Ranger;


public class Battlefield {

	private JFrame frmBattlefield;
	
	private JPanel pnlBoard ;
	private JPanel pnlTeams;
	private JPanel pnlCharacter;
	private JPanel pnlItem;
	
	private JTabbedPane paginasTeam;
	private JTabbedPane paginasCharacter;
	private JTabbedPane paginasItem;
	
	private JLayeredPane pagTeams;
	private JLayeredPane pagFighter;
	private JLayeredPane pagRanger;
	private JLayeredPane pagWeapon;
	private JLayeredPane pagArmor;
	private JLayeredPane pagHealth;
	private JLayeredPane pagRevive;
	
	private JTextField txtTeamName;
	private JComboBox cbTeamColor;
	private JButton btnTeamCreate;
	
	private JTextField txtFighterAlias;
	private JComboBox cbFighterTeam;
	private JSlider sldFighterPower;
	private JSlider sldFighterStrength;
	private JSlider sldFighterSpeed;
	private JSlider sldFighterDexterity;
	private JSlider sldFighterConstitution;
	private JButton btnFighterCreate;
	private JLabel lblFighterValuePower;
	private JLabel lblFighterValueStrength;
	private JLabel lblFighterValueSpeed;
	private JLabel lblFighterValueDexterity;
	private JLabel lblFighterValueConstitution;
	
	private JTextField txtRangerAlias;
	private JComboBox cbRangerTeam;
	private JSlider sldRangerAccuracy;
	private JSlider sldRangerStrength;
	private JSlider sldRangerSpeed;
	private JSlider sldRangerDexterity;
	private JSlider sldRangerConstitution;
	private JButton btnRangerCreate;
	private JLabel lblRangerValueAccuracy;
	private JLabel lblRangerValueStrength;
	private JLabel lblRangerValueSpeed;
	private JLabel lblRangerValueDexterity;
	private JLabel lblRangerValueConstitution;
	
	private JTextField txtWeaponName;
	private JTextField txtWeaponPrice;
	private JTextField txtWeaponAttackPts;
	private JTextField txtWeaponRange;
	private JButton btnWeaponCreate;

	private JTextField txtArmorName;
	private JTextField txtArmorPrice;
	private JTextField txtArmorDefensePts;
	private JButton btnArmorCreate;
	
	private JTextField txtHealthName;
	private JTextField txtHealthPrice;
	private JTextField txtHealthRestorePts;
	private JButton btnHealthCreate;
	
	private JTextField txtReviveName;
	private JTextField txtRevivePrice;
	private JTextField txtReviveRevivePts;
	private JButton btnReviveCreate;
	
	private JTable table;
	
	private int FighterStrength = 25;
	private int FighterSpeed = 25;
	private int FighterDexterity = 25;
	private int FighterConstitution = 25;
	
	private int RangerStrength = 25;
	private int RangerSpeed = 25;
	private int RangerDexterity = 25;
	private int RangerConstitution = 25;
	
	private Board board;
	private Character firstChar, secondChar;
	private int selectionIndex = 0;
	private int firstCharXY;
	
	private JLabel lblStatusTitle;
	private JLabel lblStatusAlias;
	private JLabel lblStatusType;
	private JLabel lblStatusHP;
	private JLabel lblStatusXP;
	private JPanel pnlOutput;
	
	public static JTextArea txtOutput;
	private static PrintStream ps = null;  
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (IllegalAccessException ex) {
	            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (UnsupportedLookAndFeelException ex) {
	            Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		
		 EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battlefield window = new Battlefield();
					window.frmBattlefield.setVisible(true);
					System.setOut(window.getPs());  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Battlefield() {
		initialize();
		
		//Used to print the System.out on the txtOutput
		ps =  new PrintStream(System.out) {  
			public void println(String str) {  
				txtOutput.append(str + "\n");  
				txtOutput.setCaretPosition( txtOutput.getText().length() );
			} 
		};
		
		//Create the 10x10 board
		board = new Board(10,10);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Board
		pnlBoard = new JPanel();
		pnlBoard.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlBoard.setBounds(335, 6, 400, 400);
		
		//Frame
		frmBattlefield = new JFrame();
		frmBattlefield.setTitle("Battlefield");
		frmBattlefield.setResizable(false);
		frmBattlefield.setBounds(100, 100, 770, 650);
		frmBattlefield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattlefield.getContentPane().setLayout(null);
		frmBattlefield.setLocationRelativeTo(null);
		frmBattlefield.getContentPane().add(pnlBoard);
		pnlBoard.setLayout(new BorderLayout(0, 0));
		
		//Table (board)
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SelectedCell();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}) {
			
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
				};
				
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
		
		//Used to show the fighter/ranger icon on the board
		MultiRenderer multiRenderer = new MultiRenderer();
		multiRenderer.registerRenderer(ImageIcon.class,
									   table.getDefaultRenderer(ImageIcon.class));
		
		table.setMaximumSize(new Dimension(400, 400));
		table.setSize(new Dimension(400, 400));
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setMinimumSize(new Dimension(50, 50));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setRowHeight(40);
		for (int i = 0; i < 10; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(40);
			table.getColumnModel().getColumn(i).setMinWidth(40);
			table.getColumnModel().getColumn(i).setMaxWidth(40);
			table.getColumnModel().getColumn(i).setResizable(false);
			table.getColumnModel().getColumn(i).setCellRenderer(multiRenderer);
		}
		pnlBoard.add(table, BorderLayout.CENTER);
		
		//Panel Character
		pnlCharacter = new JPanel();
		pnlCharacter.setBounds(23, 147, 300, 273);
		frmBattlefield.getContentPane().add(pnlCharacter);
		pnlCharacter.setLayout(new BorderLayout(0, 0));
		
		paginasCharacter = new JTabbedPane(JTabbedPane.TOP);
		pnlCharacter.add(paginasCharacter);
		
		//Fighter
		pagFighter = new JLayeredPane();
		pagFighter.setToolTipText("Character");
		paginasCharacter.addTab("Fighter", null, pagFighter, null);
		pagFighter.setLayout(null);
		
		sldFighterPower = new JSlider();
		sldFighterPower.setMinimum(1);
		sldFighterPower.setValue(25);
		sldFighterPower.setBounds(84, 59, 154, 29);
		pagFighter.add(sldFighterPower);
		
		sldFighterStrength = new JSlider();
		sldFighterStrength.setMaximum(50);
		sldFighterStrength.setMinimum(1);
		sldFighterStrength.setValue(25);
		sldFighterStrength.setBounds(84, 85, 154, 29);
		pagFighter.add(sldFighterStrength);
		
		sldFighterSpeed = new JSlider();
		sldFighterSpeed.setMaximum(50);
		sldFighterSpeed.setMinimum(1);
		sldFighterSpeed.setValue(25);
		sldFighterSpeed.setBounds(84, 111, 154, 29);
		pagFighter.add(sldFighterSpeed);
		
		sldFighterDexterity = new JSlider();
		sldFighterDexterity.setMaximum(50);
		sldFighterDexterity.setMinimum(1);
		sldFighterDexterity.setValue(25);
		sldFighterDexterity.setBounds(84, 137, 154, 29);
		pagFighter.add(sldFighterDexterity);
		
		sldFighterConstitution = new JSlider();
		sldFighterConstitution.setMaximum(50);
		sldFighterConstitution.setMinimum(1);
		sldFighterConstitution.setValue(25);
		sldFighterConstitution.setBounds(84, 163, 154, 29);
		pagFighter.add(sldFighterConstitution);
		
		JLabel lblFighterAlias = new JLabel("Alias");
		lblFighterAlias.setBounds(6, 6, 61, 16);
		pagFighter.add(lblFighterAlias);
		
		txtFighterAlias = new JTextField();
		txtFighterAlias.setBounds(74, 0, 185, 28);
		pagFighter.add(txtFighterAlias);
		txtFighterAlias.setColumns(10);
		
		JLabel lblFighterStrength = new JLabel("Strength");
		lblFighterStrength.setBounds(6, 90, 61, 16);
		pagFighter.add(lblFighterStrength);
		
		JLabel lblFighterSpeed = new JLabel("Speed");
		lblFighterSpeed.setBounds(6, 116, 61, 16);
		pagFighter.add(lblFighterSpeed);
		
		JLabel lblFighterDexterity = new JLabel("Dexterity");
		lblFighterDexterity.setBounds(6, 142, 61, 16);
		pagFighter.add(lblFighterDexterity);
		
		JLabel lblFighterConstitution = new JLabel("Constitution");
		lblFighterConstitution.setBounds(6, 168, 88, 16);
		pagFighter.add(lblFighterConstitution);
		
		JLabel lblFighterPower = new JLabel("Power");
		lblFighterPower.setBounds(6, 64, 61, 16);
		pagFighter.add(lblFighterPower);
		
		btnFighterCreate = new JButton("Create");
		btnFighterCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Fighter
				createFighter();
			}
		});
		btnFighterCreate.setBounds(173, 192, 100, 29);
		pagFighter.add(btnFighterCreate);
		
		lblFighterValuePower = new JLabel("25");
		lblFighterValuePower.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblFighterValuePower.setBounds(240, 65, 33, 16);
		pagFighter.add(lblFighterValuePower);
		
		lblFighterValueStrength = new JLabel("25");
		lblFighterValueStrength.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblFighterValueStrength.setBounds(240, 91, 33, 16);
		pagFighter.add(lblFighterValueStrength);
		
		lblFighterValueSpeed = new JLabel("25");
		lblFighterValueSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblFighterValueSpeed.setBounds(240, 117, 33, 16);
		pagFighter.add(lblFighterValueSpeed);
		
		lblFighterValueDexterity = new JLabel("25");
		lblFighterValueDexterity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblFighterValueDexterity.setBounds(240, 143, 33, 16);
		pagFighter.add(lblFighterValueDexterity);
		
		lblFighterValueConstitution = new JLabel("25");
		lblFighterValueConstitution.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblFighterValueConstitution.setBounds(240, 169, 33, 16);
		pagFighter.add(lblFighterValueConstitution);
		
		JLabel lblFighterTeam = new JLabel("Team");
		lblFighterTeam.setBounds(6, 34, 61, 16);
		pagFighter.add(lblFighterTeam);
		
		cbFighterTeam = new JComboBox();
		cbFighterTeam.setBounds(68, 30, 185, 27);
		pagFighter.add(cbFighterTeam);
		
		//Ranger
		pagRanger = new JLayeredPane();
		paginasCharacter.addTab("Ranger", null, pagRanger, null);
		pagRanger.setLayout(null);
		
		JLabel lblRangerAlias = new JLabel("Alias");
		lblRangerAlias.setBounds(6, 6, 61, 16);
		pagRanger.add(lblRangerAlias);
		
		txtRangerAlias = new JTextField();
		txtRangerAlias.setColumns(10);
		txtRangerAlias.setBounds(74, 0, 185, 28);
		pagRanger.add(txtRangerAlias);
		
		JLabel lblRangerAccuracy = new JLabel("Accuracy");
		lblRangerAccuracy.setBounds(6, 64, 61, 16);
		pagRanger.add(lblRangerAccuracy);
		
		sldRangerAccuracy = new JSlider();
		sldRangerAccuracy.setMinimum(1);
		sldRangerAccuracy.setValue(25);
		sldRangerAccuracy.setBounds(84, 59, 154, 29);
		pagRanger.add(sldRangerAccuracy);
		
		JLabel lblRangerStrength = new JLabel("Strength");
		lblRangerStrength.setBounds(6, 90, 61, 16);
		pagRanger.add(lblRangerStrength);
		
		sldRangerStrength = new JSlider();
		sldRangerStrength.setMaximum(50);
		sldRangerStrength.setMinimum(1);
		sldRangerStrength.setValue(25);
		sldRangerStrength.setBounds(84, 85, 154, 29);
		pagRanger.add(sldRangerStrength);
		
		JLabel lblRangerSpeed = new JLabel("Speed");
		lblRangerSpeed.setBounds(6, 116, 61, 16);
		pagRanger.add(lblRangerSpeed);
		
		sldRangerSpeed = new JSlider();
		sldRangerSpeed.setMaximum(50);
		sldRangerSpeed.setMinimum(1);
		sldRangerSpeed.setValue(25);
		sldRangerSpeed.setBounds(84, 111, 154, 29);
		pagRanger.add(sldRangerSpeed);
		
		JLabel lblRangerDexterity = new JLabel("Dexterity");
		lblRangerDexterity.setBounds(6, 142, 61, 16);
		pagRanger.add(lblRangerDexterity);
		
		sldRangerDexterity = new JSlider();
		sldRangerDexterity.setMaximum(50);
		sldRangerDexterity.setMinimum(1);
		sldRangerDexterity.setValue(25);
		sldRangerDexterity.setBounds(84, 137, 154, 29);
		pagRanger.add(sldRangerDexterity);
		
		JLabel lblRangerConstitution = new JLabel("Constitution");
		lblRangerConstitution.setBounds(6, 168, 88, 16);
		pagRanger.add(lblRangerConstitution);
		
		sldRangerConstitution = new JSlider();
		sldRangerConstitution.setMaximum(50);
		sldRangerConstitution.setMinimum(1);
		sldRangerConstitution.setValue(25);
		sldRangerConstitution.setBounds(84, 163, 154, 29);
		pagRanger.add(sldRangerConstitution);
		
		btnRangerCreate = new JButton("Create");
		btnRangerCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Ranger
				createRanger();
			}
		});
		btnRangerCreate.setBounds(173, 192, 100, 29);
		pagRanger.add(btnRangerCreate);
		
		lblRangerValueConstitution = new JLabel("25");
		lblRangerValueConstitution.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblRangerValueConstitution.setBounds(240, 169, 33, 16);
		pagRanger.add(lblRangerValueConstitution);
		
		lblRangerValueDexterity = new JLabel("25");
		lblRangerValueDexterity.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblRangerValueDexterity.setBounds(240, 143, 33, 16);
		pagRanger.add(lblRangerValueDexterity);
		
		lblRangerValueSpeed = new JLabel("25");
		lblRangerValueSpeed.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblRangerValueSpeed.setBounds(240, 117, 33, 16);
		pagRanger.add(lblRangerValueSpeed);
		
		lblRangerValueStrength = new JLabel("25");
		lblRangerValueStrength.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblRangerValueStrength.setBounds(240, 91, 33, 16);
		pagRanger.add(lblRangerValueStrength);
		
		lblRangerValueAccuracy = new JLabel("25");
		lblRangerValueAccuracy.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblRangerValueAccuracy.setBounds(240, 65, 33, 16);
		pagRanger.add(lblRangerValueAccuracy);
		
		JLabel lblRangerTeam = new JLabel("Team");
		lblRangerTeam.setBounds(6, 34, 61, 16);
		pagRanger.add(lblRangerTeam);
		
		cbRangerTeam = new JComboBox();
		cbRangerTeam.setBounds(68, 30, 185, 27);
		pagRanger.add(cbRangerTeam);
		
		//Panel Teams
		pnlTeams = new JPanel();
		pnlTeams.setBounds(23, 6, 300, 140);
		frmBattlefield.getContentPane().add(pnlTeams);
		pnlTeams.setLayout(new BorderLayout(0, 0));
		
		paginasTeam = new JTabbedPane(JTabbedPane.TOP);
		pnlTeams.add(paginasTeam);
		
		pagTeams = new JLayeredPane();
		paginasTeam.addTab("Teams", null, pagTeams, null);
		
		JLabel lblTeamName = new JLabel("Name");
		lblTeamName.setBounds(6, 6, 61, 16);
		pagTeams.add(lblTeamName);
		
		JLabel lblTeamColor = new JLabel("Color");
		lblTeamColor.setBounds(6, 34, 61, 16);
		pagTeams.add(lblTeamColor);
		
		txtTeamName = new JTextField();
		txtTeamName.setBounds(74, 0, 185, 28);
		pagTeams.add(txtTeamName);
		txtTeamName.setColumns(10);
		
		cbTeamColor = new JComboBox();
		cbTeamColor.setModel(new DefaultComboBoxModel(new String[] {"BLUE", "RED", "GREEN", "YELLOW", "WHITE"}));
		cbTeamColor.setBounds(68, 30, 185, 27);
		pagTeams.add(cbTeamColor);
		
		btnTeamCreate = new JButton("Create");
		btnTeamCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Team
				createTeam();
			}
		});
		btnTeamCreate.setBounds(173, 59, 100, 29);
		pagTeams.add(btnTeamCreate);
		
		
		//Panel Item
		pnlItem = new JPanel();
		pnlItem.setBounds(23, 422, 300, 194);
		frmBattlefield.getContentPane().add(pnlItem);
		pnlItem.setLayout(new BorderLayout(0, 0));
		
		paginasItem = new JTabbedPane(JTabbedPane.TOP);
		pnlItem.add(paginasItem);
		
		pagWeapon = new JLayeredPane();
		paginasItem.addTab("Weapon", null, pagWeapon, null);
		
		JLabel lblWeaponName = new JLabel("Name");
		lblWeaponName.setBounds(6, 6, 61, 16);
		pagWeapon.add(lblWeaponName);
		
		JLabel lblWeaponPrice = new JLabel("Price");
		lblWeaponPrice.setBounds(6, 34, 61, 16);
		pagWeapon.add(lblWeaponPrice);
		
		JLabel lblWeaponAttackPts = new JLabel("Attack");
		lblWeaponAttackPts.setBounds(6, 62, 61, 16);
		pagWeapon.add(lblWeaponAttackPts);
		
		JLabel lblWeaponRange = new JLabel("Range");
		lblWeaponRange.setBounds(6, 90, 61, 16);
		pagWeapon.add(lblWeaponRange);
		
		txtWeaponName = new JTextField();
		txtWeaponName.setBounds(74, 0, 185, 28);
		pagWeapon.add(txtWeaponName);
		txtWeaponName.setColumns(10);
		
		txtWeaponPrice = new JTextField();
		txtWeaponPrice.setBounds(74, 28, 185, 28);
		pagWeapon.add(txtWeaponPrice);
		txtWeaponPrice.setColumns(10);
		
		txtWeaponAttackPts = new JTextField();
		txtWeaponAttackPts.setBounds(74, 56, 185, 28);
		pagWeapon.add(txtWeaponAttackPts);
		txtWeaponAttackPts.setColumns(10);
		
		txtWeaponRange = new JTextField();
		txtWeaponRange.setBounds(74, 84, 185, 28);
		pagWeapon.add(txtWeaponRange);
		txtWeaponRange.setColumns(10);
		
		btnWeaponCreate = new JButton("Create");
		btnWeaponCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Weapon
				createWeapon();
			}
		});
		btnWeaponCreate.setBounds(173, 115, 100, 29);
		pagWeapon.add(btnWeaponCreate);
		
		pagArmor = new JLayeredPane();
		paginasItem.addTab("Armor", null, pagArmor, null);
		
		JLabel lblArmorName = new JLabel("Name");
		lblArmorName.setBounds(6, 6, 61, 16);
		pagArmor.add(lblArmorName);
		
		JLabel lblArmorPrice = new JLabel("Price");
		lblArmorPrice.setBounds(6, 34, 61, 16);
		pagArmor.add(lblArmorPrice);
		
		JLabel lblArmorDefensePts = new JLabel("Defense");
		lblArmorDefensePts.setBounds(6, 62, 61, 16);
		pagArmor.add(lblArmorDefensePts);
		
		txtArmorName = new JTextField();
		txtArmorName.setColumns(10);
		txtArmorName.setBounds(74, 0, 185, 28);
		pagArmor.add(txtArmorName);
		
		txtArmorPrice = new JTextField();
		txtArmorPrice.setColumns(10);
		txtArmorPrice.setBounds(74, 28, 185, 28);
		pagArmor.add(txtArmorPrice);
		
		txtArmorDefensePts = new JTextField();
		txtArmorDefensePts.setColumns(10);
		txtArmorDefensePts.setBounds(74, 56, 185, 28);
		pagArmor.add(txtArmorDefensePts);
		
		btnArmorCreate = new JButton("Create");
		btnArmorCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Armor
				createArmor();
			}
		});
		btnArmorCreate.setBounds(173, 115, 100, 29);
		pagArmor.add(btnArmorCreate);
		
		pagHealth = new JLayeredPane();
		paginasItem.addTab("Health", null, pagHealth, null);
		
		JLabel lblHealthName = new JLabel("Name");
		lblHealthName.setBounds(6, 6, 61, 16);
		pagHealth.add(lblHealthName);
		
		JLabel lblHealthPrice = new JLabel("Price");
		lblHealthPrice.setBounds(6, 34, 61, 16);
		pagHealth.add(lblHealthPrice);
		
		JLabel lblHealthRestorePts = new JLabel("Restore");
		lblHealthRestorePts.setBounds(6, 62, 61, 16);
		pagHealth.add(lblHealthRestorePts);
		
		txtHealthName = new JTextField();
		txtHealthName.setColumns(10);
		txtHealthName.setBounds(74, 0, 185, 28);
		pagHealth.add(txtHealthName);
		
		txtHealthPrice = new JTextField();
		txtHealthPrice.setColumns(10);
		txtHealthPrice.setBounds(74, 28, 185, 28);
		pagHealth.add(txtHealthPrice);
		
		txtHealthRestorePts = new JTextField();
		txtHealthRestorePts.setColumns(10);
		txtHealthRestorePts.setBounds(74, 56, 185, 28);
		pagHealth.add(txtHealthRestorePts);
		
		btnHealthCreate = new JButton("Create");
		btnHealthCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Health
				createHealth();
			}
		});
		btnHealthCreate.setBounds(173, 115, 100, 29);
		pagHealth.add(btnHealthCreate);
		
		pagRevive = new JLayeredPane();
		paginasItem.addTab("Revive", null, pagRevive, null);
		
		JLabel lblReviveName = new JLabel("Name");
		lblReviveName.setBounds(6, 6, 61, 16);
		pagRevive.add(lblReviveName);
		
		JLabel lblRevivePrice = new JLabel("Price");
		lblRevivePrice.setBounds(6, 34, 61, 16);
		pagRevive.add(lblRevivePrice);
		
		JLabel lblReviveRevivePts = new JLabel("Revive");
		lblReviveRevivePts.setBounds(6, 62, 61, 16);
		pagRevive.add(lblReviveRevivePts);
		
		txtReviveName = new JTextField();
		txtReviveName.setColumns(10);
		txtReviveName.setBounds(74, 0, 185, 28);
		pagRevive.add(txtReviveName);
		
		txtRevivePrice = new JTextField();
		txtRevivePrice.setColumns(10);
		txtRevivePrice.setBounds(74, 28, 185, 28);
		pagRevive.add(txtRevivePrice);
		
		txtReviveRevivePts = new JTextField();
		txtReviveRevivePts.setColumns(10);
		txtReviveRevivePts.setBounds(74, 56, 185, 28);
		pagRevive.add(txtReviveRevivePts);
		
		btnReviveCreate = new JButton("Create");
		btnReviveCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a new Revive
				createRevive();
			}
		});
		btnReviveCreate.setBounds(173, 115, 100, 29);
		pagRevive.add(btnReviveCreate);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlStatus.setBounds(335, 418, 400, 82);
		frmBattlefield.getContentPane().add(pnlStatus);
		pnlStatus.setLayout(null);
		
		lblStatusTitle = new JLabel("Characater Status");
		lblStatusTitle.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblStatusTitle.setBounds(6, 6, 228, 16);
		pnlStatus.add(lblStatusTitle);
		
		lblStatusAlias = new JLabel("");
		lblStatusAlias.setBounds(16, 34, 146, 16);
		pnlStatus.add(lblStatusAlias);
		
		lblStatusType = new JLabel("");
		lblStatusType.setBounds(16, 55, 142, 16);
		pnlStatus.add(lblStatusType);
		
		lblStatusHP = new JLabel("");
		lblStatusHP.setBounds(275, 34, 61, 16);
		pnlStatus.add(lblStatusHP);
		
		lblStatusXP = new JLabel("");
		lblStatusXP.setBounds(275, 55, 69, 16);
		pnlStatus.add(lblStatusXP);
		
		pnlOutput = new JPanel();
		pnlOutput.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlOutput.setBounds(335, 512, 400, 104);
		frmBattlefield.getContentPane().add(pnlOutput);
		pnlOutput.setLayout(new BorderLayout(0, 0));
		
		txtOutput = new JTextArea();
		txtOutput.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtOutput.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		txtOutput.setEditable(false);
		pnlOutput.add(txtOutput);
		
		JScrollPane scroll = new JScrollPane(txtOutput);
		pnlOutput.add(scroll);
		
		//Used when the values of the sliders change
		sldFighterPower.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblFighterValuePower.setText(""+value);
			}
		});
		sldFighterStrength.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblFighterValueStrength.setText(""+value);
			}
		});
		sldFighterStrength.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setFighterStrength(value);
			}
		});
		sldFighterSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblFighterValueSpeed.setText(""+value);
			}
		});
		sldFighterSpeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setFighterSpeed(value);
			}
		});
		sldFighterDexterity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblFighterValueDexterity.setText(""+value);
			}
		});
		sldFighterDexterity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setFighterDexterity(value);
			}
		});
		sldFighterConstitution.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblFighterValueConstitution.setText(""+value);
			}
		});
		sldFighterConstitution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setFighterConstitution(value);
			}
		});
		sldRangerAccuracy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblRangerValueAccuracy.setText(""+value);
			}
		});
		sldRangerStrength.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblRangerValueStrength.setText(""+value);
			}
		});
		sldRangerStrength.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setRangerStrength(value);
			}
		});
		sldRangerSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblRangerValueSpeed.setText(""+value);
			}
		});
		sldRangerSpeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setRangerSpeed(value);
			}
		});
		sldRangerDexterity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblRangerValueDexterity.setText(""+value);
			}
		});
		sldRangerDexterity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setRangerDexterity(value);
			}
		});
		sldRangerConstitution.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				lblRangerValueConstitution.setText(""+value);
			}
		});
		sldRangerConstitution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int value = ((JSlider)e.getSource()).getValue();
				setRangerConstitution(value);
			}
		});
		
		setCharacterDisabled();
		setItemDisabled();
		
	}
	
	/**
	 * Returns the application's printStream
	 * 
	 * @return printScream
	 */
	public PrintStream getPs() {  
		return ps;  
	}  

	/**
	 * Sets the components related to characters as disabled
	 */
	public void setCharacterDisabled() {
		
		paginasCharacter.setEnabled(false);
		pagFighter.setEnabled(false);
		pagRanger.setEnabled(false);
		txtFighterAlias.setEnabled(false);
		txtRangerAlias.setEnabled(false);
		sldFighterConstitution.setEnabled(false);
		sldFighterDexterity.setEnabled(false);
		sldFighterPower.setEnabled(false);
		sldFighterSpeed.setEnabled(false);
		sldFighterStrength.setEnabled(false);
		sldRangerAccuracy.setEnabled(false);
		sldRangerConstitution.setEnabled(false);
		sldRangerDexterity.setEnabled(false);
		sldRangerSpeed.setEnabled(false);
		sldRangerStrength.setEnabled(false);
		cbFighterTeam.setEnabled(false);
		cbRangerTeam.setEnabled(false);
		btnFighterCreate.setEnabled(false);
		btnRangerCreate.setEnabled(false);
		
	}

	/**
	 * Sets the components related to characters as enabled
	 */
	public void setCharacterEnabled() {
	
		paginasCharacter.setEnabled(true);
		pagFighter.setEnabled(true);
		pagRanger.setEnabled(true);
		txtFighterAlias.setEnabled(true);
		txtRangerAlias.setEnabled(true);
		sldFighterConstitution.setEnabled(true);
		sldFighterDexterity.setEnabled(true);
		sldFighterPower.setEnabled(true);
		sldFighterSpeed.setEnabled(true);
		sldFighterStrength.setEnabled(true);
		sldRangerAccuracy.setEnabled(true);
		sldRangerConstitution.setEnabled(true);
		sldRangerDexterity.setEnabled(true);
		sldRangerSpeed.setEnabled(true);
		sldRangerStrength.setEnabled(true);
		cbFighterTeam.setEnabled(true);
		cbRangerTeam.setEnabled(true);
		btnFighterCreate.setEnabled(true);
		btnRangerCreate.setEnabled(true);
		
	}
	
	/**
	 * Sets the components related to items as disabled
	 */
	public void setItemDisabled() {

		paginasItem.setEnabled(false);
		pagWeapon.setEnabled(false);
		pagArmor.setEnabled(false);
		pagHealth.setEnabled(false);
		pagRevive.setEnabled(false);
		txtArmorDefensePts.setEnabled(false);
		txtArmorName.setEnabled(false);
		txtArmorPrice.setEnabled(false);
		txtHealthName.setEnabled(false);
		txtHealthPrice.setEnabled(false);
		txtHealthRestorePts.setEnabled(false);
		txtReviveName.setEnabled(false);
		txtRevivePrice.setEnabled(false);
		txtReviveRevivePts.setEnabled(false);
		txtWeaponAttackPts.setEnabled(false);
		txtWeaponName.setEnabled(false);
		txtWeaponName.setEnabled(false);
		txtWeaponPrice.setEnabled(false);
		txtWeaponRange.setEnabled(false);
		btnArmorCreate.setEnabled(false);
		btnHealthCreate.setEnabled(false);
		btnReviveCreate.setEnabled(false);
		btnWeaponCreate.setEnabled(false);

	}
	
	/**
	 * Sets the components related to items as enabled
	 */
	public void setItemEnabled() {

		paginasItem.setEnabled(true);
		pagWeapon.setEnabled(true);
		pagArmor.setEnabled(true);
		pagHealth.setEnabled(true);
		pagRevive.setEnabled(true);
		txtArmorDefensePts.setEnabled(true);
		txtArmorName.setEnabled(true);
		txtArmorPrice.setEnabled(true);
		txtHealthName.setEnabled(true);
		txtHealthPrice.setEnabled(true);
		txtHealthRestorePts.setEnabled(true);
		txtReviveName.setEnabled(true);
		txtRevivePrice.setEnabled(true);
		txtReviveRevivePts.setEnabled(true);
		txtWeaponAttackPts.setEnabled(true);
		txtWeaponName.setEnabled(true);
		txtWeaponName.setEnabled(true);
		txtWeaponPrice.setEnabled(true);
		txtWeaponRange.setEnabled(true);
		btnArmorCreate.setEnabled(true);
		btnHealthCreate.setEnabled(true);
		btnReviveCreate.setEnabled(true);
		btnWeaponCreate.setEnabled(true);

	}
	
	/**
	 * Update the values of the fighter's sliders
	 */
	public void updateFighterSliders() {
		
		FighterStrength = sldFighterStrength.getValue();
		FighterSpeed = sldFighterSpeed.getValue();
		FighterDexterity = sldFighterDexterity.getValue();
		FighterConstitution = sldFighterConstitution.getValue();
		
		lblFighterValueStrength.setText(""+FighterStrength);
		lblFighterValueSpeed.setText(""+FighterSpeed);
		lblFighterValueDexterity.setText(""+FighterDexterity);
		lblFighterValueConstitution.setText(""+FighterConstitution);
		
	}
	
	/**
	 * Sets the fighters's new strength
	 * 
	 * @param strength
	 */
	public synchronized void setFighterStrength(int strength) {	
		int adder;
		boolean tictoc = true;
	
		if (FighterStrength > strength) adder = 1;
		else adder = -1;

		while((sldFighterConstitution.getValue() + sldFighterDexterity.getValue() + sldFighterSpeed.getValue() + strength) != 100){
			if ((tictoc) && (sldFighterSpeed.getValue()+adder!=0) && (sldFighterSpeed.getValue()+adder!=51))
				sldFighterSpeed.setValue(sldFighterSpeed.getValue() + adder);
			else if ((!tictoc) && (sldFighterDexterity.getValue()+adder!=0) && (sldFighterDexterity.getValue()+adder!=51))
				sldFighterDexterity.setValue(sldFighterDexterity.getValue() + adder);
			else if ( ((sldFighterSpeed.getValue()+adder!=0) || (sldFighterSpeed.getValue()+adder!=51)) &&
					((sldFighterDexterity.getValue()+adder!=0) || (sldFighterDexterity.getValue()+adder!=51)) )
				sldFighterConstitution.setValue(sldFighterConstitution.getValue() + adder);
				
			tictoc = !tictoc;
		}
	
		sldFighterStrength.setValue(strength);
		updateFighterSliders();
		System.out.println("Strength: " + sldFighterStrength.getValue());
	}
	
	/**
	 * Sets the fighter's new speed
	 * 
	 * @param speed
	 */
	public synchronized void setFighterSpeed(int speed) {	
		int adder;
		boolean tictoc = true;
	
		if (FighterSpeed > speed) adder = 1;
		else adder = -1;

		while((sldFighterConstitution.getValue() + sldFighterDexterity.getValue() + sldFighterStrength.getValue() + speed) != 100){
			if ((tictoc) && (sldFighterStrength.getValue()+adder!=0) && (sldFighterStrength.getValue()+adder!=51))
				sldFighterStrength.setValue(sldFighterStrength.getValue() + adder);
			else if ((!tictoc) && (sldFighterConstitution.getValue()+adder!=0) && (sldFighterConstitution.getValue()+adder!=51))
				sldFighterConstitution.setValue(sldFighterConstitution.getValue() + adder);
			else if ( ((sldFighterStrength.getValue()+adder!=0) || (sldFighterStrength.getValue()+adder!=51)) &&
					((sldFighterConstitution.getValue()+adder!=0) || (sldFighterConstitution.getValue()+adder!=51)) )
				sldFighterDexterity.setValue(sldFighterDexterity.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldFighterSpeed.setValue(speed);
		updateFighterSliders();
		System.out.println("Speed: " + sldFighterSpeed.getValue());
	}
	
	/**
	 * Sets the fighter's new dexterity
	 * 
	 * @param dexterity
	 */
	public synchronized void setFighterDexterity(int dexterity) {	
		int adder;
		boolean tictoc = true;
	
		if (FighterDexterity > dexterity) adder = 1;
		else adder = -1;

		while((sldFighterConstitution.getValue() + sldFighterSpeed.getValue() + sldFighterStrength.getValue() + dexterity) != 100){
			if ((tictoc) && (sldFighterSpeed.getValue()+adder!=0) && (sldFighterSpeed.getValue()+adder!=51))
				sldFighterSpeed.setValue(sldFighterSpeed.getValue() + adder);
			else if ((!tictoc) && (sldFighterConstitution.getValue()+adder!=0) && (sldFighterConstitution.getValue()+adder!=51))
				sldFighterConstitution.setValue(sldFighterConstitution.getValue() + adder);
			else if ( ((sldFighterSpeed.getValue()+adder!=0) || (sldFighterSpeed.getValue()+adder!=51)) &&
					((sldFighterConstitution.getValue()+adder!=0) || (sldFighterConstitution.getValue()+adder!=51)) )
				sldFighterStrength.setValue(sldFighterStrength.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldFighterDexterity.setValue(dexterity);
		updateFighterSliders();
		System.out.println("Dexterity: " + sldFighterDexterity.getValue());
	}
	
	/**
	 * Sets the fighter's new constitution
	 * 
	 * @param constitution
	 */
	public synchronized void setFighterConstitution(int constitution) {	
		int adder;
		boolean tictoc = true;
	
		if (FighterConstitution > constitution) adder = 1;
		else adder = -1;

		while((sldFighterSpeed.getValue() + sldFighterDexterity.getValue() + sldFighterStrength.getValue() + constitution) != 100){
			if ((tictoc) && (sldFighterStrength.getValue()+adder!=0) && (sldFighterStrength.getValue()+adder!=51))
				sldFighterStrength.setValue(sldFighterStrength.getValue() + adder);
			else if ((!tictoc) && (sldFighterDexterity.getValue()+adder!=0) && (sldFighterDexterity.getValue()+adder!=51))
				sldFighterDexterity.setValue(sldFighterDexterity.getValue() + adder);
			else if ( ((sldFighterStrength.getValue()+adder!=0) || (sldFighterStrength.getValue()+adder!=51)) &&
					((sldFighterDexterity.getValue()+adder!=0) || (sldFighterDexterity.getValue()+adder!=51)) )
				sldFighterSpeed.setValue(sldFighterSpeed.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldFighterConstitution.setValue(constitution);
		updateFighterSliders();
		System.out.println("Constitution: " + sldFighterConstitution.getValue());
	}
	
	/**
	 * Update the values of the ranger's sliders
	 */
	public void updateRangerSliders() {
		
		RangerStrength = sldRangerStrength.getValue();
		RangerSpeed = sldRangerSpeed.getValue();
		RangerDexterity = sldRangerDexterity.getValue();
		RangerConstitution = sldRangerConstitution.getValue();
		
		lblRangerValueStrength.setText(""+RangerStrength);
		lblRangerValueSpeed.setText(""+RangerSpeed);
		lblRangerValueDexterity.setText(""+RangerDexterity);
		lblRangerValueConstitution.setText(""+RangerConstitution);
		
	}
	
	/** 
	 * Sets the ranger's new strength
	 * 
	 * @param strength
	 */
	public synchronized void setRangerStrength(int strength) {	
		int adder;
		boolean tictoc = true;
	
		if (RangerStrength > strength) adder = 1;
		else adder = -1;

		while((sldRangerConstitution.getValue() + sldRangerDexterity.getValue() + sldRangerSpeed.getValue() + strength) != 100){
			if ((tictoc) && (sldRangerSpeed.getValue()+adder!=0) && (sldRangerSpeed.getValue()+adder!=51))
				sldRangerSpeed.setValue(sldRangerSpeed.getValue() + adder);
			else if ((!tictoc) && (sldRangerDexterity.getValue()+adder!=0) && (sldRangerDexterity.getValue()+adder!=51))
				sldRangerDexterity.setValue(sldRangerDexterity.getValue() + adder);
			else if ( ((sldRangerSpeed.getValue()+adder!=0) || (sldRangerSpeed.getValue()+adder!=51)) &&
					((sldRangerDexterity.getValue()+adder!=0) || (sldRangerDexterity.getValue()+adder!=51)) )
				sldRangerConstitution.setValue(sldRangerConstitution.getValue() + adder);
				
			tictoc = !tictoc;
		}
	
		sldRangerStrength.setValue(strength);
		updateRangerSliders();
		System.out.println("Strength: " + sldRangerStrength.getValue());
	}
	
	/**
	 * Sets the ranger's new speed
	 * 
	 * @param speed
	 */
	public synchronized void setRangerSpeed(int speed) {	
		int adder;
		boolean tictoc = true;
	
		if (RangerSpeed > speed) adder = 1;
		else adder = -1;

		while((sldRangerConstitution.getValue() + sldRangerDexterity.getValue() + sldRangerStrength.getValue() + speed) != 100){
			if ((tictoc) && (sldRangerStrength.getValue()+adder!=0) && (sldRangerStrength.getValue()+adder!=51))
				sldRangerStrength.setValue(sldRangerStrength.getValue() + adder);
			else if ((!tictoc) && (sldRangerConstitution.getValue()+adder!=0) && (sldRangerConstitution.getValue()+adder!=51))
				sldRangerConstitution.setValue(sldRangerConstitution.getValue() + adder);
			else if ( ((sldRangerStrength.getValue()+adder!=0) || (sldRangerStrength.getValue()+adder!=51)) &&
					((sldRangerConstitution.getValue()+adder!=0) || (sldRangerConstitution.getValue()+adder!=51)) )
				sldRangerDexterity.setValue(sldRangerDexterity.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldRangerSpeed.setValue(speed);
		updateRangerSliders();
		System.out.println("Speed: " + sldRangerSpeed.getValue());
	}
	
	/**
	 * Sets the ranger's new dexterity
	 * 
	 * @param dexterity
	 */
	public synchronized void setRangerDexterity(int dexterity) {	
		int adder;
		boolean tictoc = true;
	
		if (RangerDexterity > dexterity) adder = 1;
		else adder = -1;

		while((sldRangerConstitution.getValue() + sldRangerSpeed.getValue() + sldRangerStrength.getValue() + dexterity) != 100){
			if ((tictoc) && (sldRangerSpeed.getValue()+adder!=0) && (sldRangerSpeed.getValue()+adder!=51))
				sldRangerSpeed.setValue(sldRangerSpeed.getValue() + adder);
			else if ((!tictoc) && (sldRangerConstitution.getValue()+adder!=0) && (sldRangerConstitution.getValue()+adder!=51))
				sldRangerConstitution.setValue(sldRangerConstitution.getValue() + adder);
			else if ( ((sldRangerSpeed.getValue()+adder!=0) || (sldRangerSpeed.getValue()+adder!=51)) &&
					((sldRangerConstitution.getValue()+adder!=0) || (sldRangerConstitution.getValue()+adder!=51)) )
				sldRangerStrength.setValue(sldRangerStrength.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldRangerDexterity.setValue(dexterity);
		updateRangerSliders();
		System.out.println("Dexterity: " + sldRangerDexterity.getValue());
	}
	
	/**
	 * Sets the ranger's new constitution
	 * 
	 * @param constitution
	 */
	public synchronized void setRangerConstitution(int constitution) {	
		int adder;
		boolean tictoc = true;
	
		if (RangerConstitution > constitution) adder = 1;
		else adder = -1;

		while((sldRangerSpeed.getValue() + sldRangerDexterity.getValue() + sldRangerStrength.getValue() + constitution) != 100){
			if ((tictoc) && (sldRangerStrength.getValue()+adder!=0) && (sldRangerStrength.getValue()+adder!=51))
				sldRangerStrength.setValue(sldRangerStrength.getValue() + adder);
			else if ((!tictoc) && (sldRangerDexterity.getValue()+adder!=0) && (sldRangerDexterity.getValue()+adder!=51))
				sldRangerDexterity.setValue(sldRangerDexterity.getValue() + adder);
			else if ( ((sldRangerStrength.getValue()+adder!=0) || (sldRangerStrength.getValue()+adder!=51)) &&
					((sldRangerDexterity.getValue()+adder!=0) || (sldRangerDexterity.getValue()+adder!=51)) )
				sldRangerSpeed.setValue(sldRangerSpeed.getValue() + adder);
			
			tictoc = !tictoc;
		}
	
		sldRangerConstitution.setValue(constitution);
		updateRangerSliders();
		System.out.println("Constitution: " + sldRangerConstitution.getValue());
	}
	
	/**
	 * Method is called when a cell is selected on the board
	 */
	public void SelectedCell() {
	
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		int xy = board.getWidth()*row + col;
		
		//System.out.println("Selected cell ("+ row + "," + col + ")");
		
		//if the cell is empty
		if (cellIsEmpty(row,col)) {
			
			//System.out.println("Selected an empty cell");
			this.lblStatusAlias.setText("Empty cell ");
			this.lblStatusType.setText("");
			this.lblStatusHP.setText("");
			this.lblStatusXP.setText("");
			if(selectionIndex == 0) {
				setCharacterEnabled();
				setItemDisabled();
			} else {
				
				int oldRow = firstCharXY / board.getWidth(), oldCol = firstCharXY % board.getWidth();
				if ((oldRow != row) || (oldCol != col)) {
					Object o = table.getValueAt(oldRow, oldCol);
					table.setValueAt(null,oldRow, oldCol);
					board.setCharacterPosition(xy, firstChar.getTeamColor(), firstChar);
					table.setValueAt(o, row, col);
					table.repaint();
					
					printStatus(firstChar);
					selectionIndex = 0;
				}
			}
			
		}
		else {
		// otherwise (the cell is not empty)
			
			setCharacterDisabled();
			//System.out.println("Selected a not empty cell");
			
			//If it's the first time that the player clicks on a character's cell
			if (selectionIndex == 0) {
				
				firstChar = board.characterAt(row, col);
				firstCharXY = xy;
				selectionIndex = 1;
				setItemEnabled();
				printStatus(firstChar);
				
			} else {
				
				//It's the second time, so we can attack or use a consumable item

				secondChar = board.characterAt(row,col);
				
				if (firstChar.getTeamColor() == secondChar.getTeamColor()){
					if (firstChar.hasConsumable()) { 
						firstChar.useConsumable(secondChar);
					}
				} else {
					if (firstChar.getHP() > 0 && secondChar.getHP() > 0) {
						board.attack(firstChar, secondChar);
					}
				}
				
				selectionIndex = 0;
				printStatus(secondChar);
			}
			
		}
	}
	
	/**
	 * Update the character's status at the main window
	 * 
	 * @param character
	 */
	public void printStatus(Character c) {
		this.lblStatusAlias.setText("Alias: "+c.getName());
		String str;
		if (c.getClass() == Fighter.class)
			str = "Type: Fighter";
		else
			str = "Type: Ranger";
		this.lblStatusType.setText(str);
		this.lblStatusHP.setText("HP: "+c.getHP());
		this.lblStatusXP.setText("XP: "+c.getXP());
	}
	
	/**
	 * Verifies if a cell is empty or not
	 * 
	 * @param row
	 * @param col
	 * 
	 * @return true/false
	 */
	public boolean cellIsEmpty(int row, int col) {
		
		if (!board.isOccupied(row, col))
			return true;
		
		return false;
		
	}
	
	/**
	 * Creates a new Team
	 */
	public void createTeam() {
		
		if (!txtTeamName.getText().isEmpty()) {
			if (board.addTeam(Colors.values()[cbTeamColor.getSelectedIndex()],new Team(txtTeamName.getText(),Colors.values()[cbTeamColor.getSelectedIndex()]))) {
				
				cbFighterTeam.addItem(board.getTeam(Colors.values()[cbTeamColor.getSelectedIndex()]).getName());
				cbRangerTeam.addItem(board.getTeam(Colors.values()[cbTeamColor.getSelectedIndex()]).getName());
				
				txtTeamName.setText("");
				
			} else {
				System.out.println("Error during the addition of the new team"); 
			}
		}
		
		selectionIndex = 0;
		
	}
	
	/**
	 * Creates a new Fighter
	 */
	public void createFighter() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		int xy = board.getWidth()*row + col;
		
		if ((!txtFighterAlias.getText().isEmpty()) && (cbFighterTeam.getItemCount() != 0)) {
			
			for (Colors c : Colors.values()) {
				
				Team t = board.getTeam(c);
				
				if ((t != null) && (t.getName().equals(cbFighterTeam.getSelectedItem())) )
				{
					Fighter f = new Fighter(txtFighterAlias.getText(), sldFighterPower.getValue(), sldFighterStrength.getValue(), sldFighterSpeed.getValue(), sldFighterDexterity.getValue(), sldFighterConstitution.getValue());
					if (board.getTeam(c).addCharacter(f)) {
						
						board.setCharacterPosition(xy, c, f);
						//System.out.println("Created fighter at ("+ row + "," + col + ")");
						txtFighterAlias.setText("");
						setCharacterDisabled();
						printStatus(f);
						
						table.setValueAt(new ImageIcon("./resource/Fighter.png"), row, col);
					}
				}
			}

			cbFighterTeam.setSelectedIndex(0);
			sldFighterPower.setValue(25);
			sldFighterStrength.setValue(25);
			sldFighterSpeed.setValue(25);
			sldFighterDexterity.setValue(25);
			sldFighterConstitution.setValue(25);
			updateFighterSliders();
			
		}
		
		selectionIndex = 0;
		
	}
	
	/**
	 * Creates a new Ranger
	 */
	public void createRanger() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		int xy = board.getWidth()*row + col;
		
		if ((!txtRangerAlias.getText().isEmpty()) && (cbRangerTeam.getItemCount() != 0)) {
			
			for (Colors c : Colors.values()) {
				
				Team t = board.getTeam(c);
				
				if ((t != null) && (t.getName().equals(cbRangerTeam.getSelectedItem())) )
				{
					Ranger f = new Ranger(txtRangerAlias.getText(), sldRangerAccuracy.getValue(), sldRangerStrength.getValue(), sldRangerSpeed.getValue(), sldRangerDexterity.getValue(), sldRangerConstitution.getValue());
					if (board.getTeam(c).addCharacter(f)) {
						
						board.setCharacterPosition(xy, c, f);
						//System.out.println("Created Ranger at ("+ row + "," + col + ")");
						txtRangerAlias.setText("");
						setCharacterDisabled();
						printStatus(f);
						
						table.setValueAt(new ImageIcon("./resource/Ranger.png"), row, col);
					}
				}
			}

			cbRangerTeam.setSelectedIndex(0);
			sldRangerAccuracy.setValue(25);
			sldRangerStrength.setValue(25);
			sldRangerSpeed.setValue(25);
			sldRangerDexterity.setValue(25);
			sldRangerConstitution.setValue(25);
			updateRangerSliders();
			
		}
		
		selectionIndex = 0;
		
	}
	
	/**
	 * Creates a new Weapon
	 */
	public void createWeapon() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		
		Character c = board.characterAt(row, col);
		if (c!=null) {
			
			if(txtWeaponName.getText().equals("")){
				System.out.println("Error: item doesn't have a name");
				return;
			}
			
			Double price = null;
			try{
				price = new Double(txtWeaponPrice.getText());
			}
				catch (NumberFormatException e){
					System.out.println("Exception: price must be a number");
					return;
				}
			Integer attackpts = null;
			try{
				attackpts = new Integer(txtWeaponAttackPts.getText());
			}catch (NumberFormatException e){
				System.out.println("Exception: attackPts must be a number");
				return;
			}
			Integer range = null;
			try{
				range = new Integer(txtWeaponRange.getText());
			}catch (NumberFormatException e){
				System.out.println("Exception: range must be a number");
				return;
			}
			
			try {
				c.addItem(c.inventorySize(), new Weapon(txtWeaponName.getText(),price.doubleValue(),attackpts.intValue(),range.intValue()));
				
				txtWeaponAttackPts.setText("");
				txtWeaponName.setText("");
				txtWeaponPrice.setText("");
				txtWeaponRange.setText("");
				
				setCharacterDisabled();
				setItemDisabled();
				
			} catch (ItemExceptionPrice e) {
				e.printLog();
			} catch (ItemExceptionName e) {
				e.printLog();
			} catch (ItemExceptionAttackPts e) {
				e.printLog();
			} catch (ItemExceptionRange e) {
				e.printLog();
			}catch (NumberFormatException e){
				System.out.println("Exception: price must be a number");
			}
			
		}
		
		selectionIndex = 0;
		
	}

	/**
	 * Creates a new Armor
	 */
	public void createArmor() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		
		Character c = board.characterAt(row, col);
		if (c!=null) {
			
			if(txtArmorName.getText().equals("")){
				System.out.println("Error: item doen't have a name");
				return;
			}
			
			Double price = null;
			try{
				price = new Double(txtArmorPrice.getText());
			}
				catch (NumberFormatException e){
					System.out.println("Exception: price must be a number");
					return;
				}
			Integer defensepts = null;
			try{
				defensepts= new Integer(txtArmorDefensePts.getText());
			}catch (NumberFormatException e){
				System.out.println("Exception: defendePts must be a number");
				return;
			}
			
			try {
				c.addItem(c.inventorySize(), new Armor(txtArmorName.getText(),price.doubleValue(),defensepts.intValue()));
				
				txtArmorDefensePts.setText("");
				txtArmorName.setText("");
				txtArmorPrice.setText("");
				
				setCharacterDisabled();
				setItemDisabled();
				
			} catch (ItemExceptionPrice e) {
				e.printLog();
			} catch (ItemExceptionName e) {
				e.printLog();
			} catch (ItemExceptionDefensePts e) {
				e.printLog();
			}catch (NumberFormatException e){
				System.out.println("Exception: price must be a number");
			}
			
		}
		
		selectionIndex = 0;
		
	}
	
	/**
	 * Creates a new HealthPotion
	 */
	public void createHealth() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		
		Character c = board.characterAt(row, col);
		if (c!=null) {
			
			if(txtHealthName.getText().equals("")){
				System.out.println("Error: item doen't have a name");
				return;
			}
			
			Double price = null;
			try{
				price = new Double(txtHealthPrice.getText());
			}
				catch (NumberFormatException e){
					System.out.println("Exception: price must be a number");
					return;
				}
			Integer restore = null;
			try{
				restore = new Integer(txtHealthRestorePts.getText());
			}catch (NumberFormatException e){
				System.out.println("Exception: restore must be a number");
				return;
			}
			
			try {
				
				if (c.hasConsumable())
					c.addItem(c.inventorySize(), new HealthPotion(txtHealthName.getText(),price.doubleValue(),restore.intValue()));
				else
					c.setConsumable(new HealthPotion(txtHealthName.getText(),price.doubleValue(),restore.intValue()));
				
				txtHealthRestorePts.setText("");
				txtHealthName.setText("");
				txtHealthPrice.setText("");
				
				setCharacterDisabled();
				setItemDisabled();
				
			} catch (ItemExceptionPrice e) {
				e.printLog();
			} catch (ItemExceptionName e) {
				e.printLog();
			} catch (ItemExceptionRestorePts e) {
				e.printStackTrace();
			}catch (NumberFormatException e){
				System.out.println("Exception: price must be a number");
			}
			
		}
		
		selectionIndex = 0;
		
	}
	
	/**
	 * Creates a new RevivePotion
	 */
	public void createRevive() {
		
		int row = table.getSelectedRow(), col = table.getSelectedColumn();
		
		Character c = board.characterAt(row, col);
		if (c!=null) {
			
			if(txtReviveName.getText().equals("")){
				System.out.println("Error: item doen't have a name");
				return;
			}
			
			Double price = null;
			try{
				price = new Double(txtRevivePrice.getText());
			}
				catch (NumberFormatException e){
					System.out.println("Exception: price must be a number");
					return;
				}
			Integer revive = null;
			
			try{
				revive = new Integer(txtReviveRevivePts.getText());
			}catch (NumberFormatException e){
				System.out.println("Exception: revive must be a number");
				return;
			}
			
			try {
				
				if (c.hasConsumable())
					c.addItem(c.inventorySize(), new RevivePotion(txtReviveName.getText(),price.doubleValue(),revive.intValue()));
				else
					c.setConsumable(new RevivePotion(txtReviveName.getText(),price.doubleValue(),revive.intValue()));
				
				txtReviveRevivePts.setText("");
				txtReviveName.setText("");
				txtRevivePrice.setText("");
				
				setCharacterDisabled();
				setItemDisabled();
				
			} catch (ItemExceptionPrice e) {
				e.printLog();
			} catch (ItemExceptionName e) {
				e.printLog();
			} catch (ItemExceptionRevivePts e) {
				e.printStackTrace();
			}catch (NumberFormatException e){
				System.out.println("Exception: price must be a number");
			}
			
		}
		
		selectionIndex = 0;
		
	}

}

package oaks;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ChordPracticeFrame extends JFrame {
	private static final String NOTESET = "ABCDEFG";
	private static int[][][] tallies;
	private static int a;
	private static int b;
	private static int c;
	JPanel contentPane;
	JLabel lblCMajor;
	private final Action action = new SwingAction();
	private boolean major, minor, maj7, dom7, m7, m7b5, sixchord, dim7, fillerbool = true;
	int delaybetweenchords = 3000;
	public static ChordPracticeFrame frame;
	private ThreadExtend tExtend = new ThreadExtend();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ChordPracticeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String gen(){
		System.out.println(major + ", " + minor + ", "  + maj7  + ", " + dom7  + ", " + m7  + ", " + m7b5  + ", " + sixchord  + ", " + dim7);
		double x = java.lang.Math.random()*7;
		double y = java.lang.Math.random()*7;
		int intx= (int) x;
		int inty=(int) y;
		String S = NOTESET.substring(intx,intx+1);
		a = intx;
		double z = java.lang.Math.random();
		if(z < .33 && !S.equals("E") && !S.equals("B")) {
			S = S + "#";
			c = 0;
		}
		else if (z <.66 && !S.equals("C") && !S.equals("F")) {
			S = S + "b";
			c = 1;
		}
		else c =2;
		b = inty;
		System.out.println(inty);
		switch (inty) {
			case 0:{
				if(sixchord) S+="6";
				else return gen();
				}
				break;
			case 1: {
				if(maj7) S = S +"maj7";
				else return gen();
			}
				break;
			case 2: {
				if (m7) S = S +"m7";
				else return gen();
			}
				break;
			case 3: {
				if(m7b5) S = S + "m7b5";
				else return gen();
			}
				break;
			case 4: {
				System.out.println("testing dim7");
				System.out.println(dim7);
				if(dim7) S = S +"dim7";
				else return gen();
			}
				break;
			case 5: {
				if(minor)S = S +"m";
				else return gen();

			}
				break;
			case 6: {
				if(dom7) S = S + "7";
				else return gen();
			}
				break;
			case 7: {
				System.out.println("major = " + major);
				if(major)
				{
					S = S;
					System.out.println("major = " + major);
				}
				else 
				{
					System.out.println("major = " + major);
					return gen();
				}
				}
			break;
		}
		return S;
	}
	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public ChordPracticeFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setResizable(false);
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(ChordPracticeFrame.class.getResource("/oaks/musicicon.png")));
		setTitle("Chord Practice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JCheckBox chckbxMajor = new JCheckBox("Major");
		chckbxMajor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				major  ^=true;
			}
		});
		chckbxMajor.setSelected(true);
		GridBagConstraints gbc_chckbxMajor = new GridBagConstraints();
		gbc_chckbxMajor.anchor = GridBagConstraints.WEST;
		gbc_chckbxMajor.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMajor.gridx = 0;
		gbc_chckbxMajor.gridy = 1;
		contentPane.add(chckbxMajor, gbc_chckbxMajor);
		
		JCheckBox chckbxMinor = new JCheckBox("Minor");
		chckbxMinor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				minor ^=true;
			}
		});
		chckbxMinor.setSelected(true);
		GridBagConstraints gbc_chckbxMinor = new GridBagConstraints();
		gbc_chckbxMinor.anchor = GridBagConstraints.WEST;
		gbc_chckbxMinor.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMinor.gridx = 0;
		gbc_chckbxMinor.gridy = 2;
		contentPane.add(chckbxMinor, gbc_chckbxMinor);
		
		lblCMajor = new JLabel("Click Start");
		lblCMajor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCMajor.setFont(new Font("Tahoma", Font.PLAIN, 44));
		GridBagConstraints gbc_lblCMajor = new GridBagConstraints();
		gbc_lblCMajor.gridheight = 5;
		gbc_lblCMajor.gridwidth = 9;
		gbc_lblCMajor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCMajor.gridx = 2;
		gbc_lblCMajor.gridy = 2;
		contentPane.add(lblCMajor, gbc_lblCMajor);
		
		JCheckBox chckbxMaj = new JCheckBox("maj7");
		chckbxMaj.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				maj7  ^=true;
			}
		});
		chckbxMaj.setSelected(true);
		GridBagConstraints gbc_chckbxMaj = new GridBagConstraints();
		gbc_chckbxMaj.anchor = GridBagConstraints.WEST;
		gbc_chckbxMaj.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMaj.gridx = 0;
		gbc_chckbxMaj.gridy = 3;
		contentPane.add(chckbxMaj, gbc_chckbxMaj);
		
		JCheckBox chckbxM = new JCheckBox("m7");
		chckbxM.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				m7  ^=true;
			}
		});
		chckbxM.setSelected(true);
		GridBagConstraints gbc_chckbxM = new GridBagConstraints();
		gbc_chckbxM.anchor = GridBagConstraints.WEST;
		gbc_chckbxM.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxM.gridx = 0;
		gbc_chckbxM.gridy = 4;
		contentPane.add(chckbxM, gbc_chckbxM);
		
		JCheckBox checkBox = new JCheckBox("7");
		checkBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				dom7  ^=true;
			}
		});
		checkBox.setSelected(true);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.anchor = GridBagConstraints.WEST;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 5;
		contentPane.add(checkBox, gbc_checkBox);
		
		JSlider slider = new JSlider(0,8000,4000);
		slider.setToolTipText("Time between chords in miliseconds");
		slider.setMinorTickSpacing(500);
		slider.setMajorTickSpacing(2000);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		//slider.setValue(9000);
		//slider.setMaximum(10000);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				delaybetweenchords = slider.getValue();
			}
		});
		slider.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.VERTICAL;
		gbc_slider.gridheight = 7;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 1;
		contentPane.add(slider, gbc_slider);
		
		JCheckBox chckbxMb = new JCheckBox("m7b5");
		chckbxMb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				m7b5  ^=true;
			}
		});
		chckbxMb.setSelected(true);
		GridBagConstraints gbc_chckbxMb = new GridBagConstraints();
		gbc_chckbxMb.anchor = GridBagConstraints.WEST;
		gbc_chckbxMb.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMb.gridx = 0;
		gbc_chckbxMb.gridy = 6;
		contentPane.add(chckbxMb, gbc_chckbxMb);
		
		JCheckBox chckbxDim = new JCheckBox("dim7");
		chckbxDim.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("I'm changing the state on dim7");
				dim7  ^=true;
			}
		});
		chckbxDim.setSelected(true);
		GridBagConstraints gbc_chckbxDim = new GridBagConstraints();
		gbc_chckbxDim.anchor = GridBagConstraints.WEST;
		gbc_chckbxDim.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDim.gridx = 0;
		gbc_chckbxDim.gridy = 7;
		contentPane.add(chckbxDim, gbc_chckbxDim);
		
		JCheckBox checkBox_1 = new JCheckBox("6");
		checkBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sixchord ^=true;
			}
		});
		checkBox_1.setSelected(true);
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.anchor = GridBagConstraints.WEST;
		gbc_checkBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_1.gridx = 0;
		gbc_checkBox_1.gridy = 8;
		contentPane.add(checkBox_1, gbc_checkBox_1);
		
		JLabel lblSpeed = new JLabel("Speed");
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.insets = new Insets(0, 0, 0, 5);
		gbc_lblSpeed.gridx = 1;
		gbc_lblSpeed.gridy = 8;
		contentPane.add(lblSpeed, gbc_lblSpeed);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setAction(action);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 9;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 8;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Click here to start...");
		}
		public void actionPerformed(ActionEvent e) {
			tExtend.start();
		}
	}
}

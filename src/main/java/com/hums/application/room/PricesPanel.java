package com.hums.application.room;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.hums.roomManagementSystem.RMS_Registry;
import com.hums.tools.data.FileHandling;

public class PricesPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton buttonSetPrices;
	private JPanel panel_1;
	private JSpinner spinnerDinner;
	private JSpinner spinnerLaunch;
	private JSpinner spinnerBreakfast;
	private JSpinner spinnerVIP;
	private JSpinner spinnerPenthouse;
	private JSpinner spinnerRegular;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public PricesPanel() {
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 545, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		buttonSetPrices = new JButton("Set Prices");
		buttonSetPrices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double breakfastCost = Double.parseDouble(spinnerBreakfast.getValue().toString());
				double launchCost = Double.parseDouble(spinnerLaunch.getValue().toString());
				double dinnerCost = Double.parseDouble(spinnerDinner.getValue().toString());
				
				
				int regularCost = Integer.parseInt(spinnerRegular.getValue().toString());
				int penthouseCost = Integer.parseInt(spinnerPenthouse.getValue().toString());
				int vipCost = Integer.parseInt(spinnerVIP.getValue().toString());
				
				
				RMS_Registry aReg = RMS_Registry.getInstance();
				
				aReg.setBreakfastCost(breakfastCost);
				aReg.setLaunchCost(launchCost);
				aReg.setDinnerCost(dinnerCost);
				
				aReg.getRoomList().setRegularCost(regularCost);
				aReg.getRoomList().setPenthouseCost(penthouseCost);
				aReg.getRoomList().setVipCost(vipCost);
				
				JOptionPane.showMessageDialog(null, "Prices updated successfully");
				
				//Write to File
				FileHandling.exportToFile(RMS_Registry.getInstance());
				
				
			}
		});
		panel_1.add(buttonSetPrices);
		
		JLabel lblNewLabel_4 = new JLabel("VIP");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_1 = new JLabel("Regular");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_3 = new JLabel("Penthouse");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_8 = new JLabel("Breakfast");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_9 = new JLabel("Launch");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNewLabel_10 = new JLabel("Dinner");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinnerDinner = new JSpinner();
		spinnerDinner.setModel(new SpinnerNumberModel(0, 0, 9999, 0.1));
		((DefaultEditor) spinnerDinner.getEditor()).getTextField().setEditable(false);
		
		spinnerLaunch = new JSpinner();
		spinnerLaunch.setModel(new SpinnerNumberModel(0, 0, 9999, 0.1));
		((DefaultEditor) spinnerLaunch.getEditor()).getTextField().setEditable(false);
		
		spinnerBreakfast = new JSpinner();
		spinnerBreakfast.setModel(new SpinnerNumberModel(0, 0, 9999, 0.1));
		((DefaultEditor) spinnerBreakfast.getEditor()).getTextField().setEditable(false);
		
		spinnerVIP = new JSpinner();
		spinnerVIP.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		((DefaultEditor) spinnerVIP.getEditor()).getTextField().setEditable(false);
		
		spinnerRegular = new JSpinner();
		spinnerRegular.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		((DefaultEditor) spinnerRegular.getEditor()).getTextField().setEditable(false);
		
		spinnerPenthouse = new JSpinner();
		spinnerPenthouse.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		((DefaultEditor) spinnerPenthouse.getEditor()).getTextField().setEditable(false);
		
		JLabel lblNewLabel_6 = new JLabel("/bed");
		
		JLabel lblNewLabel_2 = new JLabel("/bed");
		
		JLabel lblNewLabel_5 = new JLabel("(2 Beds)");
		
		JLabel lblNewLabel_7 = new JLabel("Meal Prices");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel = new JLabel("Room Prices (/day)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_7)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(spinnerDinner, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerLaunch, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerBreakfast, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(spinnerVIP, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_6))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(spinnerRegular, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_2))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(spinnerPenthouse, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel_5)))))
					.addContainerGap(351, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(21)
							.addComponent(lblNewLabel_1)
							.addGap(103)
							.addComponent(lblNewLabel_7)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8)
								.addComponent(spinnerBreakfast, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_9)
								.addComponent(spinnerLaunch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_10)
								.addComponent(spinnerDinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerRegular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addGap(21)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinnerPenthouse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_3))
							.addGap(21)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(spinnerVIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4))))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	
	public void updatePrices() {
		
		RMS_Registry aReg = RMS_Registry.getInstance();
		
		spinnerBreakfast.setValue(aReg.getBreakfastCost());
		spinnerLaunch.setValue(aReg.getLaunchCost());
		spinnerDinner.setValue(aReg.getDinnerCost());
		
		spinnerRegular.setValue(aReg.getRoomList().getRegularCost());
		spinnerPenthouse.setValue(aReg.getRoomList().getPenthouseCost());
		spinnerVIP.setValue(aReg.getRoomList().getVipCost());
		
		
	}
	
}

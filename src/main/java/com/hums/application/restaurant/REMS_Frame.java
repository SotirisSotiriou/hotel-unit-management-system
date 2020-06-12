package com.hums.application.restaurant;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.hums.application.login.LoginFrame;
import com.hums.restaurantManagementSystem.Ingredient;
import com.hums.restaurantManagementSystem.REMS_Registry;
import com.hums.tools.data.FileHandling;



public class REMS_Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private static JPanel cards;
	private JButton buttonTodaysMenu;
	private JButton buttonCreateDish;
	private static CardLayout cl_cards;
	private Component todayMenuPanel;
	private Component weeklyMenuPanel;
	private DishesPanel dishesPanel;
	private CreateDishPanel createDishPanel;
	private JButton buttonNewIngredient;
	private JButton btnNewButton;
	private NewIngredientPanel newIngredientPanel;
	private IngredientsPanel ingredientsPanel;
	private static UpdateIngredientStockPanel updateIngredientStockPanel;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem menuItemLogout;

	

	/**
	 * Create the frame.
	 */
	public REMS_Frame() {
		
		
		REMS_Registry reg = (REMS_Registry) FileHandling.importFromFile("rems-registry.ser");
		REMS_Registry.setInstance(reg);
		
		
		
		setMinimumSize(new Dimension(1120, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 435);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		menuItemLogout = new JMenuItem("Logout");
		menuItemLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new LoginFrame();
				dispose();
				
			}
		});
		menuFile.add(menuItemLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		cards = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(cards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(2))
		);
		cards.setLayout(new CardLayout(0, 0));
		
		todayMenuPanel = new TodayMenuPanel();
		weeklyMenuPanel = new WeeklyMenuPanel();
		dishesPanel = new DishesPanel();
		createDishPanel = new CreateDishPanel();
		newIngredientPanel = new NewIngredientPanel();
		ingredientsPanel = new IngredientsPanel();
		updateIngredientStockPanel = new UpdateIngredientStockPanel();
		
		
		cl_cards = (CardLayout)(cards.getLayout());
		
		
		cards.add(todayMenuPanel, "today");
		cards.add(weeklyMenuPanel, "weekly");
		cards.add(dishesPanel, "dishes");
		cards.add(createDishPanel,"create");
		cards.add(newIngredientPanel,"newIng");
		cards.add(ingredientsPanel,"ingredients");
		cards.add(updateIngredientStockPanel,"updateStock");
		
		cl_cards.show(cards, "weekly");
		
		
		buttonTodaysMenu = new JButton("Today's menu");
		buttonTodaysMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				((TodayMenuPanel) todayMenuPanel).updateModel();
				cl_cards.show(cards, "today");
				
			}
		});
		
		JButton buttonWeeklyMenu = new JButton("Weekly Menu");
		buttonWeeklyMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				((WeeklyMenuPanel) weeklyMenuPanel).updateModel();
				cl_cards.show(cards, "weekly");
				
			}
		});
		
		buttonCreateDish = new JButton("Create Dish");
		buttonCreateDish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				createDishPanel.resetAllFields();
				cl_cards.show(cards, "create");
				
			}
		});
		
		JButton buttonDishes = new JButton("Dishes");
		buttonDishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				dishesPanel.updateDishesModel();
				cl_cards.show(cards, "dishes");
				
				
			}
		});
		
		buttonNewIngredient = new JButton("New Ingredient");
		buttonNewIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cl_cards.show(cards, "newIng");
				
			}
		});
		
		btnNewButton = new JButton("Ingredients");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ingredientsPanel.updateModel();
				cl_cards.show(cards, "ingredients");
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonTodaysMenu)
						.addComponent(buttonWeeklyMenu)
						.addComponent(buttonCreateDish)
						.addComponent(buttonDishes)
						.addComponent(buttonNewIngredient)
						.addComponent(btnNewButton))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonTodaysMenu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonWeeklyMenu)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonCreateDish)
					.addGap(5)
					.addComponent(buttonDishes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buttonNewIngredient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap(369, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		this.setVisible(true);
		
	}
	
	public static void showUpdateIngredientStockPanel(Ingredient ingredientToUpdate) {
		
		updateIngredientStockPanel.setIngredientToUpdate(ingredientToUpdate);
		cl_cards.show(cards, "updateStock");
		
	}
	
	
}

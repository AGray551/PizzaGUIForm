import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {
    JPanel mainPnl;
    JPanel crust;
    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;

    JPanel sizePnl;
    JComboBox size;
    JPanel toppings;
    JCheckBox olives;
    JCheckBox pepperoni;
    JCheckBox sausage;
    JCheckBox chicken;
    JCheckBox pickles;
    JCheckBox mushrooms;

    JTextArea text;

    JPanel buttons;
    JButton order;
    JButton clear;
    JButton quit;



    public PizzaGUIFrame() {
        setLayout(new BorderLayout());
        mainPnl = new JPanel();

        createCrust();
        mainPnl.add(crust);

        createSizePnl();
        mainPnl.add(sizePnl);

        createToppings();
        mainPnl.add(toppings);

        createText();
        text.setPreferredSize(new Dimension(300, 300));
        mainPnl.add(text);

        createButtons();
        buttons.setPreferredSize(new Dimension(300, 300));
        mainPnl.add(buttons);

        add(mainPnl);
        setTitle("Pizza Ordering Forum");
        setLocationRelativeTo(null);
        setLocation(500, 200);
        setSize(new Dimension(800, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createCrust() {
        crust = new JPanel();
        crust.setLayout(new GridLayout(1, 3));
        crust.setBorder(new TitledBorder(new EtchedBorder(),"Crust"));

        thin  = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepDish  = new JRadioButton("Deep-dish");

        crust.add(thin);
        crust.add(regular);
        crust.add(deepDish);

        regular.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(thin);
        group.add(regular);
        group.add(deepDish);

    }

    private void createSizePnl() {
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(),"Size"));

        size = new JComboBox();
        size.addItem("Small");
        size.addItem("Medium");
        size.addItem("Large");
        size.addItem("Super");
        sizePnl.add(size);
    }


    private void createToppings() {
        toppings = new JPanel();
        toppings.setLayout(new GridLayout(2,3));
        toppings.setBorder(new TitledBorder(new EtchedBorder(),"Toppings"));

        olives = new JCheckBox("Olives");
        pepperoni = new JCheckBox("Pepperoni");
        sausage = new JCheckBox("Sausage");
        chicken = new JCheckBox("Chicken");
        pickles = new JCheckBox("Pickles");
        mushrooms = new JCheckBox("Mushrooms");

        toppings.add(olives);
        toppings.add(pepperoni);
        toppings.add(sausage);
        toppings.add(chicken);
        toppings.add(pickles);
        toppings.add(mushrooms);
    }

    private void createText() {
        text = new JTextArea();

        text = new JTextArea("",2,45);
        text.setBorder(new TitledBorder(new EtchedBorder(),"Order"));

        add(text, BorderLayout.NORTH);
    }

    private void createButtons() {
        buttons = new JPanel();

        order = new JButton("Order");
        order.addActionListener(
                (ActionEvent ae) ->
                {
                    String res ="======================================\n";
                    res += "Type of Crust & Size: ";
                    res += (String) size.getSelectedItem();
                    if(thin.isSelected())
                        res += " Thin Pizza ";
                    else if(regular.isSelected())
                        res += " Regular Pizza ";
                    else if(deepDish.isSelected())
                        res += " Deep-dish Pizza ";
                    double sizePrice = 0.0;
                    String sizeSelected = (String) size.getSelectedItem();
                    switch(sizeSelected) {
                        case "Small":
                            sizePrice = 8.0;
                            break;
                        case "Medium":
                            sizePrice = 12.0;
                            break;
                        case "Large":
                            sizePrice = 16.0;
                            break;
                        case "Super":
                            sizePrice = 20.0;
                            break;
                    }

                    res += "- $" + sizePrice + "\n";


                    // get the items
                    res += "Ingredients: ";
                    double topPrice = 0;
                    if (olives.isSelected()) {
                        res += "\tOlives ";
                        topPrice += 1.0;
                    }

                    if (pepperoni.isSelected()) {
                        res += "\tPepperoni ";
                        topPrice += 1.0;
                    }

                    if (sausage.isSelected()) {
                        res += "\tSausage ";
                        topPrice += 1.0;
                    }

                    if (chicken.isSelected()) {
                        res += "\tChicken\n";
                        topPrice += 1.0;
                    }

                    if (pickles.isSelected()) {
                        res += "\tPickles ";
                        topPrice += 1.0;
                    }

                    if (mushrooms.isSelected()) {
                        res += "\tMushrooms ";
                        topPrice += 1.0;
                    }
                    res += "\n";
                    res += "\n";
                    res += "\n";
                    double subTotal = 0.0;
                    subTotal = topPrice + sizePrice;
                    res += "Sub-total: $" + subTotal + "\n";
                    double taxAmount = 0.0;
                    taxAmount = subTotal * 0.07;
                    res += "Tax: $" + taxAmount + "\n";

                    double total = 0.0;
                    total = subTotal + taxAmount;
                    res += "------------------------------------------------------------------\n";
                    res += "Total: $" + total + "\n";
                    res += "======================================";
                    text.setText(res);
                }
        );
        buttons.add(order);

        clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                regular.setSelected(true);

                size.setSelectedIndex(0);

                olives.setSelected(false);
                pepperoni.setSelected(false);
                sausage.setSelected(false);
                chicken.setSelected(false);
                pickles.setSelected(false);
                mushrooms.setSelected(false);

                text.setText("");
            }
        });
        buttons.add(clear);

        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttons.add(quit);
        add(buttons, BorderLayout.SOUTH);
    }
}
